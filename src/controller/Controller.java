/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author vldmrk
 */
public class Controller {

    private static Controller instance;
    private List<User> useri;

    private Controller() {
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

}
