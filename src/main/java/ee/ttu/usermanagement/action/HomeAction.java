package ee.ttu.usermanagement.action;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import ee.ttu.usermanagement.service.TestService;

public class HomeAction {
	
	private static final Logger LOGGER = Logger.getLogger(HomeAction.class); 

	@Inject
	private TestService testService;
	
	private String name = "John Dow";
	
	public String getName() {
		return name + " (" + testService.getName() + ")";
	}

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	public String execute() {
		return "SUCCESS";
	}
}
