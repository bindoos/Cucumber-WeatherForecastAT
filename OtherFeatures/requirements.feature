Feature: Call 5 day/3 hour forecast data by CityName 

Scenario Outline: 5 day weather forecast displayed when entered city name
	Given I am on the weather forecast5 page
	When I enter the city name as <city> and click Enter
	Then I should get five day weather forecast for <city>.
	Examples:
	| city     |
	| Aberdeen |
	| Dundee   |
	| Edinburgh|
	| Glasgow  |
	| Perth    |
	| Sterling |
	
	

Scenario: 5 day weather forecast starting from today is displayed 
	Given I enter the city in the forecast5 page and click enter
	When the 5 day weather forecast is displayed
	Then I should see 5 day weather forecast starting from today.
	
	

Scenario: 3 hourly forecast displayed when selected a day 
	Given I enter the city in the forecast5 page and click enter
	And I see the 5 day weather forecast
	When I select a day
	Then I should get 3 hour weather forecast for that day.
	
	
Scenario: Hide 3 hourly forecast when day is selected again
	Given I see the 5 day weather forecast 
	And I select a day
	When I select that day again 
	Then the 3 hourly forecast display should be hidden.



Scenario: Daily forecast has a summary of 3 hour data
  Given I see the 5 day weather forecast for a day
	When I see the daily forecast
	Then I should see a 3hour data summary 
	
	
	
Scenario: Daily forecast has a summary of 
         Most dominant (or current) condition
         Most dominant (or current) wind speed and direction
         Aggregate rainfall
         Minimum and maximum temperatures
	Given I see the 5 day weather forecast for a day
	And I see the daily forecast
	When I view the 3 hour summary data
	Then I should see only the dominant condition,dominant wind speed and direction ,aggregate rainfall and Minimum and max temperatures.


Scenario: weather forecast has all the values rounded down 
	Given I enter the city in the forecast5 page and click enter
	When I see the 5 day weather forecast
	Then I should see all the values rounded down.

