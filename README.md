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
