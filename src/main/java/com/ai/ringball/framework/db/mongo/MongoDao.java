package com.ai.ringball.framework.db.mongo;

import com.ai.ringball.framework.utility.common.PageUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;
import java.util.Map;

/**
 * Dao 层接口
 */
public interface MongoDao {

	/**
	 * 功能说明:通过ObjectId获取记录信息
	 * 
	 * @param db
	 *            指定的MongoDB数据库对象
	 * @param table
	 *            指定的MongoDB表对象
	 * @param Id
	 *            指定记录的ObjectId
	 * @return 查询到的记录信息
	 */
	public Map<String, Object> queryByID(MongoDatabase db, String table, Object Id, BasicDBObject fields);

	/**
	 * 功能说明:根据检索条件doc来检索需要的记录信息列表(所有查询字段精确查询)
	 * 
	 * @param db
	 *            指定的MongoDB数据库对象
	 * @param table
	 *            指定的MongoDB表对象
	 * @param doc
	 *            检索条件
	 * @param sort
	 *            排序条件 1,表示正序； －1,表示倒序
	 * @return 查询到的记录信息列表
	 */
	public List<Map<String, Object>> queryByDoc(MongoDatabase db, String table, BasicDBObject doc, BasicDBObject sort, BasicDBObject fields);

	/**
	 * 功能说明:根据检索条件doc来检索需要的记录信息列表(所有查询字段模糊查询)
	 * 
	 * @param db
	 *            指定的MongoDB数据库对象
	 * @param table
	 *            指定的MongoDB表对象
	 * @param query
	 *            检索条件
	 * @param sort
	 *            排序条件 1,表示正序； －1,表示倒序
	 * @return 查询到的记录信息列表
	 */
	public List<Map<String, Object>> queryAll(MongoDatabase db, String table, BasicDBObject query, BasicDBObject sort, BasicDBObject fields);

	/**
	 * 功能说明:分页检索全部并返回迭代器(默认_id字段排序,所有查询字段模糊查询)
	 * 
	 * @param db
	 *            指定的MongoDB数据库对象
	 * @param table
	 *            指定的MongoDB表对象
	 * @param query
	 *            查询条件列表
	 * @param sort
	 *            排序条件 1,表示正序； －1,表示倒序
	 * @param page
	 *            分页信息对象
	 * @return 查询到的记录信息列表
	 */
	public List<Map<String, Object>> queryAllByPage(MongoDatabase db, String table, BasicDBObject query,
                                                    BasicDBObject sort, BasicDBObject fields, PageUtils page);

	/**
	 * 功能说明:统计当前查询条件下所有记录条数
	 * 
	 * @param db
	 *            指定的MongoDB数据库对象
	 * @param table
	 *            指定的MongoDB表对象
	 * @param query
	 *            查询条件列表
	 * @param sort
	 *            排序条件 1,表示正序； －1,表示倒序
	 * @return 查询到的记录条数
	 */
	public int count(DB db, String table, BasicDBObject query, BasicDBObject sort, BasicDBObject fields);

	/**
	 * 功能说明:向表中插入单条数据
	 * 
	 * @param db
	 *            指定的MongoDB数据库对象
	 * @param table
	 *            指定的MongoDB表对象
	 * @param doc
	 *            需要插入的数据对象
	 * @return true:插入成功 false:插入失败
	 */
	public boolean insertOne(MongoDatabase db, String table, Document doc);

	/**
	 * 功能说明:向表中插入多条数据
	 * 
	 * @param db
	 *            指定的MongoDB数据库对象
	 * @param table
	 *            指定的MongoDB表对象
	 * @param documents
	 *            需要插入的数据对象list
	 * @return true:插入成功 false:插入失败
	 */
	public boolean insertMany(MongoDatabase db, String table, List<Document> documents);

	/**
	 * 功能说明:根据条件删除满足查询条件的所有记录(如果whereDoc为空将删除所有数据)
	 * 
	 * @param db
	 *            指定的MongoDB数据库对象
	 * @param table
	 *            指定的MongoDB表对象
	 * @param whereDoc
	 *            删除条件
	 * @return true:删除成功 false:删除失败
	 */
	public boolean deleteMany(MongoDatabase db, String table, BasicDBObject whereDoc);

	/**
	 * 功能说明:根据条件删除满足查询条件的所有记录
	 * 
	 * @param db
	 *            指定的MongoDB数据库对象
	 * @param table
	 *            指定的MongoDB表对象
	 * @param doc
	 *            删除条件,至少包含_id或者业务主键
	 * @return true:删除成功 false:删除失败
	 */
	public boolean deleteOne(MongoDatabase db, String table, BasicDBObject doc);

	/**
	 * 功能说明:根据条件更新满足查询条件的所有记录
	 * 
	 * @param db
	 *            指定的MongoDB数据库对象
	 * @param table
	 *            指定的MongoDB表对象
	 * @param oldDoc
	 * @param newDoc
	 * @return true:更新成功 false:更新失败
	 */
	public boolean updateMany(MongoDatabase db, String table, BasicDBObject oldDoc, BasicDBObject newDoc);

	/**
	 * 功能说明:根据条件更新满足查询条件的所有记录
	 * 
	 * @param db
	 *            指定的MongoDB数据库对象
	 * @param table
	 *            指定的MongoDB表对象
	 * @param whereDoc
	 * @param updateDoc
	 * @return true:更新成功 false:更新失败
	 */
	public boolean updateOne(MongoDatabase db, String table, BasicDBObject whereDoc, BasicDBObject updateDoc);
	
	/**
	 * 功能说明:复制Collection
	 * 
	 * @param db 指定的MongoDB数据库对象
	 * @param srcTable 源MongoDB表对象
	 * @param tarTable 目标MongoDB表对象
	 * @return true:操作成功 false:操作失败
	 */
	public boolean copyCollections(MongoDatabase db, String srcTable, String tarTable);

}