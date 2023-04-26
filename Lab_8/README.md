# Laboratorul nr. 8

## Compulsory
- Create a relational database using any RDBMS (Oracle, Postgres, MySql, Java DB, etc.).
- Write an SQL script that will create the following tables:
  - albums: id, release year, title, artist, genre(s)
  - artists: id, name (for example: Beatles)
  - genres: id, name (for example: Rock)
  - an associative (junction) table in order to store each album genres 
- Update pom.xml, in order to add the database driver to the project libraries.
- Create a singleton class in order to manage a connection to the database.
- Create DAO classes that offer methods for managing artists, genres and albums (at least one).
- Implement a simple test using your classes. 