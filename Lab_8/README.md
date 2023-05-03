# Laboratorul nr. 8

## Compulsory
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

## Homework

- Create an object-oriented model of the data managed by the Java application.  (*solved*)
- Implement all the DAO classes. (*solved*)
- Use a connection pool in order to manage database connections, such as C3PO, HikariCP or Apache Commons DBCP. (*solved*)
- Create a tool to import data from a real dataset, such as Rolling Stone's 500 Greatest Albums of All Time (or other). (*solved*)

## Bonus

- Extend the model in order to create playlists. A playlist has a name, a creation timestamp and a set of albums. (*solved*)
- Two albums are related if they meet at least one of the following conditions: are composed by the same artist or have been released in the same year or have at least one common genre.
Create an algorithm that generates maximal playlists (all of them or a limited number, if there are too many) that contain only unrelated albums. (*NO*)