package ui;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import logic.Logic;
import logic.Thread;
import logic.User;
import logic.file.FileExtensionException;
import logic.file.Video;

public class CreateThreadDialog extends javax.swing.JDialog {

    private MainFrame parent;
    private User creator;
    private DefaultListModel videosListModel;
    
    public CreateThreadDialog(User creator, MainFrame parent) throws SQLException {
        super(parent, true);
        this.parent = parent;
        this.creator = creator;
        videosListModel = new DefaultListModel();
        initComponents();
        this.setTitle("Create New Thread");
        this.setLocationRelativeTo(parent);
        this.videosList.setModel(videosListModel);
        for(int i = 1; i <= Logic.getInstance().getCantidadPartidos(); i++)
            this.matchIDCombobox.addItem((Integer)i);
    }
    
    private CreateThreadDialog(java.awt.Frame parent, boolean modal){
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        videosMenu = new javax.swing.JPopupMenu();
        addVideoMenuitem = new javax.swing.JMenuItem();
        removeVideoMenuitem = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        textarea = new javax.swing.JTextArea();
        saveButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        videosList = new javax.swing.JList();
        cancelButton = new javax.swing.JButton();
        matchIDCombobox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        teamsLabel = new javax.swing.JLabel();

        addVideoMenuitem.setText("Add Video");
        addVideoMenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVideoMenuitemActionPerformed(evt);
            }
        });
        videosMenu.add(addVideoMenuitem);

        removeVideoMenuitem.setText("Remove Selected Video");
        removeVideoMenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeVideoMenuitemActionPerformed(evt);
            }
        });
        videosMenu.add(removeVideoMenuitem);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        textarea.setColumns(20);
        textarea.setRows(5);
        jScrollPane1.setViewportView(textarea);

        saveButton.setText("Guardar");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        jScrollPane2.setComponentPopupMenu(videosMenu);

        videosList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        videosList.setInheritsPopupMenu(true);
        jScrollPane2.setViewportView(videosList);

        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        matchIDCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matchIDComboboxActionPerformed(evt);
            }
        });

        jLabel1.setText("ID Partido:");

        teamsLabel.setText("team vs team");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(matchIDCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(teamsLabel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(matchIDCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(teamsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        int matchID = (Integer)matchIDCombobox.getSelectedItem();
        String text = textarea.getText();
        try {
            Thread thread = new Thread(matchID, creator, text);
            for(int i = 0; i < videosListModel.getSize(); i++)
                thread.addVideo((Video)videosListModel.get(i));
            Logic.getInstance().addThread(thread);
            parent.update();
            this.dispose();
        } catch (Exception ex) {
            UI.getInstance().displayError(ex.getMessage(), this);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void removeVideoMenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeVideoMenuitemActionPerformed
        if(videosList.getSelectedValue() != null)
            videosListModel.removeElement(videosList.getSelectedValue());
        else
            UI.getInstance().displayError("No video selected to remove.", this);
    }//GEN-LAST:event_removeVideoMenuitemActionPerformed

    private boolean videoAlreadyListed(Video video){
        for(int i = 0; i < videosListModel.getSize(); i++){
            Video videoInList = (Video)videosListModel.get(i);
            if(videoInList.toString().equals(video.toString()))
                return true;
        }
        return false;
    }
    
    private void addVideoMenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVideoMenuitemActionPerformed
        JFileChooser fc = new JFileChooser(".");
        fc.setDialogTitle("Select a Video");
        if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            String path = fc.getSelectedFile().getAbsolutePath();
            try {
                Video video = new Video(path);
                if(!videoAlreadyListed(video))
                    videosListModel.addElement(video);
            } catch (FileExtensionException ex) {
                UI.getInstance().displayError(ex.getMessage(), this);
            }
        }
    }//GEN-LAST:event_addVideoMenuitemActionPerformed

    private void matchIDComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matchIDComboboxActionPerformed
        try {
            this.teamsLabel.setText(Logic.getInstance().getEquipos((Integer) matchIDCombobox.getSelectedItem()));
        } catch (SQLException ex) {
            UI.getInstance().displayError("Error while trying to obtain the correponding match teams:\n"+ex.getMessage(), this);
        }
    }//GEN-LAST:event_matchIDComboboxActionPerformed

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
            java.util.logging.Logger.getLogger(CreateThreadDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateThreadDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateThreadDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateThreadDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CreateThreadDialog dialog = new CreateThreadDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JMenuItem addVideoMenuitem;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox matchIDCombobox;
    private javax.swing.JMenuItem removeVideoMenuitem;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel teamsLabel;
    private javax.swing.JTextArea textarea;
    private javax.swing.JList videosList;
    private javax.swing.JPopupMenu videosMenu;
    // End of variables declaration//GEN-END:variables
}
