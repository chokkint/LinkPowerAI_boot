package com.ai.ringball.framework.db.mongo;

import com.ai.ringball.framework.utility.common.JsonStrToMap;
import com.ai.ringball.framework.utility.common.PageUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 实现Dao接口
 */
public class MongoDaoImpl implements MongoDao {

	@Override
	public Map<String, Object> queryByID(MongoDatabase db, String table, Object Id, BasicDBObject fields) {
		MongoCollection<Document> collection = db.getCollection(table);
		BasicDBObject query = new BasicDBObject("_id", Id);

		// DBObject接口和BasicDBObject对象：表示一个具体的记录，BasicDBObject实现了DBObject，是key-value的数据结构，用起来和HashMap是基本一致的。
		FindIterable<Document> iterable = collection.find(query).projection(fields);

		Map<String, Object> jsonStrToMap = null;
		MongoCursor<Document> cursor = iterable.iterator();
		int count = 0;
		while (cursor.hasNext()) {
			count++;
			Document document = cursor.next();
			String jsonString = document.toJson();
			jsonStrToMap = JsonStrToMap.jsonStrToMap(jsonString);// 这里用到我自己写的方法,主要是包json字符串转换成map格式,为后面做准备,方法放在后面
		}
		System.out.println("检索记录(_id:" + Id + ")完成,共检索到[" + count + "]条记录 !");

		return jsonStrToMap;
	}

	@Override
	public List<Map<String, Object>> queryByDoc(MongoDatabase db, String table, BasicDBObject doc, BasicDBObject sort, BasicDBObject fields) {

		MongoCollection<Document> collection = db.getCollection(table);
		if (sort == null) {
			// 1,表示正序； －1,表示倒序
			sort = new BasicDBObject("_id", 1);
		}
		FindIterable<Document> iterable = collection.find(doc).sort(sort).projection(fields);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		MongoCursor<Document> cursor = iterable.iterator();
		int count = 0;
		while (cursor.hasNext()) {
			count++;
			Document document = cursor.next();
			String jsonString = document.toJson();
			Map<String, Object> jsonStrToMap = JsonStrToMap.jsonStrToMap(jsonString);
			list.add(jsonStrToMap);
		}
		System.out.println("检索完毕,共检索到[" + count + "]条记录 !");
		return list;
	}

	@Override
	public List<Map<String, Object>> queryAll(MongoDatabase db, String table, BasicDBObject query, BasicDBObject sort, BasicDBObject fields) {

		MongoCollection<Document> collection = db.getCollection(table);

		if (sort == null) {
			// 1,表示正序； －1,表示倒序
			sort = new BasicDBObject("_id", 1);
		}
		FindIterable<Document> iterable = collection.find(query).sort(sort).projection(fields);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		MongoCursor<Document> cursor = iterable.iterator();
		int i = 0;
		while (cursor.hasNext()) {
			i++;
			Document document = cursor.next();
			String jsonString = document.toJson();
			Map<String, Object> jsonStrToMap = JsonStrToMap.jsonStrToMap(jsonString);
			list.add(jsonStrToMap);
		}
		System.out.println("检索完毕,共查询到" + i + "条记录!");
		return list;
	}

	@Override
	public List<Map<String, Object>> queryAllByPage(MongoDatabase db, String table, BasicDBObject query, BasicDBObject sort, BasicDBObject fields, PageUtils page) {

		MongoCollection<Document> collection = db.getCollection(table);
		if (sort == null) {
			// 1,表示正序； －1,表示倒序
			sort = new BasicDBObject("_id", 1);
		}
		FindIterable<Document> iterable = collection.find(query).sort(sort).projection(fields).skip(page.getPageStartNum()).limit(page.getPageSize());

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		MongoCursor<Document> cursor = iterable.iterator();
		int count = 0;
		while (cursor.hasNext()) {
			count++;
			Document document = cursor.next();
			String jsonString = document.toJson();
			Map<String, Object> jsonStrToMap = JsonStrToMap.jsonStrToMap(jsonString);
			list.add(jsonStrToMap);
		}
		System.out.println("分页检索完毕,共检索到[" + count + "]条记录 !");
		return list;
	}

	@Override
	public int count(DB db, String table, BasicDBObject query, BasicDBObject sort, BasicDBObject fields) {
		DBCollection dbColl = db.getCollection(table);
		int count = dbColl.find(query).sort(sort).count();
		System.out.println("检索完毕,共检索到[" + count + "]条记录!");
		return count;
	}

	@Override
	public boolean insertOne(MongoDatabase db, String table, Document document) {
		MongoCollection<Document> collection = db.getCollection(table);
		collection.insertOne(document);
		long count = collection.count(document);

		System.out.println("count: " + count);
		if (count == 1) {
			System.out.println("文档插入成功!");
			return true;
		} else {
			System.out.println("文档插入失败!");
			return false;
		}

	}

	@Override
	public boolean insertMany(MongoDatabase db, String table, List<Document> documents) {

		MongoCollection<Document> collection = db.getCollection(table);
		long preCount = collection.count();
		collection.insertMany(documents);
		long nowCount = collection.count();
		if ((nowCount - preCount) == documents.size()) {
			System.out.println("文档插入多个成功,共插入[" + documents.size() + "]条记录!");
			return true;
		} else {
			System.out.println("文档插入多个失败,应该插入[" + documents.size() + "]条记录,实际插入[" + (nowCount - preCount) + "]条记录!");
			return false;
		}
	}

	@Override
	public boolean deleteMany(MongoDatabase db, String table, BasicDBObject whereDoc) {
		MongoCollection<Document> collection = db.getCollection(table);
		DeleteResult deleteManyResult = collection.deleteMany(whereDoc);
		long deletedCount = deleteManyResult.getDeletedCount();
		if (deletedCount > 0) {
			System.out.println("删除多个记录成功,共删除[" + deletedCount + "]条记录!");
			return true;
		} else {
			System.out.println("删除多个文档失败,共删除[0]条记录!");
			return false;
		}
	}

	@Override
	public boolean deleteOne(MongoDatabase db, String table, BasicDBObject document) {
		MongoCollection<Document> collection = db.getCollection(table);
		DeleteResult deleteOneResult = collection.deleteOne(document);
		long deletedCount = deleteOneResult.getDeletedCount();
		if (deletedCount == 1) {
			System.out.println("删除记录成功,共删除[1]条记录!");
			return true;
		} else {
			System.out.println("删除记录失败,共删除[0]条记录!");
			return false;
		}
	}

	@Override
	public boolean updateMany(MongoDatabase db, String table, BasicDBObject whereDoc, BasicDBObject updateDoc) {

		MongoCollection<Document> collection = db.getCollection(table);
		UpdateResult updateManyResult = collection.updateMany(whereDoc, new Document("$set", updateDoc));
		long modifiedCount = updateManyResult.getModifiedCount();

		if (modifiedCount > 0) {
			System.out.println("文档更新成功,共更新[" + modifiedCount + "]条记录!");
			return true;
		} else {
			System.out.println("文档更新失败,共更新[0]条记录!");
			return false;
		}
	}

	@Override
	public boolean updateOne(MongoDatabase db, String table, BasicDBObject whereDoc, BasicDBObject updateDoc) {

		MongoCollection<Document> collection = db.getCollection(table);
		UpdateResult updateOneResult = collection.updateOne(whereDoc, new Document("$set", updateDoc));
		long modifiedCount = updateOneResult.getModifiedCount();

		if (modifiedCount == 1) {
			System.out.println("文档更新成功,共更新[1]条记录!");
			return true;
		} else {
			System.out.println("文档更新失败,共更新[0]条记录!");
			return false;
		}
	}

	@Override
	public boolean copyCollections(MongoDatabase db, String srcTable, String tarTable) {
		
		//创建目标Collection
		this.createCollection(db, tarTable);
		
		//获取源Collection所有数据
		MongoCollection<Document> collection = db.getCollection(srcTable);
		FindIterable<Document> iterable = collection.find();

		MongoCursor<Document> cursor = iterable.iterator();
		int count = 0;
		while (cursor.hasNext()) {
			count++;
			Document document = cursor.next();
			this.insertOne(db, tarTable, document);
		}
		System.out.println("Collection复制完毕,共复制[" + count + "]条记录 !");
		return true;
	}

	/**
	 * 功能说明:创建MongoDB指定表
	 * 
	 * @param db
	 *            指定的MongoDB数据库对象
	 * @param table
	 *            指定的MongoDB表对象
	 */
	public void createCollection(MongoDatabase db, String table) {
		db.createCollection(table);
		System.out.println("集合[" + table + "]创建成功");
	}

	/**
	 * 功能说明:删除MongoDB指定表
	 * 
	 * @param db
	 *            指定的MongoDB数据库对象
	 * @param tableName
	 *            指定的MongoDB表对象
	 */
	public void dropCollection(MongoDatabase db, String table) {
		db.getCollection(table).drop();
		System.out.println("集合[" + table + "]删除成功!");
	}

	/**
	 * 功能说明:遍历打印迭代器FindIterable<Document>
	 * 
	 * @param iterable
	 *            需要遍历的数据
	 */
	public void printFindIterable(FindIterable<Document> iterable) {
		MongoCursor<Document> cursor = iterable.iterator();
		while (cursor.hasNext()) {
			Document document = cursor.next();
			System.out.println(document.toJson());
		}
		cursor.close();
	}

	/**
	 * 功能说明:遍历打印List信息
	 * 
	 * @param list
	 *            需要遍历的数据
	 */
	public void printListMap(List<Map<String, Object>> list) {
		for (Map<String, Object> map : list) {
			System.out.println("-----:---" + map);
		}
	}
}