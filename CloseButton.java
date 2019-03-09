package simpledisplaydatadb;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Ella
 */
public class CloseButton extends JButton {

    private SimpleDisplayDataDb mainWindow;
    private static final int WIDTH = 25;
    private static final int HEIGHT = 90;

    public CloseButton(SimpleDisplayDataDb mainWindow) {
        super("Close");
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
                mainWindow.closeConnection();
            }
        });
    }
}
