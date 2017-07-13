package StudentSpringBootWithoutDB.StudentSpringBootWithoutDB;

public class Student {
	
	
	private Long stdId;

	private String name;
	
		
	public Student() {	
	}

	

	public Long getStdId() {
		return stdId;
	}



	public void setStdId(Long stdId) {
		this.stdId = stdId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String toString() {
		return "Student [stdId=" + stdId + ", name=" + name + ", name=" + name + "]";
	}
	
	
	public Student(Long stdId, String name) {
		this.name =name;
		this.stdId =stdId;
	}
	

}
