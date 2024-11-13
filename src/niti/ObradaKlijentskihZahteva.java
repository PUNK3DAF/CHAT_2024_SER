/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Poruka;
import model.User;
import operacije.Operacije;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author vldmrk
 */
public class ObradaKlijentskihZahteva extends Thread {

    private final Socket s;

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            switch (kz.getOperacija()) {
                case operacije.Operacije.LOGIN:
                    User u = Controller.getInstance().login((User) kz.getParam());
                    so.setOperacija(Operacije.LOGIN);
                    so.setOdgovor(u);
                    break;
                case operacije.Operacije.LOGOUT:
                    Controller.getInstance().logout((User) kz.getParam());
                    break;
                case operacije.Operacije.POSALJI_USERE:
                    List<User> useri = Controller.getInstance().vratiUsere();
                    so.setOdgovor(useri);
                    so.setOperacija(Operacije.POSALJI_USERE);
                    break;
                case Operacije.PORUKA:
                    Controller.getInstance().posalji((Poruka) kz.getParam());
                    break;
                default:
                    throw new AssertionError();
            }
            posaljiOdgovor(so);

        }
    }

    public KlijentskiZahtev primiZahtev() {
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return (KlijentskiZahtev) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void posaljiOdgovor(ServerskiOdgovor so) {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void posaljiUsere(List<User> useri) {
        ServerskiOdgovor so = new ServerskiOdgovor(useri, Operacije.POSALJI_USERE);
        posaljiOdgovor(so);
    }

}
