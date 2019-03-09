package simpledisplaydatadb;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Интерфейс для получения данных из БД
 * 
 */
public interface IDataProvider {

    /**
     * Открывает соединение с БД
     * @param connectString строка подключения к БД
     * @throws SQLException 
     */
    void openConnection(String connectString)  throws SQLException;
    
    /**
     * Получает список существующих таблиц в указанной БД
     * @return список таблиц из БД
     * @throws SQLException 
     */
    ArrayList<String> getExistedTableNames() throws SQLException;
    
    /**
     * Получает данные из указанной таблицы
     * @param tableName таблица базы данных
     * @return данные из таблицы в виде коллекции ArrayList<String[]>
     * @throws SQLException 
     */
    ArrayList<String[]> getDataFromTable(String tableName) throws SQLException;

    /**
     * Получает заголовок текущей таблицы
     * @return коллекцию с названимями колонок текущей таблицы
     */
    ArrayList<String> getHeader();
    
    /**
     * Закрывает соединение с БД
     * @throws SQLException 
     */
    void closeConnection() throws SQLException;
    
}
