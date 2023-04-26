import java.sql.*;

public class ArtistDAO {
    private int id = 1;

    /**
     * this method insert in database an artist
     * @param name
     * @throws SQLException
     */
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try(PreparedStatement preparedStatement = con.prepareStatement("insert into artists (id, name) values (?, ?)")){
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            int val = preparedStatement.executeUpdate();
            System.out.println(val);
            id++;
        }
    }

    /**
     * this method search the id of the given name in the artists tabel
     * @param name
     * @return
     * @throws SQLException
     */
    public int findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try(Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select id from artists where name='"+ name +"'")){
            return resultSet.next() ? resultSet.getInt(1):null;
        }
    }

    /**
     * this method search the id of the given name in the artists tabel
     * @param id
     * @return
     * @throws SQLException
     */
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try(Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select name from artists where id='"+id+"'")){
            return resultSet.next()?resultSet.getString(1):null;
        }
    }
}
