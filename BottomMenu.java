package simpledisplaydatadb;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Ella
 */
public class BottomMenu extends JPanel {

    private JLabel dropListLabel;
    private JComboBox dropList;
    private ShowDataButton showButton;
    private CloseButton closeButton;
    private static final int DEFAULT_HIGHT = 25;
    private static final int LABEL_WIDTH = 90;
    private static final int DROP_LIST_WIDTH = 200;
    private static final int GAP = 10;

    BottomMenu(JLabel dropListLabel, JComboBox dropList,
            ShowDataButton showButton, CloseButton closeButton) {
        this.dropListLabel = dropListLabel;
        this.dropList = dropList;
        this.showButton = showButton;
        this.closeButton = closeButton;
        init();
    }

    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(GAP, 0, 0, 0));

        dropListLabel.setPreferredSize(new Dimension(LABEL_WIDTH, DEFAULT_HIGHT));
        dropListLabel.setMinimumSize(new Dimension(LABEL_WIDTH, DEFAULT_HIGHT));

        dropList.setPreferredSize(new Dimension(DROP_LIST_WIDTH, DEFAULT_HIGHT));
        dropList.setMinimumSize(new Dimension(DROP_LIST_WIDTH, DEFAULT_HIGHT));

        add(dropListLabel);
        add(dropList);
        add(Box.createRigidArea(new Dimension(GAP, 0)));
        add(showButton);
        add(Box.createRigidArea(new Dimension(GAP, 0)));
        add(closeButton);
    }
}
