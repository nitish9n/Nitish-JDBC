package learning_JDBC.entity;

public class Students {
	private int id;
	private String name, gender, city;
	public int getId() {
		return id;
	}
	
	
	
	public Students(int id, String name, String gender, String city) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.city = city;
	}

	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public void setId(int id) {
		this.id = id;
	}
	
	public int getId(int id) {
		return id;
	}
	
	



	@Override
	public String toString() {
		return "Students [id=" + id + ", name=" + name + ", gender=" + gender + ", city=" + city + "]";
	}
	
}
