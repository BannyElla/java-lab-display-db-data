package simpledisplaydatadb;

import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Ella
 */
public class TableData extends JTable {

    TableData(TableModel model) throws SQLException {
        super(model);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        setRowSorter(sorter);      
    }
}
