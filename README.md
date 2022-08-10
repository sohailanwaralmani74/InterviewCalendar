<h1>Interviewer Claendar</h1>

<h3>Technologies Used</h1>

Spring boot , JPA as an ORM, Mockito, JUnit for test cases.

Heroku for deployment of application and DB deployment.

Postgresql as DB.

Open API for Testing and documentation of API

ModelMapper dependency for Mapping between DTO and Entity objects.

<h2>swagger URLs </h2>

http://localhost:8080/swagger-ui/index.html

http://calendar-pp.herokuapp.com/swagger-ui.html

<h2>Functionalities</h2>

User Operations
	
Calendar Operations
	
For Payload please refer open API docs at above given urls 
		
Custom business exception is thrown in case of any failure.

refer exception packages for classes of Custom exception handler

#Below Method is used for available time slot comparision

	/**
	 * @Method fillAvailableSlots 
	 * 
	 * private method 
	 * 
	 * get below parameters as input
	 * 
	 * availableTimeSlots (an empty list)
	 * UserTimeSlot time slot object for candidate
	 * interviewerTimeSlot list for interviewers time slots
	 * 
	 * get interview start time and on date from candidate slot object and will compare if 
	 * any is available in interviewers and add to to available time slot otherwise will ignore it.
	 * 
	 * @return availableTimeSlots
	 * 
	 * */
	private List<AvailableTimeSlot> fillAvailableSlots(List<AvailableTimeSlot> availableTimeSlots,
			UserTimeSlot slot, List<UserTimeSlot> interviewerTimeSlot) {

		
			AvailableTimeSlot availableTimeSlot = new AvailableTimeSlot();
			availableTimeSlot.setFromTime(slot.getFromTime());
			availableTimeSlot.setOnDate(slot.getOnDate());
			availableTimeSlot.setCandidate(slot.getUser());
			availableTimeSlot.setToTime(slot.getToTime());
			List<ApplicationUser> interviewers = new ArrayList<>();
			for(UserTimeSlot slot2:interviewerTimeSlot) {
				if(slot.getFromTime().equals(slot2.getFromTime()) && slot.getOnDate().equals(slot2.getOnDate())) {
					interviewers.add(slot2.getUser());
				}
				
			}
			availableTimeSlot.setInterviewers(interviewers);
			
			availableTimeSlots.add(availableTimeSlot);
		return availableTimeSlots;

	}

<h1>Test Class</h1>

<h2>Mock are written for below operation</h2>

User Operations

Calendar Operations

<h2>Classes</h2>

ApplicationUserTest

UserTimeSlotTest


