package simpledisplaydatadb;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Ella
 */
public class ShowDataButton extends JButton {

    private SimpleDisplayDataDb mainWindow;

    public ShowDataButton(SimpleDisplayDataDb mainWindow) {
        super("Show Data");
        this.mainWindow = mainWindow;
        addListener();
    }

    private void addListener() {
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mainWindow.getTable();
            }
        });
    }
}
