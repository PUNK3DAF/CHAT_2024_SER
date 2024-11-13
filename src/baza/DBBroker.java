/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Poruka;
import model.User;

/**
 *
 * @author vldmrk
 */
public class DBBroker {

    public DBBroker() {
    }

    public User login(User user) {
        User u = null;
        try {
            String upit = "SELECT * FROM useri WHERE username=? AND pass=?";
            PreparedStatement ps = Konekcija.getInstance().getKonek().prepareStatement(upit);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPass());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setUserId(rs.getInt("userId"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public void posalji(Poruka poruka) {
        System.out.println(poruka.getPosiljalacId().getUserId() + " " + poruka.getPrimalacId().getUserId());
        try {
            String upit = "INSERT INTO poruke (posiljalacId,primalacId,text,vreme) VALUES (?,?,?,?)";
            PreparedStatement ps = Konekcija.getInstance().getKonek().prepareStatement(upit);
            ps.setInt(1, poruka.getPosiljalacId().getUserId());
            ps.setInt(2, poruka.getPrimalacId().getUserId());
            ps.setString(3, poruka.getText());
            Timestamp ts = new Timestamp(poruka.getVreme().getTime());
            ps.setTimestamp(4, ts);
            ps.executeUpdate();
            Konekcija.getInstance().getKonek().commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Poruka> vratiPoruke() {
        List<Poruka> poruke = new ArrayList<>();
        try {
            String upit = "SELECT * FROM poruke p JOIN useri u ON p.PosiljalacId=u.UserId";
            Statement st = Konekcija.getInstance().getKonek().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                Poruka p = new Poruka();
                p.setPorId(rs.getInt("porId"));
                User pos = new User();
                pos.setUserId(rs.getInt("posiljalacId"));
                User prim = new User();
                prim.setUserId(rs.getInt("primalacId"));
                p.setPosiljalacId(pos);
                p.setPrimalacId(prim);
                p.setPosiljalacId(p.getPosiljalacId());
                p.setText(rs.getString("text"));
                Date vreme = new Date(rs.getTimestamp("vreme").getTime());
                p.setVreme(vreme);
                poruke.add(p);
            }
            Konekcija.getInstance().getKonek().commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return poruke;
    }

}
