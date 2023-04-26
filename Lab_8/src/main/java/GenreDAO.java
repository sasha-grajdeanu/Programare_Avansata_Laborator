import java.sql.*;

public class GenreDAO {
    private int id=1;

    /**
     * this method inserts a genre in the database
     * @param name
     * @throws SQLException
     */
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try(PreparedStatement preparedStatement = con.prepareStatement("insert into genres values (?, ?)");){
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
            id++;
        }
    }

    /**
     * this method search the id of the given name in the genres tabel
     * @param name
     * @return
     * @throws SQLException
     */
    public int findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try(Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select id from genres where name='"+name+"'")){
            return resultSet.next() ? resultSet.getInt(1):null;
        }
    }

    /**
     * this method search the id of the given name in the genres tabel
     * @param id
     * @return
     * @throws SQLException
     */
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try(Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select name from genres where id='"+id+"'")){
            return resultSet.next()?resultSet.getString(1):null;
        }
    }
}
