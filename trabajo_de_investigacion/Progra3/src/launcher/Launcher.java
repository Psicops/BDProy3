package launcher;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import logic.Logic;
import ui.UI;

public class Launcher {

    public static void main(String[] args) throws UnknownHostException, SQLException, Exception{
        try {
            //poniendo el look and feel de windows
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) { 
            JOptionPane.showMessageDialog(null, "You're not using Windows, so the interface might look a little clunky :(", "ERROR",
            JOptionPane.ERROR_MESSAGE);
        }
        
        Logic.getInstance();
        UI.getInstance().displayLogin();
    }
}
