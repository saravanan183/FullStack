package StudentSpringBootWithoutDB.StudentSpringBootWithoutDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	
	public Map<Long, Student> studentMap=new HashMap<Long,Student>();
	

	@RequestMapping(value = "/students")
	public String students() {
		return "hi";
	}
	
	@RequestMapping(value = "/allStudents", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> fetchStudents() {

		List<Student> students = new ArrayList<Student>();
		for (Map.Entry<Long, Student> entry : studentMap.entrySet()) {
			students.add(entry.getValue());
		}
		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addStudent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> addStudent(@RequestBody Student student) {
		
		studentMap.put(student.getStdId(), student);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	

	@RequestMapping(value = "/deleteStudent/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<List<Student>> deleteStudent(@PathVariable("id") Long id) {
		
		List<Student> students = new ArrayList<Student>();
		studentMap.remove(id);
		for (Map.Entry<Long, Student> entry : studentMap.entrySet()) {
			students.add(entry.getValue());
		}

		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/modifyStudent/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> modifyStudent(@PathVariable("id") Long id, @RequestBody Student student) {

		studentMap.put(id, student);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
