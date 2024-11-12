/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.User;

/**
 *
 * @author vldmrk
 */
public class ModelTabeleUseri extends AbstractTableModel {

    private List<User> useri;
    private final String[] kolone = {"id", "username"};

    public ModelTabeleUseri(List<User> useri) {
        this.useri = useri;
    }

    @Override
    public int getRowCount() {
        return useri.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return useri.get(rowIndex).getUserId();
            }
            case 1 -> {
                return useri.get(rowIndex).getUsername();
            }
            default ->
                throw new AssertionError();
        }
    }

    public List<User> getUseri() {
        return useri;
    }

    public void setUseri(List<User> useri) {
        this.useri = useri;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

}
