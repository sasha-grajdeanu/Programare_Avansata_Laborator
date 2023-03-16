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
