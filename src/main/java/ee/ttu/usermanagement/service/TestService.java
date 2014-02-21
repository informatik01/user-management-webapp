package ee.ttu.usermanagement.service;

public class TestService {

	private static final String name = "This is a test service.";

	public TestService() {
		System.out.println("TestService.TestService()!!!");
	}
	
	public String getName() {
		return name;
	}

//	public void setName(String name) {
//		this.name = name;
//	}
	
}
