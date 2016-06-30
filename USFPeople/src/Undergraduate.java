
public class Undergraduate extends Student {
	
	
	private String year;
	

	public Undergraduate(String name, long id, double gpa,String year) {
		super(name,id,gpa);
		this.year = year;				
	}

}
