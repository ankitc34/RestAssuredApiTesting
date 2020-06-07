Feature: Validating Place Api's 
@AddPlace
Scenario Outline: : Verify if place is being succesfully added using AddPlaceAPI 
	Given Add place payload with "<name>" "<language>" "<address>" 
	When user calls "AddPlaceAPI" with "Post" HTTP request 
	Then the API call got success with status code 200 
	And "status" response body is "OK" 
	And "scope" response body is "APP" 
	And verify place_id created maps to "<name>" using "GetPlaceAPI" 
	
	Examples: 
		|name|language|address|
		|Ankit|English|Chowk Faizabad|
		|Komal|English|Chowk Lucknow|
		
@DeletePlace		
Scenario: Verify if delete place functionality is working 
	Given DeletePlace payload 
	When user calls "DeletePlaceAPI" with "Post" HTTP request 
	Then the API call got success with status code 200 
	And "status" response body is "OK" 