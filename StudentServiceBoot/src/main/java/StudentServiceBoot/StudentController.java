package StudentServiceBoot;



import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;



@RestController
@RequestMapping("/StudentServiceBoot")
public class StudentController {
	
	 
    @RequestMapping(value ="/example" , method = RequestMethod.GET)
    public String intro() {
    	
    	return "Seetha";
    }
    
    

    
    
    @RequestMapping(value ="/addStudent" , method = RequestMethod.POST , produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addStudent(String jsonString) {
		try {
			System.out.println(jsonString);

			JSONParser parser = new JSONParser();
			JSONObject student = (JSONObject) parser.parse(jsonString);

			MongoClient mongoClient = new MongoClient("localhost", 27017);

			DB db = mongoClient.getDB("studentDB");
			DBCollection coll = db.getCollection("student");
			BasicDBObject doc = new BasicDBObject("name", student.get("name"))
					.append("id", (Long) student.get("id"))
					.append("age", (Long) student.get("age"));

			coll.insert(doc);
	
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		String result = "";
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
 
    @RequestMapping(value ="/getAllStudents" , method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAllStudents() throws Exception {
		String result = "";

		try {

			MongoClient mongoClient = new MongoClient("localhost", 27017);

			DB db = mongoClient.getDB("studentDB");
			DBCollection coll = db.getCollection("student");
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

		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
    
    
  
    @RequestMapping(value ="/updateStudent" , method = RequestMethod.POST ,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String>  updateStudent(String jsonString) {
		try {
			System.out.println(jsonString);

			JSONParser parser = new JSONParser();
			JSONObject student = (JSONObject) parser.parse(jsonString);
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			DB db = mongoClient.getDB("studentDB");
			
			DBCollection coll = db.getCollection("student");
			BasicDBObject newObj = new BasicDBObject("name", student.get("name")).append("id", student.get("id"))
					.append("age", student.get("age"));

			BasicDBObject doc1 = new BasicDBObject("id", student.get("id"));
			DBObject oldObj = coll.findOne(doc1);
		

			coll.update(oldObj, newObj);
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		String result = "";
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}


	@RequestMapping(value ="/deleteStudent" , method = RequestMethod.GET  , produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteSudent(@RequestParam(value="id") Integer id) throws Exception {
		try {
		
			MongoClient mongoClient = new MongoClient("localhost", 27017);

			DB db = mongoClient.getDB("studentDB");
			DBCollection coll = db.getCollection("student");
			
			BasicDBObject doc = new BasicDBObject("id", id);
			coll.remove(doc);
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

		String result = "";
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	
	@RequestMapping(value ="/getStudentById" , method = RequestMethod.GET , produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getStudentById(@RequestParam(value="id") Integer id) throws Exception {
	String result = "";

		try {

			MongoClient mongoClient = new MongoClient("localhost", 27017);
			DB db = mongoClient.getDB("studentDB");
		

			DBCollection coll = db.getCollection("student");
		

			DBCursor cursor = coll.find();
			int i = 1;
			JSONObject jsonObject = new JSONObject();
			while (cursor.hasNext()) {

				DBObject obj = cursor.next();
				System.out.println("Inserted Document: " + id + "===" + obj.get("id"));
				long objId = (Long) obj.get("id");
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
		return new ResponseEntity<String>(result,HttpStatus.OK);

	}


}
