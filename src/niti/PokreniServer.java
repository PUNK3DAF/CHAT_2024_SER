/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import konfiguracija.Konfiguracija;

/**
 *
 * @author vldmrk
 */
public class PokreniServer extends Thread {

    private int brojac = 0;
    private final int max = Integer.parseInt(Konfiguracija.getInstance().getKonfig("max_broj_klijenata"));
    private boolean kraj = false;
    private ServerSocket ss;

    @Override
    public void run() {
        try {
            ss = new ServerSocket(9000);
            while (!kraj) {
                if (brojac < max) {
                    Socket s = ss.accept();
                    System.out.println("Klijent broj " + brojac + 1 + " povezan");
                    ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                    okz.start();
                    brojac++;
                } else {
                    JOptionPane.showMessageDialog(null, "NE MOZE VISE KLIJENATA", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void zaustaviServer() {
        try {
            kraj = true;
            System.out.println("SOKET ZATVOREN");
            ss.close();
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
