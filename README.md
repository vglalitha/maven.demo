# Rest_API

A RESTful API is an architectural style for an application program interface (API) that uses HTTP requests to access and use data. 
That data can be used to GET, PUT, POST and DELETE data types, which refers to the reading, 
updating, creating and deleting of operations concerning resources.
GET to retrieve a resource;
POST to create that resource;

Dropwizard :
Dropwizard straddles the line between being a library and a framework. Its goal is to provide performant,
reliable implementations of everything a production-ready web application needs. Because this functionality is extracted into a reusable library,
your application remains lean and focused, reducing both time-to-market and maintenance burdens.
 
GOAL: Turn the application into an application that serves RESTful API endpoints.

Created new git branch.

Added dropwizard as a dependency.

Created configuration and necessary files to configure dropwizard application to start up. 

Post tweet - Created a POST route to the following url http://localhost:8080/api/1.0/twitter/tweet. 
This route should take a single post parameter 'message' which will represent the message of the tweet. 
When called properly, this route will post the message to the Twitter account.

Get timeline - Created a GET route to the following url http://localhost:8080/api/1.0/twitter/timeline. 
This route will retrieve a list of latest tweets from the home timeline.
