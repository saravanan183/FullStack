package assg.sdnt;

import java.util.HashMap;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentService {
	HashMap<String, String> studentDetails=new  HashMap<String,String>();

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@RequestParam(value="Id") String id,@RequestParam(value="Name") String name) {	       
		studentDetails.put(id, name);
		return "Added "+name;
	}

	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public HashMap<String, String> view() {
		return studentDetails;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam(value="Id") String id,@RequestParam(value="Name") String name) {
		if(studentDetails.containsKey(id) && studentDetails.containsValue(name)){
			studentDetails.remove(id);
		}else{
			return "No match found";
		}
		return "Removed "+id;
	}


	@RequestMapping(value = "/modifyStudent", method = RequestMethod.POST)
	public String modifyStudent(@RequestParam(value="Id") String id,@RequestParam(value="Name") String name) {

		if(id!=null && !id.isEmpty() && studentDetails.containsKey(id)){
			studentDetails.put(id, name);
		}else{
			return "No matching student Name or Id found to modify";
		}

		return "Student "+id+" modified";
	}
}
