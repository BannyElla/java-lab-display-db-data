package simpledisplaydatadb;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Ella
 */
public class SimpleDisplayDataDb extends JFrame {

    private JScrollPane scrollTable = null;
    private JTextField connectStringField;
    private IDataProvider dp;
    private ArrayList<String> existedTables;
    private JComboBox dropList;
    private BottomMenu bottomMenuPanel;
    private ShowDataButton showButton;
    private CloseButton closeButton;
    private JPanel menuPanel;
    private JPanel tabelPanel = null;
    private String connectionStringExample = "jdbc:derby://localhost:1527/demozab2;user=demozab2;password=demozab2";
    

    public SimpleDisplayDataDb(IDataProvider dp) {
        setTitle("Db Query");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(200, 150, 720, 300);
        setLayout(new BorderLayout());
        initPanels();
        this.dp = dp;
    }

    private void initPanels() {
        //Панель меню
        menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        menuPanel.setLayout(new BorderLayout());
        add(menuPanel, BorderLayout.NORTH);

        //Верхняя часть панели меню
        JButton connectionButton = new ConnectButton(this);
        connectStringField = new JTextField();
        JLabel connectStringLabel = new JLabel("Connect string");
        TopMenu topMenuPanel = new TopMenu(connectStringLabel,
                connectStringField,
                connectionButton);
        menuPanel.add(topMenuPanel, BorderLayout.NORTH);
        connectStringField.setText(connectionStringExample);

        //Нижняя часть панели меню
        JLabel dropListLabel = new JLabel("Tables");
        dropList = new JComboBox();
        showButton = new ShowDataButton(this);
        closeButton = new CloseButton(this);
        bottomMenuPanel = new BottomMenu(dropListLabel, dropList, showButton, closeButton);
        menuPanel.add(bottomMenuPanel, BorderLayout.SOUTH);

        showButton.setEnabled(false);
        closeButton.setEnabled(false);

        //Панель для таблицы с данными
        tabelPanel = new JPanel();
        tabelPanel.setLayout(new GridLayout(1, 1));
        add(tabelPanel, BorderLayout.CENTER);
    }

    public void closeConnection() {
        if(scrollTable !=null) {
        tabelPanel.remove(scrollTable);
        tabelPanel.repaint();
        }
        dropList.removeAllItems();
        showButton.setEnabled(false);
        closeButton.setEnabled(false);
        try {
            dp.closeConnection();
        } catch (SQLException se) {
            showErrorMessage(this, se.getLocalizedMessage());
        }
    }

    public String getConnectString() {
        return connectStringField.getText();
    }

    public void getDropdownList() {
        try {
            existedTables = dp.getExistedTableNames();
            dropList.removeAllItems();
            for (String str : existedTables) {
                dropList.addItem(str);
            }
            showButton.setEnabled(true);
            closeButton.setEnabled(true);
            bottomMenuPanel.repaint();

        } catch (SQLException se) {           
            showErrorMessage(this, se.getLocalizedMessage());
        }
    }

    public void getTable() {
        String selectedTabel = dropList.getSelectedItem().toString();
        try {
            TableData table = new TableData(getModel(selectedTabel));
            scrollTable = new JScrollPane(table);
            tabelPanel.removeAll();
            tabelPanel.add(scrollTable);
            tabelPanel.doLayout();
        } catch (SQLException se) {
            showErrorMessage(this, se.getLocalizedMessage());
        }
    }

    public void openConnection(String connectionString) {
        try {
            dp.openConnection(connectionString);
        } catch (RuntimeException re) {
            showWarningMessage(this, re.getMessage());
        } catch (SQLException se) {
            showErrorMessage(this, se.getLocalizedMessage());
        }
    }
    
    private TableModel getModel(String selectedTabel) throws SQLException {
        ArrayList<String[]> dataForJTable = dp.getDataFromTable(selectedTabel);
        ArrayList<String> header = dp.getHeader();
        return new TableModel(dataForJTable, header);
    }


    private static void showErrorMessage(JFrame mainWindow, String message) {
        JOptionPane.showMessageDialog(mainWindow,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
    
    private static void showWarningMessage(JFrame mainWindow, String message) {
        JOptionPane.showMessageDialog(mainWindow,
                message,
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }
}
