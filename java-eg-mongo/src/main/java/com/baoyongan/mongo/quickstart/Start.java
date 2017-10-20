package com.baoyongan.mongo.quickstart;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bqct_bya on 2017/9/21.
 */
public class Start {

    public static void main(String[] args) {
        // 1、创建一个连接
        /**
         * 使用mongoclient 创建连接MongoDB实例
         * mongoclient 代表一个连接池，对于多个线程你只需要一个MongoClient实例
         *
         */
        // 以下有5种方式创建连接到本机数据库 “mydb”ongoDB会为你创建它

        //MongoClient mongoClient=new MongoClient();  // default ip 127.0.0.1 和default port 27017
        //MongoClient mongoClient=new MongoClient("127.0.0.1");// 指定ip,默认端口
        //MongoClient mongoClient=new MongoClient("127.0.0.1",27017);// 指定ip,指定port
        // 使用指定的连接URI
        MongoClientURI uri=new MongoClientURI("mongdb://127.0.0.1:27017"); // 连接字符串主要遵循RFC 3986,更多连接字符串，请查看 http://docs.mongodb.org/manual/reference/connection-string
        MongoClient mongoClient=new MongoClient(uri);

        // 2、访问一个数据库
        /**
         * 如果该数据库不存在，MongoDB会在第一次存储数据的时候创建该库
         */
        MongoDatabase db=mongoClient.getDatabase("test");
        // 3、访问一个集合
        /**
         * 类似于访问数据库，该集合不存在的时候，会在第一次存储数据的时候创建
         */
        MongoCollection<Document> collection=db.getCollection("testColl");
        // 4、创建一个Document
        // eg:{"name":"MongoDB","type":"database","count":1,"versions":["v3.2","v3.0","v2.6"],"info":{"x":203,"y":102}}
        Document doc=new Document("name","MongoDB")
                .append("type","database")
                .append("count",1)
                .append("versions", Arrays.asList("v3.2","v3.0","v2.6"))
                .append("info",new Document("x",203).append("y",102));

        // NOTE: BSON类型的数组对应于Java类型的java.util.List。
        // 5、插入一个Document
        collection.insertOne(doc);
        // NOTE：没有_id字段的话会自动添加
        // 6、插入多个
        List<Document> docs=new ArrayList<Document>();
        for (int i = 0; i <20 ; i++) {
            docs.add(new Document("i",i));
        }
        collection.insertMany(docs);
        // 7、计算集合的总数
        System.out.println("collection counts=>"+collection.count());
        // 8、查询集合
        // 8.1 查询结果集的第一个
        Document first=collection.find().first();
        System.out.println(first.toJson());
        // 8.2 遍历整个结果集
        MongoCursor<Document> cursor= collection.find().iterator();
        try {
            while (cursor.hasNext())
                System.out.println(cursor.next().toJson());
        }finally {
            cursor.close();
        }
        // 以下循环也可以，但是不推荐
        for(Document cur:collection.find())
            System.out.println(cur.toJson());
        // 8.3 指定查询过滤条件
        // 8.3.1 查询满足条件的一个
        Document doc1=collection.find(eq("i",19)).first();
        System.out.println(doc1.toJson());
        // 8.3.2 查询满足条件的所有
        Block<Document> printBlock= new Block<Document>() {
            @Override
            public void apply(Document document) {
                System.out.println(document.toJson());
            }
        };
        collection.find(lt("i",15)).forEach(printBlock);
        collection.find(and(lt("i",15),gt("i",10))).forEach(printBlock);
        //


    }

}
