package simpledisplaydatadb;

/**
 *
 * @author Ella
 */
public class Main {

    public static void main(String[] args) {
        IParser parser = new ConnectStringParser();
        DataProvider dp = new DataProvider(parser);
        new SimpleDisplayDataDb(dp).setVisible(true);

    }
}
