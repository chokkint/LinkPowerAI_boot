package com.ai.ringball.framework.db.mybatis;

import com.ai.ringball.framework.constants.SysConstants;
import com.ai.ringball.framework.utility.common.PageUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

//import org.apache.ibatis.executor.parameter.DefaultParameterHandler;

//只拦截select部分
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PageInterceptor implements Interceptor {

	private final static Log logger = LogFactory.getLog(PageInterceptor.class);

	private String dialect;
	private String defaultDialect = "oracle";
	private String pageSqlId;
	private String defaultPageSqlId = SysConstants.CONSTANT_NULL_STRING;

	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
		// 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环
		// 可以分离出最原始的的目标类)
		while (metaStatementHandler.hasGetter("h")) {
			Object object = metaStatementHandler.getValue("h");
			metaStatementHandler = SystemMetaObject.forObject(object);
		}
		// 分离最后一个代理对象的目标类
		while (metaStatementHandler.hasGetter("target")) {
			Object object = metaStatementHandler.getValue("target");
			metaStatementHandler = SystemMetaObject.forObject(object);
		}
		Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
		dialect = configuration.getVariables().getProperty("dialect");

		if (null == dialect || SysConstants.CONSTANT_NULL_STRING.equals(dialect)) {
			logger.warn("Property dialect is not setted,use default 'mysql' ");
			dialect = defaultDialect;
		}
		pageSqlId = configuration.getVariables().getProperty("pageSqlId");
		if (null == pageSqlId || SysConstants.CONSTANT_NULL_STRING.equals(pageSqlId)) {
			logger.warn("Property pageSqlId is not setted,use default '.*Page$' ");
			pageSqlId = defaultPageSqlId;
		}
		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
		// 只重写需要分页的sql语句。通过MappedStatement的ID匹配，默认重写以Page结尾的
		// MappedStatement的sql
		if (mappedStatement.getId().matches(pageSqlId)) {
			BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
			Object parameterObject = boundSql.getParameterObject();
			if (parameterObject == null) {
				throw new NullPointerException("parameterObject is null!");
			} else {
				// 分页参数作为参数对象parameterObject的一个属性
				Object pageObj = metaStatementHandler.getValue("delegate.boundSql.parameterObject.page");
				if (pageObj == null)
					return invocation.proceed();
				PageUtils page = (PageUtils) pageObj;
				String sql = boundSql.getSql();
				// 重写sql
				String pageSql = buildPageSql(sql, page);
				metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
				// 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
				metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
				metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
				Connection connection = (Connection) invocation.getArgs()[0];
				// 重设分页参数里的总页数等
				setPageParameter(sql, connection, mappedStatement, boundSql, page);
			}
		}
		// 将执行权交给下一个拦截器
		return invocation.proceed();
	}

	private String buildPageSql(String sql, PageUtils page) {
		if (page != null) {
			StringBuilder pageSql = new StringBuilder();
			if ("mysql".equals(dialect)) {
				pageSql = buildPageSqlForMysql(sql, page);
			} else if ("oracle".equals(dialect)) {
				pageSql = buildPageSqlForOracle(sql, page);
			} else {
				return sql;
			}
			return pageSql.toString();
		} else {
			return sql;
		}
	}

	public StringBuilder buildPageSqlForMysql(String sql, PageUtils page) {
		StringBuilder pageSql = new StringBuilder(100);
		String beginrow = String.valueOf((page.getPageNo() - 1) * page.getPageSize());
		pageSql.append(sql);
		pageSql.append(" limit " + beginrow + "," + page.getPageSize());
		return pageSql;
	}

	public StringBuilder buildPageSqlForOracle(String sql, PageUtils page) {
		StringBuilder pageSql = new StringBuilder(100);
		String beginrow = String.valueOf((page.getPageNo() - 1) * page.getPageSize());
		String endrow = String.valueOf(page.getPageNo() * page.getPageSize() + 1);
		pageSql.append("select * from ( select temp.*, rownum row_id from ( ");
		pageSql.append(sql);
		pageSql.append(" ) temp where rownum < ").append(endrow);
		pageSql.append(") where row_id > ").append(beginrow);
		return pageSql;
	}

	/**
	 * 从数据库里查询总的记录数并计算总页数，回写进分页参数<code>PageUtils</code>,这样调用 者就可用通过 分页参数
	 * <code>PageUtils</code>获得相关信息。
	 * 
	 * @param sql
	 * @param connection
	 * @param mappedStatement
	 * @param boundSql
	 * @param page
	 */
	private void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement, BoundSql boundSql, PageUtils page) {
		// 记录总记录数
		String countSql = "select count(0) as total from (" + sql + ") table_count";
		PreparedStatement countStmt = null;
		ResultSet rs = null;
		try {
			countStmt = connection.prepareStatement(countSql);
			BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
			setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());
			rs = countStmt.executeQuery();
			int totalCount = 0;
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
			page.setPageCount(totalCount);
		} catch (SQLException e) {
			logger.error("Ignore this exception", e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("Ignore this exception", e);
			}
			try {
				countStmt.close();
			} catch (SQLException e) {
				logger.error("Ignore this exception", e);
			}
		}
	}

	/**
	 * 对SQL参数(?)设值
	 * 
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws SQLException
	 */
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
		parameterHandler.setParameters(ps);
	}

	public Object plugin(Object target) {
		// 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的
		// 次数
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub

	}

}