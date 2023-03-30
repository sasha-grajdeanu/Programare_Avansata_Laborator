# Laboratorul nr. 5

## Compulsory

- Create an object-oriented model of the problem. You should have at least the following classes: Compulsory.Catalog, Compulsory.Document. Create a class responsible with external operations regarding a catalog. (*solved*)
- Implement the following methods representing commands that will manage the content of the catalog:
  - add: adds a new entry into the catalog; (*solved*)
  - toString: a textual representation of the catalog; (*solved*)
  - save: saves the catalog to an external file using JSON format; you may use Jackson or other library; (*solved*)
  - load: loads the catalog from an external file. (*solved*)

## Homework
- Represent the commands using classes instead of methods. Use an interface or an abstract class in order to desribe a generic command. (*solved*)
Implement the commands load, list, view, report (create the classes AddCommand, ListCommand, etc.).
  - list: prints the list of documents on the screen;
  - view: opens a document using the native operating system application (see the Desktop class); 
  - report: creates (and opens) an HTML report representing the content of the catalog. Use a template engine such as FreeMarker or Velocity, in order to create the HTML report.
- The application will signal invalid data or the commands that are not valid using custom exceptions. (*solved* i think)
- The final form of the application will be an executable JAR archive. Identify the generated archive and launch the application from the console, using the JAR. (*solved*)