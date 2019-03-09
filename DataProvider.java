package simpledisplaydatadb;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Ella
 */
public class DataProvider implements IDataProvider {

    private String connectString;
    private IParser parser;

    private Connection connection;
    private Statement stmt;
    private ResultSet resultData;
    private ArrayList<String> header;
    private ArrayList<String[]> tableData;

    DataProvider(IParser parser) {
        this.parser = parser;
    }
     @Override
    public void openConnection(String connectString) throws SQLException {
        if(parser.checkConnectString(connectString)) {
        this.connectString = connectString;
        } else {
            throw new IllegalArgumentException(ConnectStringParser.INVALID_CONNECT_STRING_MESSAGE);
        }
        closeConnection();
        Connection con = DriverManager.getConnection(this.connectString);
        this.connection = con;
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                   ResultSet.CONCUR_READ_ONLY);
        
    }

    @Override
    public ArrayList<String> getExistedTableNames() throws SQLException {
        ArrayList<String> tables = new ArrayList<>();
        String dbName = parser.getParameter(connectString, parser.DB_NAME);
        DatabaseMetaData meta = this.connection.getMetaData();
        ResultSet metaTabels = meta.getTables(null, dbName.toUpperCase(),
                                              null, null);
        while (metaTabels.next()) {
            tables.add(metaTabels.getString("TABLE_NAME"));
        }
        return tables;
    }

    @Override
    public ArrayList<String[]> getDataFromTable(String tableName) throws SQLException {
        String query = "select * from " + tableName;
        resultData = stmt.executeQuery(query);
        header = getTableHeader(resultData);
        tableData = dataDbToArrayList(resultData);
        return tableData;
    }

    @Override
    public ArrayList<String> getHeader() {
        return this.header;
    }

    @Override
    public void closeConnection() throws SQLException {
        if(connection != null) { 
            connection.close();
        connection=null;
        }
    }

    private ArrayList<String> getTableHeader(ResultSet data) throws SQLException {
        ArrayList<String> columns = new ArrayList<>();
        int colCnt = data.getMetaData().getColumnCount();
        ResultSetMetaData resMeta = data.getMetaData();
        for (int i = 1; i <= colCnt; ++i) {
            columns.add(resMeta.getColumnName(i));
        }
        return columns;
    }

    private ArrayList<String[]> dataDbToArrayList(ResultSet table) throws SQLException {
        ArrayList<String[]> rows = new ArrayList<>();
        int colCnt = table.getMetaData().getColumnCount();
        while (table.next()) {
            String[] row = new String[colCnt];
            for (int i = 1; i <= colCnt; ++i) {
                row[i - 1] = table.getObject(i).toString();
            }
            rows.add(row);
        }
        return rows;
    }

}
