package com.cts.angular.service;

import com.cts.angular.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
//import org.json.JSONException;
//import org.json.JSONObject;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Path("/student")
public class StudentService {
	@POST
	@Path("/addStudent")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addStudent(String jsonString) {
		try {
			System.out.println(jsonString);

			JSONParser parser = new JSONParser();
			JSONObject student = (JSONObject) parser.parse(jsonString);

			// To connect to mongodb server
			MongoClient mongoClient = new MongoClient("localhost", 27017);

			// Now connect to your databases
			DB db = mongoClient.getDB("studentDB");
			System.out.println("Connect to database successfully");

			// boolean auth = db.authenticate(myUserName, myPassword);
			// System.out.println("Authentication: "+auth);
			DBCollection coll = db.getCollection("student");
			System.out.println("Collection mycol selected successfully");
			System.out.println("Studentobject" + student);
			BasicDBObject doc = new BasicDBObject("name", student.get("name"))
					.append("id", (long) student.get("id"))
					.append("age", (long) student.get("age"));

			coll.insert(doc);
			System.out.println("Document inserted successfully");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		String result = "";
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("/updateStudent")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateStudent(String jsonString) {
		try {
			System.out.println(jsonString);

			JSONParser parser = new JSONParser();
			JSONObject student = (JSONObject) parser.parse(jsonString);

			// To connect to mongodb server
			MongoClient mongoClient = new MongoClient("localhost", 27017);

			// Now connect to your databases
			DB db = mongoClient.getDB("studentDB");
			System.out.println("Connect to database successfully");

			DBCollection coll = db.getCollection("student");
			System.out.println("Collection mycol selected successfully");
			System.out.println("Studentobject" + student);
			BasicDBObject newObj = new BasicDBObject("name", student.get("name")).append("id", student.get("id"))
					.append("age", student.get("age"));

			BasicDBObject doc1 = new BasicDBObject("id", student.get("id"));
			DBObject oldObj = coll.findOne(doc1);
			System.out.println(oldObj.get("name"));

			coll.update(oldObj, newObj);
			System.out.println("Document inserted successfully");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		String result = "";
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/deleteStudent/{id}")
	@Produces("application/json")
	public Response deleteSudent(@PathParam("id") Integer id) throws Exception {
		try {
			// To connect to mongodb server
			MongoClient mongoClient = new MongoClient("localhost", 27017);

			// Now connect to your databases
			DB db = mongoClient.getDB("studentDB");
			System.out.println("Connect to database successfully");

			DBCollection coll = db.getCollection("student");
			System.out.println("Collection mycol selected successfully");
			System.out.println("id" + id);
			BasicDBObject doc = new BasicDBObject("id", id);
			// append("id",getStudentById).
			// append("age", student.getAge());

			coll.remove(doc);
			System.out.println("Document deleted successfully");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

		String result = "";
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/getStudentById/{id}")
	@Produces("application/json")
	public Response getStudentById(@PathParam("id") Integer id) throws Exception {

		String result = "";

		try {

			// To connect to mongodb server
			MongoClient mongoClient = new MongoClient("localhost", 27017);

			// Now connect to your databases
			DB db = mongoClient.getDB("studentDB");
			System.out.println("Connect to database successfully");

			// boolean auth = db.authenticate(myUserName, myPassword);
			// System.out.println("Authentication: "+auth);

			DBCollection coll = db.getCollection("student");
			System.out.println("Collection mycol selected successfully");

			DBCursor cursor = coll.find();
			int i = 1;
			JSONObject jsonObject = new JSONObject();
			while (cursor.hasNext()) {

				System.out.println("Inserted Document: " + id);
				// System.out.println(cursor.next());
				DBObject obj = cursor.next();
				System.out.println("Inserted Document: " + id + "===" + obj.get("id"));
				long objId = (long) obj.get("id");
				if (objId == (long) id) {
					System.out.println("Condition satisfied");
					jsonObject.put("name", obj.get("name"));
					jsonObject.put("id", obj.get("id"));
					jsonObject.put("age", obj.get("age"));
				}

				i++;
			}
			result = jsonObject + "";

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

		System.out.println("Inserted Document: " + result);
		return Response.status(200).entity(result).build();

	}

	@GET
	@Path("/getAllStudents")
	@Produces("application/json")
	public Response getAllStudents() throws Exception {
		String result = "";

		try {

			// To connect to mongodb server
			MongoClient mongoClient = new MongoClient("localhost", 27017);

			// Now connect to your databases
			DB db = mongoClient.getDB("studentDB");
			System.out.println("Connect to database successfully");

			// boolean auth = db.authenticate(myUserName, myPassword);
			// System.out.println("Authentication: "+auth);

			DBCollection coll = db.getCollection("student");
			System.out.println("Collection mycol selected successfully");

			DBCursor cursor = coll.find();
			int i = 1;
			List<JSONObject> list = new ArrayList<JSONObject>();

			while (cursor.hasNext()) {
				System.out.println("Inserted Document: " + i);
				// System.out.println(cursor.next());
				DBObject obj = cursor.next();
				JSONObject jsonObject = new JSONObject();

				jsonObject.put("name", obj.get("name"));
				jsonObject.put("id", obj.get("id"));
				jsonObject.put("age", obj.get("age"));

				list.add(jsonObject);
				i++;
			}
			result = list + "";

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

		return Response.status(200).entity(result).build();
	}

	public <T> T getBodyAsObject(String jsonString, Class<T> objectClass) {
		T result = null;

		if (jsonString != null) {
			try {
				result = new ObjectMapper().readValue(jsonString, objectClass);
			} catch (Exception e) {
				System.out.println("Error serializing message string: " + e);
			}
		}

		return result;
	}

}
