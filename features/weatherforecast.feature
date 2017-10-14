Feature: Call 5 day/3 hour forecast data by CityName 

Scenario Outline: 5 day weather forecast displayed when entered city name
Given I am on the http://localhost:3000/ page
When I enter the city name as <city> and click Enter
Then I should get 5 day weather forecast for <city>.
Examples:
| city     |
| Aberdeen |

Scenario Outline: Daily forecast has a summary of 
         Most dominant (or current) condition
         Most dominant (or current) wind speed and direction
         Aggregate rainfall
         Minimum and maximum temperatures
Given I am on the weather forecast page for a <city>
When I see the daily forecast for that <city>
Then I should see summarised three hour data.
Examples:
| city		 |
| Edinburgh  |

Scenario Outline: 3 hourly forecast displayed when selected a day 
Given I am on the forecast page
And I see the five day weather forecast for <city>
When I click day <daynum>
Then I should get <hourspan> hourly weather forecast for day <daynum>.
Examples:
| city		| | daynum | | hourspan |
| Dundee  | | 1 |      | 3 |   
