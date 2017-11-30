package ui;

import java.awt.Component;
import javax.swing.JOptionPane;

public class UI {
    public static final String POPUP_MENU_MESSAGE = "Presione el botón de mouse derecho para más opciones.";
    
    private static UI instance;
    
    private LoginFrame loginFrame;
    
    private UI(){
        loginFrame = null;
    }
    
    public static UI getInstance(){
        if(instance == null)
            instance = new UI();
        return instance;
    }
    
    public void displayLogin(){
        if(loginFrame == null)
            loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
    
    public void displayError(String message, Component parent){
        JOptionPane.showMessageDialog(parent, message, "ERROR",
                                      JOptionPane.ERROR_MESSAGE);
    }
    
    public void displayInfo(String message, Component parent){
        JOptionPane.showMessageDialog(null, message, "Información",
                                      JOptionPane.INFORMATION_MESSAGE);
    }
    
    public boolean displayConfirm(String message, Component parent){
        int reply =  JOptionPane.showConfirmDialog(null, message, "Pregunta", 
                                      JOptionPane.YES_NO_OPTION);
        return reply == JOptionPane.YES_OPTION;
    }
}