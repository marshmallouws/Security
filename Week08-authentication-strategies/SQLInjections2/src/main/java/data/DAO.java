/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Annika
 */
public class DAO {

    public void testDB(String username) {
        try {
            String query = "SELECT * FROM User WHERE username = '" + username + "';";
            ResultSet rs = DBConnector.getConnection().prepareStatement(query).executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("username") + " " + rs.getString("pass") + " " + rs.getString("udata"));
            }
        } catch (SQLException e) {

        }
    }
    
    public static void main(String[] args) {
        DAO d = new DAO();
        d.testDB("a");
    }
}
