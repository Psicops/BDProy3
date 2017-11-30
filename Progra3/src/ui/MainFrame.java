package ui;

import java.sql.SQLException;
import javax.swing.DefaultListModel;
import logic.Logic;
import logic.User;
import logic.Thread;

public class MainFrame extends javax.swing.JFrame {
    private static final String LOGIN_MSG = "Logged in as: ";
    
    private final User user;
    private final DefaultListModel threadListModel;
    
    public MainFrame(User user) {
        this.user = user;
        initComponents();
        this.setTitle("Football Reddit");
        this.setLocationRelativeTo(null);
        loginLabel.setText(LOGIN_MSG+user.getId());
        statusLabel.setText(user.getStatus());
        imageLabel.setIcon(user.getImage().toIcon(imageLabel.getHeight(), imageLabel.getWidth()));
        threadListModel = new DefaultListModel();
        threadList.setModel(threadListModel);
        update();
    }
    
    public final void update(){
        try {
            threadListModel.clear();
            for(Thread thread : Logic.getInstance().getThreads())
                threadListModel.addElement(thread);
        } catch (Exception ex) {
            UI.getInstance().displayError("Error al obtener los threads de Mongo:\n"+ex.getMessage(), this);
        }
    }

    private MainFrame(){
        initComponents();
        this.user = null;
        this.threadListModel = null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        addThreadMenuitem = new javax.swing.JMenuItem();
        deleteThreadMenuitem = new javax.swing.JMenuItem();
        openThreadMenuitem = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        threadList = new javax.swing.JList();
        loginLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();

        addThreadMenuitem.setText("Create New Thread");
        addThreadMenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addThreadMenuitemActionPerformed(evt);
            }
        });
        popupMenu.add(addThreadMenuitem);

        deleteThreadMenuitem.setText("Delete Selected Thread");
        deleteThreadMenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteThreadMenuitemActionPerformed(evt);
            }
        });
        popupMenu.add(deleteThreadMenuitem);

        openThreadMenuitem.setText("Open Selected Thread");
        openThreadMenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openThreadMenuitemActionPerformed(evt);
            }
        });
        popupMenu.add(openThreadMenuitem);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setComponentPopupMenu(popupMenu);

        threadList.setInheritsPopupMenu(true);
        jScrollPane1.setViewportView(threadList);

        loginLabel.setText("Logged in as:");

        statusLabel.setText("STATUS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loginLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(statusLabel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginLabel)
                    .addComponent(statusLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openThreadMenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openThreadMenuitemActionPerformed
        if(threadList.getSelectedValue() != null)
            new ThreadDialog((Thread)threadList.getSelectedValue(), user, this).setVisible(true);
        else
            UI.getInstance().displayError("No selected thread to open.", this);
    }//GEN-LAST:event_openThreadMenuitemActionPerformed

    private void addThreadMenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addThreadMenuitemActionPerformed
        if(user.getStatus().equals(User.ADMINISTRATOR)){
            try {
                new CreateThreadDialog(user, this).setVisible(true);
            } catch (SQLException ex) {
                UI.getInstance().displayError("Error while connecting to the Oracle database:\n"+ex.getMessage(), this);
            }
        }
        else
            UI.getInstance().displayError("You must be an administrator to create threads.", this);
    }//GEN-LAST:event_addThreadMenuitemActionPerformed

    private void deleteThreadMenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteThreadMenuitemActionPerformed
        try{
            if(threadList.getSelectedValue() != null){
                Logic.getInstance().deleteThread((Thread)threadList.getSelectedValue());
                this.update();
            }
            else
                UI.getInstance().displayError("No selected thread to delete.", this);
        } catch(Exception ex){
            UI.getInstance().displayError("Error when deleting a thread:\n"+ex.getMessage(), this);
        }
    }//GEN-LAST:event_deleteThreadMenuitemActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addThreadMenuitem;
    private javax.swing.JMenuItem deleteThreadMenuitem;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JMenuItem openThreadMenuitem;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JList threadList;
    // End of variables declaration//GEN-END:variables
}
