package com.ai.ringball.inteceptor;

import com.ai.ringball.framework.annotation.LogTag;
import com.ai.ringball.framework.base.BaseEntity;
import com.ai.ringball.framework.base.EntityService;
import com.ai.ringball.framework.constants.SysConstants;
import com.ai.ringball.framework.utility.common.EntityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class LogAspect {

//	private SysLogService sysLogService;
//
//	public SysLogService getSysLogService() {
//		return sysLogService;
//	}
//
//	public void setSysLogService(SysLogService sysLogService) {
//		this.sysLogService = sysLogService;
//	}

	/**
	 * 功能说明:该方法拦截SynHandler标签
	 * 
	 * @param call
	 * @return
	 * @throws Throwable
	 */
	public Object doAround(ProceedingJoinPoint call) throws Throwable {

		// 1.调用服务方法并获得返回值,该处不捕获异常,由上一层异常AOP处理层捕获

		// 方法返回值
		Map<String, Object> result = new HashMap<String, Object>();

		// 目标方法
		Signature signature = call.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();

		// 获取标签参数
		LogTag logTag = null;
		if (method.isAnnotationPresent(LogTag.class)) {
			logTag = (LogTag) method.getAnnotation(LogTag.class);
		} else {
			// 调用target方法
			return call.proceed();
		}

		boolean single = logTag.isSingle();

		if (single) {
			result = processSingle(call, logTag);
		} else {
			result = processMore(call, logTag);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> processMore(ProceedingJoinPoint call, LogTag logTag) throws Throwable {

		Map<String, Object> result = new HashMap<String, Object>();

		String logType = logTag.logType();
		String busType = logTag.busType();
		String opType = logTag.opType();

		// 方法入参index
		int dataParamIndex = logTag.dataParamIndex();

		// 获取方法入参
		Object[] args = call.getArgs();
		Object data = args[dataParamIndex];

		// 目标服务
		Object target = call.getTarget();

		EntityService<BaseEntity> baseService = (EntityService<BaseEntity>) target;
		String logDesc = SysConstants.CONSTANT_NULL_STRING;
		Map<String, String> commons = new HashMap<String, String>();
		if (LogTag.OP_TYPE_UPDATE.equals(opType)) {
			List<BaseEntity> baseEntities = (List<BaseEntity>) data;
			for (BaseEntity baseEntity : baseEntities) {
				logDesc = "模块 - " + this.translateBizCodeToBizName(busType) + "[修改操作]\r\n";
				BaseEntity oldEntity = baseService.getEntityById(baseEntity);
				String oldEntityComm = EntityUtils.getEntityComm(oldEntity);
				logDesc += "操作明细：\r\n===============数据修改明细===============\r\n";
				logDesc += "修改前数据明细：\r\n[\r\n" + oldEntityComm + "\r\n]\r\n";
				commons.put(oldEntity.getEntityId(), logDesc);
			}
		} else if (LogTag.OP_TYPE_DELETE.equals(opType)) {
			String[] ids = (String[]) data;
			for (String baseEntityId : ids) {
				logDesc = "模块 - " + this.translateBizCodeToBizName(busType) + "[删除操作]\r\n";
				BaseEntity oldEntity = baseService.getEntityById(baseEntityId);
				String oldEntityComm = EntityUtils.getEntityComm(oldEntity);
				logDesc += "操作明细：\r\n===============数据删除明细===============\r\n";
				logDesc += "删除前数据明细：\r\n[\r\n" + oldEntityComm + "\r\n]\r\n";
				commons.put(oldEntity.getEntityId(), logDesc);
			}
		}

		// 调用目标服务方法
		result = (Map<String, Object>) call.proceed();

		if (SysConstants.ERROR_CODE_SUCCESS.equals(result.get(SysConstants.ERROR_CODE_KEY))) {
			// 获取变更后对象
			if (LogTag.OP_TYPE_UPDATE.equals(opType)) {
				List<BaseEntity> baseEntities = (List<BaseEntity>) data;
				for (BaseEntity baseEntity : baseEntities) {

					BaseEntity newEntity = baseService.getEntityById(baseEntity);
					String newEntityComm = EntityUtils.getEntityComm(newEntity);
					String entityId = newEntity.getEntityId();
					logDesc = commons.get(entityId);
					logDesc += "修改后数据明细：\r\n[\r\n" + newEntityComm + "\r\n]\r\n";
					commons.put(entityId, logDesc);
				}
			} else if (LogTag.OP_TYPE_INSERT.equals(opType)) {
				List<BaseEntity> baseEntities = (List<BaseEntity>) data;
				for (BaseEntity baseEntity : baseEntities) {
					logDesc = "模块 - " + this.translateBizCodeToBizName(busType) + "[新增操作]\r\n";
					BaseEntity newEntity = baseService.getEntityById(baseEntity);
					String newEntityComm = EntityUtils.getEntityComm(newEntity);
					String entityId = newEntity.getEntityId();
					logDesc += "操作明细：\r\n===============数据新增明细===============\r\n";
					logDesc += "新增数据明细：\r\n[\r\n" + newEntityComm + "\r\n]\r\n";
					commons.put(entityId, logDesc);
				}
			}

			// 调用日志保存服务,记录old entity 和 new entity
			for (Entry<String, String> entry : commons.entrySet()) {
				// String entityId = entry.getKey();
				String common = entry.getValue();

				// 写入日志
				this.insertLogInLogAspect(logType, busType, opType, common);
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> processSingle(ProceedingJoinPoint call, LogTag logTag) throws Throwable {

		Map<String, Object> result = new HashMap<String, Object>();
		String logType = logTag.logType();
		String busType = logTag.busType();
		String opType = logTag.opType();

		// 方法入参index
		int dataParamIndex = logTag.dataParamIndex();

		// 获取方法入参
		Object[] args = call.getArgs();
		Object data = args[dataParamIndex];

		// 目标服务
		Object target = call.getTarget();
		EntityService<BaseEntity> baseService = (EntityService<BaseEntity>) target;

		// 获取对象id
		String baseEntityId = null;
		BaseEntity baseEntity = null;
		if (!(data instanceof BaseEntity)) {
			// 传入的data为ID时处理
			baseEntityId = (String) data;
		} else {
			baseEntity = (BaseEntity) data;
			baseEntityId = baseEntity.getEntityId();
		}

		String logDesc = SysConstants.CONSTANT_NULL_STRING;
		// 获取变更前对象
		String oldEntityComm = SysConstants.CONSTANT_NULL_STRING;
		if (LogTag.OP_TYPE_UPDATE.equals(opType)) {
			logDesc = "模块 - " + this.translateBizCodeToBizName(busType) + "[修改操作]\r\n";
			BaseEntity oldEntity = baseService.getEntityById(baseEntityId);
			oldEntityComm = EntityUtils.getEntityComm(oldEntity);
			logDesc += "操作明细：\r\n===============数据修改明细===============\r\n";
			logDesc += "修改前数据明细：\r\n[\r\n" + oldEntityComm + "\r\n]\r\n";
		} else if (LogTag.OP_TYPE_DELETE.equals(opType)) {
			logDesc = "模块 - " + this.translateBizCodeToBizName(busType) + "[删除操作]\r\n";
			BaseEntity oldEntity = baseService.getEntityById(baseEntityId);
			oldEntityComm = EntityUtils.getEntityComm(oldEntity);
			logDesc += "操作明细：\r\n===============数据删除明细===============\r\n";
			logDesc += "删除前数据明细：\r\n[\r\n" + oldEntityComm + "\r\n]\r\n";
		}

		// 调用目标服务方法
		result = (Map<String, Object>) call.proceed();

		if (SysConstants.ERROR_CODE_SUCCESS.equals(result.get(SysConstants.ERROR_CODE_KEY))) {
			// 获取变更后对象
			String newEntityComm = SysConstants.CONSTANT_NULL_STRING;
			if (LogTag.OP_TYPE_UPDATE.equals(opType)) {
				BaseEntity newEntity = baseService.getEntityById(baseEntityId);
				newEntityComm = EntityUtils.getEntityComm(newEntity);
				logDesc += "修改后数据明细：\r\n[\r\n" + newEntityComm + "\r\n]\r\n";
			} else if (LogTag.OP_TYPE_INSERT.equals(opType)) {
				logDesc = "模块 - " + this.translateBizCodeToBizName(busType) + "[新增操作]\r\n";
				newEntityComm = EntityUtils.getEntityComm(baseEntity);
				logDesc += "操作明细：\r\n===============数据新增明细===============\r\n";
				logDesc += "新增数据明细：\r\n[\r\n" + newEntityComm + "\r\n]\r\n";
			}

			// 写入日志
			this.insertLogInLogAspect(logType, busType, opType, logDesc);
		}
		return result;
	}

	/**
	 * 功能描述：拦截器中写日志共通方法
	 * 
	 * @param logType
	 *            日志类型代码
	 * @param busType
	 *            操作模块代码
	 * @param opType
	 *            操作类型代码
	 * @param logDesc
	 *            日志详细信息
	 */
	private void insertLogInLogAspect(String logType, String busType, String opType, String logDesc) {

		// 当前系统登陆用户
//		SysUser sysUser = ThreadDataUtils.getThreadUser();

		// 调用日志保存服务,记录old entity 和 new entity
//		LogInfoBean record = new LogInfoBean();
//
//		record.setBranch(sysUser.getOrgid());
//		record.setEmployeeId(sysUser.getUsername());
//		record.setIpAddress(ThreadDataUtils.getThreadIp());
//		record.setOpDate(new Date());
//		record.setOperator(sysUser.getUsername() + "（职员名称：" + sysUser.getUserrealname() + ")");
//		record.setOperatorId(sysUser.getUsername());
//		record.setVaruserdepid(sysUser.getDepartcode());
//		// 日志类型不同，存储值的含义不同
//		record.setOpType(opType);
//		record.setBusType(this.translateBizCodeToBizName(busType));
//		record.setLogDesc(logDesc);
		// 根据logType不同，插入日志到不同的表
//		sysLogService.insertSysOrBizLog(record, logType);
	}

	/**
	 * 功能描述：将[操作模块]代码转换成实际模块名称
	 * 
	 * @param bizCode
	 *            操作模块代码
	 * @return 操作模块名称
	 */
	private String translateBizCodeToBizName(String bizCode) {
		return bizCode;
	}
}
