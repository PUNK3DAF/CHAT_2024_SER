/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vldmrk
 */
public class Konekcija {

    private static Konekcija instance;
    private Connection konek;

    public Connection getKonek() {
        return konek;
    }

    private Konekcija() {
        try {
            String url = "jdbc:mysql://localhost:3306/chat2024";
            konek = DriverManager.getConnection(url, "root", "");
            konek.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(Konekcija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Konekcija getInstance() {
        if (instance == null) {
            instance = new Konekcija();
        }
        return instance;
    }

}
