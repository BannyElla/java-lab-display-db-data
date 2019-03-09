package simpledisplaydatadb;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Ella
 */
public class ConnectButton extends JButton {

    private SimpleDisplayDataDb mainWindow;
    private static final int WIDTH = 25;
    private static final int HEIGHT = 90;

    public ConnectButton(SimpleDisplayDataDb mainWindow) {
        super("Connect");
        this.mainWindow = mainWindow;
        init();
        addListener();
    }

    private void init() {
        setMinimumSize(new Dimension(HEIGHT, WIDTH));
        setPreferredSize(new Dimension(HEIGHT, WIDTH));

    }

    private void addListener() {
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //Закрываем предыдущее соедниение, если оно открыто
                mainWindow.closeConnection();
                
                //Открываем новое соедниение
                String connectionString = mainWindow.getConnectString();
                mainWindow.openConnection(connectionString);
                mainWindow.getDropdownList();
            }
        });
    }
}
