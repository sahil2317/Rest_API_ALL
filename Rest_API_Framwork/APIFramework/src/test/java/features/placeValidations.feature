Feature: Validating Place API's
@AddPlace
Scenario Outline: Verify if place is being Successfully added using AddPlaceAPI

	Given Add Place Payload with "<name>" "<language>" "<address>" "<Phone_number>"
	When User calls "AddPlaceAPI" with "POST" http request
	Then The API call got success with status code 200
	And  "status" in response body is "OK" 
	And  "scope" in response body is "APP" 
	And  verify place_Id created maps to "<name>" using "getPlaceAPI"
	
	Examples:
	   |name  | language | address        | Phone_number |
	   |Sahil | Marathi  |Erandwana,Pune  | 7798921322   |
	   |Bhakti| Hindi    |Satara Road,Pune| 8956892344   |
	
@DeletePlace
Scenario: Verify if Delete Place functionality is working

	Given DeletePlace Payload
	When  User calls "deletePlaceAPI" with "POST" http request  
	Then  The API call got success with status code 200
	And  "status" in response body is "OK" 