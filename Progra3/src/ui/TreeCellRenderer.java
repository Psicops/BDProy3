package ui;

import javax.swing.Icon;
import javax.swing.tree.DefaultTreeCellRenderer;

public class TreeCellRenderer extends DefaultTreeCellRenderer{

    @Override
    public void setLeafIcon(Icon icon) {
        super.setLeafIcon(null);
    }

    @Override
    public void setClosedIcon(Icon icon) {
        super.setClosedIcon(null);
    }

    @Override
    public void setOpenIcon(Icon icon) {
        super.setOpenIcon(icon);
    }
}
