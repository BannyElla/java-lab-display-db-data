package simpledisplaydatadb;

/**
 * Интерфейс для имплементации парсером, который будет разбирать строку подключения
 */
public interface IParser {
      
    /**
     * Параметры, передаваемые в метод {@link IParser#getParameter}
     */
    int DRIVER = 1;
    int PROTOCOL = 2;
    int HOST = 3;
    int PORT = 4;
    int DB_NAME = 5;
    int USER_NAME = 6;
    int PASSWORD = 7;
    
    /**
     * Проверяет соответствие переданной строки подключения к БД, заданному шаблону
     * @param connectString строка подключения, заданная пользователем
     * @return true, если connectString соответствует шаблону, иначе - false
     */
    boolean checkConnectString(String connectString);

    /**
     * Возвращает значение переданного параметра
     * @param connectString строка подключения, заданная пользователем
     * @param parametrName одна из констант интерфейса
     * @return значение параметра в строковом формате
     */
    String getParameter(String connectString, int parametrName);
    
}
