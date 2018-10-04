/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Dheta
 */
public class koneksi {
        static Connection koneksi;
    private Statement stm;

    public koneksi() {

    }

    public Connection getKoneksi() {
        try {
            String database = "bapenda";
            String username = "root";
            String password = "";
            koneksi = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                    + database, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return koneksi;
    }
}
