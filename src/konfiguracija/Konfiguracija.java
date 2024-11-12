/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vldmrk
 */
public class Konfiguracija {

    private static Konfiguracija instance;
    private Properties konfig;
    private String putanja;

    private Konfiguracija() {
        FileInputStream fis = null;
        try {
            putanja = "C:\\Users\\vldmrk\\OneDrive - Fakultet organizacionih nauka\\Documents\\NetBeansProjects\\CHAT_ISPIT_SER\\konfig\\app.config";
            File fajl = new File(putanja);
            fis = new FileInputStream(fajl);
            konfig.load(fis);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static Konfiguracija getInstance() {
        if (instance == null) {
            instance = new Konfiguracija();
        }
        return instance;
    }

    public String getKonfig(String key) {
        return konfig.getProperty(key, "n/a");
    }

    public void setKonfig(String key, String value) {
        konfig.setProperty(key, value);
    }

    public void sacuvajIzmene() {
        try {
            konfig.store(new FileOutputStream(putanja), null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
