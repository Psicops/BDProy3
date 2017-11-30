package ui;

import javax.swing.JFileChooser;
import logic.Logic;
import logic.User;
import logic.file.Image;

public class CreateUserDialog extends javax.swing.JDialog {
    
    Image image;
    
    public CreateUserDialog(LoginFrame parent) {
        super(parent, true);
        initComponents();
        this.setTitle("Create New User");
        this.setLocationRelativeTo(parent);
        statusCombobox.addItem(User.ADMINISTRATOR);
        statusCombobox.addItem(User.REGULAR);
        imageCheckbox.setSelected(true);
        emailCheckbox.setSelected(true);
        setImageLabel("default.jpg");
    }
    
    private void setImageLabel(String imagePath){
        try {
            image = new Image(imagePath);
            imageLabel.setIcon(image.toIcon(imageLabel.getHeight(), imageLabel.getWidth()));
        } catch (Exception ex) {
            UI.getInstance().displayError("La imagen no pudo ser cargada:\n"+ex.getMessage(), this);
        }
    }
    
    private CreateUserDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        changeImageMenuitem = new javax.swing.JMenuItem();
        emailTextfield = new javax.swing.JTextField();
        usernameTextfield = new javax.swing.JTextField();
        passwordTextfield = new javax.swing.JPasswordField();
        statusCombobox = new javax.swing.JComboBox();
        imageCheckbox = new javax.swing.JCheckBox();
        emailCheckbox = new javax.swing.JCheckBox();
        createButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        imageLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        changeImageMenuitem.setText("Change Image");
        changeImageMenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeImageMenuitemActionPerformed(evt);
            }
        });
        popupMenu.add(changeImageMenuitem);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        imageCheckbox.setText("Show Image on Threads");

        emailCheckbox.setText("Show Email on Threads");

        createButton.setText("Create");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        imageLabel.setBackground(new java.awt.Color(51, 0, 51));
        imageLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        imageLabel.setComponentPopupMenu(popupMenu);

        jLabel1.setText("Username:");

        jLabel2.setText("Password:");

        jLabel3.setText("Status:");

        jLabel5.setText("Email:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameTextfield)
                            .addComponent(emailTextfield)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(statusCombobox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(imageCheckbox)
                                    .addComponent(emailCheckbox)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(passwordTextfield)))))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(createButton, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                    .addComponent(cancelButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(imageLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(emailTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usernameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(passwordTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(statusCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imageCheckbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emailCheckbox))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(createButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        String id = usernameTextfield.getText();
        String password = passwordTextfield.getText();
        String status = (String)statusCombobox.getSelectedItem();
        String email = emailTextfield.getText();
        boolean displayEmail = emailCheckbox.isSelected();
        boolean displayImage = imageCheckbox.isSelected();
        try {
            Logic.getInstance().addUser(new User(id, password, status, email, image, displayEmail, displayImage));
            UI.getInstance().displayInfo("User created.", this);
            this.dispose();
        } catch (Exception ex) {
            UI.getInstance().displayError("El usuario no pudo ser creado:\n"+ex.getMessage(), this);
        }
    }//GEN-LAST:event_createButtonActionPerformed

    private void changeImageMenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeImageMenuitemActionPerformed
        JFileChooser fc = new JFileChooser(".");
        fc.setDialogTitle("Select an Image");
        if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            String path = fc.getSelectedFile().getAbsolutePath();
            setImageLabel(path);
        }
    }//GEN-LAST:event_changeImageMenuitemActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreateUserDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateUserDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateUserDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateUserDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CreateUserDialog dialog = new CreateUserDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JMenuItem changeImageMenuitem;
    private javax.swing.JButton createButton;
    private javax.swing.JCheckBox emailCheckbox;
    private javax.swing.JTextField emailTextfield;
    private javax.swing.JCheckBox imageCheckbox;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField passwordTextfield;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JComboBox statusCombobox;
    private javax.swing.JTextField usernameTextfield;
    // End of variables declaration//GEN-END:variables
}
