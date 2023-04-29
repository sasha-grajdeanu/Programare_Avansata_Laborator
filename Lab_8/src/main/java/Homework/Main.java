package Homework;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        try {
            ToolToInsertCSV tool = new ToolToInsertCSV();
            tool.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            DBCPDatabase.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
