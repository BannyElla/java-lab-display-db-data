package simpledisplaydatadb;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ella
 */
public class TableModel  extends AbstractTableModel{
    private ArrayList<String[]> data;
    private ArrayList<String> header;
    
    public TableModel(ArrayList<String[]> data, ArrayList<String> header) {
        this.data = data;
        this.header = header;
    }
    @Override
    public String getColumnName(int i) {
        return header.get(i);
    }
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return data.get(0).length;
    }

    @Override
    public Object getValueAt(int i, int j) {
        return data.get(i)[j];
    }
    
}
