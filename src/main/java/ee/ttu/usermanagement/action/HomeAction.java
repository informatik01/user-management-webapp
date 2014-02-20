package ee.ttu.usermanagement.action;

public class HomeAction {

	private String name = "John Dow";
	
	public String getName() {
		return name;
	}

	public String execute() {
		return "SUCCESS";
	}
}
