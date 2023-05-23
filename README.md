# Programare_Avansata_Laborator

Autor : `GRĂJDEANU ALEXANDRU-CRISTIAN` FROM `2B2`

Activitatea la laborator+teme

## Laboratorul nr.1

Pus pe GitHub partea de COMPULSORY si HOMEWORK

## Laboratorul nr.2

Pus pe Github partea de COMPULSORY si HOMEWORK

### COMPULSORY (*solved*)

- Create an object-oriented model of the problem. You should have (at least) the following classes: Location, Road.
The location and road types will be implemented as enums. (*solved*)
- Each class should have appropriate constructors, getters and setters. (*solved*)
- The toString method form the Object class must be properly overridden for all the classes. (*solved*)
- Create and print on the screen various objects of the two classes. (*solved*)

### HOMEWORK

- Create a class that describes an instance of the problem. (*solved*)
- Override the Object.equals method for the Location and Road classes. The problem should not allow adding the same location or road twice. (*solved*)
- Instead of using an enum, create dedicated classes either for locations: cities, air ports, gas stations etc. or roads: highway, express, country, etc. Each concrete location class may have additional specific propertes (population, number of terminals, gas price, etc.) (*solved*)
- Implement a method that determines if a problem's instance is valid. (*solved*)
- Implement an algorithm for determining if it is possible to go from one location to another using the given roads. (*solved*)
- Write doc comments in your source code and generate the class documentation using javadoc. (*solved*)

## Laboratorul nr.3

### Compulsory

- Create an object-oriented model of the problem. You should have at least the following classes Person, Company. (*solved*)
- Both classes should implement the interface java.util.Comparable. The natural order of the objects will be given by their names. (*solved*)
- Create the interface Node that defines the method used to obtain the name of a person or company. The classes above must implement this interface. (*solved*)
- Create a java.util.List containing node objects and print it on the screen. (*solved*)

### Homework

- Create the complete model: Person, Programmer, Designer, Company. All persons have a birth date. Each class must have at least one specific property, that others don't have (be creative). (*solved*)
- Each person will contain a Map defining the relathionships to other persons or companies. (*solved*)
- Create the Network class containing a List of identifiable nodes. (*solved*)
- Create a method that computes the importance of a node in the network, as the number of its connections to other nodes. (*solved*)
- Create a network object containing persons, companies and relationships and print it on the screen. When printing the network, the nodes must be ordered according to their importance. (*solved*)

## Laboratorul nr. 4

### Compulsory

- Create a Maven project. (*solved*)
- Create an object-oriented model of the problem. Students and projects have names. Make sure the objects of these classes are comparable. (*solved*)
- Create the students and the projects described in the example. Use streams in order to easily create the objects. (*solved*)
- Put all the students in a LinkedList and print them sorted by their names. (*solved*)
- Put all the projects in a TreeSet and print them sorted by their names. (*solved*)

### Homework

- Create a class that describes the problem. (*solved*)
- Using Java Stream API, write a query that display all the students that have a number of preferences lower than the average number of preferences. (*solved*)
- Use a third-party library in order to generate random fake names for students and projects. (*solved*)
- Create a Greedy algorithm in order to solve the problem. (*solved*)

## Laboratorul nr. 5

### Compulsory

- Create an object-oriented model of the problem. You should have at least the following classes: Catalog, Document. Create a class responsible with external operations regarding a catalog. (*solved*)
- Implement the following methods representing commands that will manage the content of the catalog:
  - add: adds a new entry into the catalog; (*solved*)
  - toString: a textual representation of the catalog; (*solved*)
  - save: saves the catalog to an external file using JSON format; you may use Jackson or other library; (*solved*)
  - load: loads the catalog from an external file. (*solved*)
  
  ### Homework
- Represent the commands using classes instead of methods. Use an interface or an abstract class in order to desribe a generic command. (*solved*)
Implement the commands load, list, view, report (create the classes AddCommand, ListCommand, etc.).
  - list: prints the list of documents on the screen;
  - view: opens a document using the native operating system application (see the Desktop class); 
  - report: creates (and opens) an HTML report representing the content of the catalog. Use a template engine such as FreeMarker or Velocity, in order to create the HTML report.
- The application will signal invalid data or the commands that are not valid using custom exceptions. (*solved* i think)
- The final form of the application will be an executable JAR archive. Identify the generated archive and launch the application from the console, using the JAR. (*solved*)


## Laboratorul nr. 6

### Compulsory

Create the following components:

 - The main frame of the application. (*solved*)
-  A configuration panel for introducing parameters regarding the number of dots and lines and a button for creating a new game. The panel must be placed at the top part of the frame. The panel must contain at least one label and one input component. (*solved*)
- A canvas (drawing panel) for drawing the board. Draw the dots and the lines according to the values specified in the config panel. This panel must be placed in the center part of the frame. (*solved*)
- A control panel for managing the game. This panel will contain the buttons: Load, Save, Exit ,etc. and it will be placed at the bottom part of the frame. (*solved*)

### Homework

- Create the object oriented model of the game. Consider implementing a retained mode for drawing the game board. (
  *solved*)
- Implement the logic of the game. Use a *mouse listener* in order to select the line which must be colored, either by
  selecting the dots or the line itself. Validate the moves, according to the game rules. Determine the winner of the
  game. (*solved*)
- Export the current image of the game board into a PNG file. (*solved*)
- Use object serialization in order to save and restore the current status of the game. (*solved*)

## Laboratorul nr. 7

### Compulsory

- Create an object oriented model of the problem. (*solved*)
- Each robot will have a name and they must perform in a concurrent manner, moving randomly around the map and extracting tokens from the shared memory when reaching an unvisited cell. A message will be displayed on the screen every time a robot visits a new cell. (*solved*)
- __Simulate the exploration using a thread for each robot.__  Pay attention to the synchronization of the threads when extracting tokens and when visiting cells. (*solved*)

### Homework


- Implement the commands that start/pause the robots (all of them or only a specific one). A robot can be paused for a specific time or indefinitely, requiring a start command. The commands must be given using the keyboard. (*kinda solved*)
- Design an algorithm such that each robots will try to explore the map in a systematic fashion, ensuring the termination of the exploration process. (*solved*)
- Implement a timekeeper thread that runs concurrently with the player threads, as a daemon. This thread will display the running time of the exploration and it will stop it exceeds a certain time limit. (*solved*)
- At the end of the exploration, determine how many tokens each robot has placed in the matrix. (*solved*)

## Laboratorul nr. 8

### Compulsory
- Create a relational database using any RDBMS (Oracle, Postgres, MySql, Java DB, etc.). (*solved*)
- Write an SQL script that will create the following tables: (*solved*)
  - albums: id, release year, title, artist, genre(s)
  - artists: id, name (for example: Beatles)
  - genres: id, name (for example: Rock)
  - an associative (junction) table in order to store each album genres  
- Update pom.xml, in order to add the database driver to the project libraries. (*solved*)
- Create a singleton class in order to manage a connection to the database. (*solved*)
- Create DAO classes that offer methods for managing artists, genres and albums (at least one). (*solved*)
- Implement a simple test using your classes.  (*solved*)

### Homework

- Create an object-oriented model of the data managed by the Java application.  (*solved*)
- Implement all the DAO classes. (*solved*)
- Use a connection pool in order to manage database connections, such as C3PO, HikariCP or Apache Commons DBCP. (*solved*)
- Create a tool to import data from a real dataset, such as Rolling Stone's 500 Greatest Albums of All Time (or other). (*solved*)

### Bonus

- Extend the model in order to create playlists. A playlist has a name, a creation timestamp and a set of albums. (*solved*)
- Two albums are related if they meet at least one of the following conditions: are composed by the same artist or have been released in the same year or have at least one common genre.
Create an algorithm that generates maximal playlists (all of them or a limited number, if there are too many) that contain only unrelated albums. (*NO*)


## Laboratorul nr. 9

### Compulsory
- Create a persistence unit (use EclipseLink or Hibernate or other JPA implementation). 
- Verify the presence of the persistence.xml file in your project. Make sure that the driver for EclipseLink or Hibernate was added to to your project classpath (or add it yourself). 
- Define the entity classes for your model (at least one) and put them in a dedicated package. You may use the IDE support in order to generate entity classes from database tables. 
- Create a singleton responsible with the management of an EntityManagerFactory object. 
- Define repository clases for your entities (at least one). They must contain the following methods:
  - create - receives an entity and saves it into the database;
  - findById - returns an entity based on its primary key;
  - findByName - returns a list of entities that match a given name pattern. Use a named query in order to implement this method. 
- Test your application. 

### Homework
- Create all entity classes and repositories. Implement properly the one-to-many and many-to-many relationships.
- Create a generic AbstractRepository using generics in order to simplify the creation of the repository classes. You may take a look at the CrudRepository interface from Spring Framework.
- Insert, using JPA, a large number of fake artists and albums in the database and log the execution time of your JPQL statements. 

## Laboratory no. 10

### Compulsory 

- Create the project ServerApplication. This will contain (at least) the classes: GameServer and ClientThread.(*solved*)
- Create the class GameServer. An instance of this class will create a ServerSocket running at a specified port. The server will receive requests (commands) from clients and it will execute them.(*solved*)
- Create the class ClientThread. An instance of this class will be responsible with communicating with a client Socket. If the server receives the command stop it will stop and will return to the client the respons "Server stopped", otherwise it return: "Server received the request ... ".(*solved*)
- Create the project ClientApplication. This will contain (at least) the class: GameClient.(*solved*)
- Create the class GameClient. An instance of this class will read commands from the keyboard and it will send them to the server. The client stops when it reads from the keyboard the string "exit". (*solved*)

### Homework

- Implement functionalities of the game, using the classes Game, Board, Player, etc. (*solved*)
- The clients will send to the server commands such as: create game, join game, submit move, etc. (*solved*)
- The server is responsible with the game management and mediating the players. (*solved*)
- The games will be played under time control (blitz) with each player having the same amount of time at the beginning of the game. If a player's time runs out, the game is lost. (*nope*)

## Laboratory no. 11

### Compulsory
- Create a Spring Boot project that will contain the REST services for comunicating with the server data. (*solved*)
- Create a REST controller containing a method for:(*solved*)
    - obtaining the list of the registered players, via a HTTP GET request. 
- Test your service using the browser and/or Postman. (*solved*)

### Homework
- Create REST services for:(*solved*)
    - adding a new player, via a HTTP POST request.
    - modifying the name of a player, via a HTTP PUT request.
    - deleting a player, via a HTTP DELETE request. 
- Create a REST service for obtaining the games that were recorded by the server.(*solved*)
- Create a simple client application that invokes the services above, using the support offered by Spring Boot.(*solved*)
- Document your services using Swagger or a similar tool. (*solved*)
