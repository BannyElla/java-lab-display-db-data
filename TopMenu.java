package simpledisplaydatadb;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Ella
 */
public class TopMenu extends JPanel {

    private JLabel connectStringLabel;
    private JTextField connectStringField;
    private JButton connectionButton;
    private static final int DEFAULT_HIGHT = 20;
    private static final int LABEL_WIDTH = 90;
    private static final int FIELD_LENGHT = 200;

    TopMenu(JLabel connectStringLabel,
            JTextField connectStringField,
            JButton connectionButton) {
        this.connectStringLabel = connectStringLabel;
        this.connectStringField = connectStringField;
        this.connectionButton = connectionButton;
        init();
    }

    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        connectStringLabel.setPreferredSize(new Dimension(LABEL_WIDTH, DEFAULT_HIGHT));
        connectStringLabel.setMinimumSize(new Dimension(LABEL_WIDTH, DEFAULT_HIGHT));

        connectStringField.setMinimumSize(new Dimension(FIELD_LENGHT, DEFAULT_HIGHT));
        connectStringField.setPreferredSize(new Dimension(FIELD_LENGHT, DEFAULT_HIGHT));

        add(connectStringLabel);
        add(connectStringField);
        add(Box.createRigidArea(new Dimension(5, 0)));
        add(connectionButton);
    }
}
