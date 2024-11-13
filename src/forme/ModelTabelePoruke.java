/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Poruka;

/**
 *
 * @author vldmrk
 */
public class ModelTabelePoruke extends AbstractTableModel {

    private List<Poruka> poruke;
    private String[] kolone = {"posiljalac id", "primalac id", "tekst", "vreme"};

    public ModelTabelePoruke(List<Poruka> poruke) {
        this.poruke = poruke;
    }

    @Override
    public int getRowCount() {
        return poruke.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return poruke.get(rowIndex).getPosiljalacId().getUserId();
            case 1:
                return poruke.get(rowIndex).getPrimalacId().getUserId();
            case 2:
                return poruke.get(rowIndex).getText();
            case 3:
                return poruke.get(rowIndex).getVreme();
            default:
                throw new AssertionError();
        }
    }

}
