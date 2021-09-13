## Spring MVC Film C.R.U.D.

### Week 8 Team Project for Skill Distillery

### Overview
Full stack project that implements CRUD database operations for a mock film database. Users are able to search for, add, edit, and delete information regarding a database of films.

Users are able to visit our web page where they are presented with three choices. The first being search a film by its Id in the database. If a film is found, user will be shown some details, and presented with a home, update film data or delete film links. If the film is not available, the user is made aware of this.

Back at home the second menu choice is to add a film to the existing database allowing the user to input all the new films details and will be presented with a confirmation page if all info gets filled out, if they miss or skip one they should be presented with a message but we ended up getting a page 400 error.

The last option on the home page is to search for a film using a keyword. If more than one film is found with that keyword, users are able to choose one of them and display their details along with category and actors in the film. This also has a redirect button to home.

### Technologies Used

* Java
* Spring MVC
* MySQL
* MAMP

### Lessons Learned
* Communication is key. Our group was having issues pulling and pushing and had several merge conflicts. We were able to
set up a zoom conference and work together but when we weren't in constant communication things had conflicts. Building the different parts such as the controller, the DAO, and the various jsps was not difficult but mapping them properly was.

* Ran into various issues with mapping and connecting files to each other, looking back at the previous jsp, html, and corresponding method in controller was helpful.

* ended up running into an issue with adding a film when any of the slots are null the page loads a status 400 Bad Request and currently unsure of how to fix this, been looking at it all night.

* Sometimes the information would not be updated when pushing and pulling then running code. Needed to remember to refresh project as well as in gradle just to be sure everything was up to date.
