/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DBBroker;
import forme.ServerForma;
import java.util.ArrayList;
import java.util.List;
import model.Poruka;
import model.User;
import niti.ObradaKlijentskihZahteva;
import operacije.Operacije;
import transfer.ServerskiOdgovor;

/**
 *
 * @author vldmrk
 */
public class Controller {

    private static Controller instance;
    private List<User> useri;
    private List<ObradaKlijentskihZahteva> niti = new ArrayList<>();
    private DBBroker dbb;
    private ServerForma sf;

    public ServerForma getSf() {
        return sf;
    }

    public void setSf(ServerForma sf) {
        this.sf = sf;
    }

    private Controller() {
        dbb = new DBBroker();
        useri = new ArrayList<>();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public List<User> getUseri() {
        return useri;
    }

    public void setUseri(List<User> useri) {
        this.useri = useri;
    }

    public List<ObradaKlijentskihZahteva> getNiti() {
        return niti;
    }

    public void setNiti(List<ObradaKlijentskihZahteva> niti) {
        this.niti = niti;
    }

    public User login(User user) {
        User u = dbb.login(user);

        if (useri.size() == Integer.parseInt(konfiguracija.Konfiguracija.getInstance().getKonfig("max_br_klijenata")) || useri.contains(u)) {
            u = null;
        } else if (u != null) {
            useri.add(u);
            sf.osveziTabelu();
            for (ObradaKlijentskihZahteva okz : niti) {
                okz.posaljiUsere(useri);
            }
        }
        return u;
    }

    public void logout(User user) {
        useri.remove(user);
        sf.osveziTabelu();
        for (ObradaKlijentskihZahteva okz : niti) {
            okz.posaljiUsere(useri);
        }
    }

    public List<User> vratiUsere() {
        return useri;
    }

    public void posalji(Poruka poruka) {
        dbb.posalji(poruka);
        sf.osveziPoruke();
    }

    public List<Poruka> vratiPoruke() {
        return dbb.vratiPoruke();
    }

}
