package simpledisplaydatadb;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ella
 */
public class ConnectStringParser implements IParser {

    private static final String patternString = 
            "(\\w+):(\\w+)://(\\w+):(\\d+){4}/([\\w\\d-_]+);"
            + "user=([\\w\\d-_]+);password=(.*)";
    public static String INVALID_CONNECT_STRING_MESSAGE = 
            "Invalid Connect String.\n Valid pattern:\n"
            + "jdbc:\\<protocol>://<host>:<port>/<db_name>;"
            + "[user=<user_name>;password=<password>]";

    @Override
    public String getParameter(String connectString, int parametrName) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(connectString.trim());
        if (matcher.matches()) {
            return matcher.group(parametrName);
        } else {
            throw new IllegalArgumentException(INVALID_CONNECT_STRING_MESSAGE);
        }
    }

    @Override
    public boolean checkConnectString(String connectString) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(connectString);
        if (matcher.matches()) return true; 
        return false; 
    }
}
