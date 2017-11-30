package ui;

import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import logic.Comment;
import logic.Thread;
import logic.User;
import logic.file.Video;

public class ThreadDialog extends javax.swing.JDialog {

    private Thread thread;
    private User user;
    
    private DefaultListModel videosListModel;
    
    public ThreadDialog(Thread thread, User user, MainFrame parent) {
        super(parent, true);
        this.thread = thread;
        this.user = user;
        initComponents();
        this.setTitle("Thread for Match ID: "+thread.getMatchID());
        this.setLocationRelativeTo(parent);
        this.textarea.setText(thread.getText());
        this.commentsTree.setRootVisible(false);
        this.videosListModel = new DefaultListModel();
        videosList.setModel(videosListModel);
        for(Video video : thread.getVideos())
            videosListModel.addElement(video);
        updateComments();
    }
    
    private void populateCommentsTree(DefaultMutableTreeNode parent, List<Comment> comments){
        DefaultMutableTreeNode child;
        for(Comment comment : comments){
            child = new DefaultMutableTreeNode(comment);
            parent.add(child);
            if(comment.hasChildren())
                populateCommentsTree(child, comment.getChildren());
        }
    }
    
    public final void updateComments(){
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        populateCommentsTree(root, thread.getComments());
        DefaultTreeModel model = new DefaultTreeModel(root);
        commentsTree.setModel(model);
    }
    
    private ThreadDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        commentsMenu = new javax.swing.JPopupMenu();
        addReplyToCommentMenuitem = new javax.swing.JMenuItem();
        addReplyToThreadMenuitem = new javax.swing.JMenuItem();
        videosMenu = new javax.swing.JPopupMenu();
        viewVideoMenuItem = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        textarea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        videosList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        commentsTree = new javax.swing.JTree();

        addReplyToCommentMenuitem.setText("Responder al Comentario Seleccionado");
        addReplyToCommentMenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addReplyToCommentMenuitemActionPerformed(evt);
            }
        });
        commentsMenu.add(addReplyToCommentMenuitem);

        addReplyToThreadMenuitem.setText("Agregar Comentario");
        addReplyToThreadMenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addReplyToThreadMenuitemActionPerformed(evt);
            }
        });
        commentsMenu.add(addReplyToThreadMenuitem);

        viewVideoMenuItem.setText("Ver Video");
        viewVideoMenuItem.setInheritsPopupMenu(true);
        viewVideoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewVideoMenuItemActionPerformed(evt);
            }
        });
        videosMenu.add(viewVideoMenuItem);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        textarea.setColumns(20);
        textarea.setRows(5);
        textarea.setEnabled(false);
        jScrollPane1.setViewportView(textarea);

        jScrollPane3.setComponentPopupMenu(videosMenu);

        videosList.setInheritsPopupMenu(true);
        jScrollPane3.setViewportView(videosList);

        jScrollPane2.setComponentPopupMenu(commentsMenu);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        commentsTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        commentsTree.setInheritsPopupMenu(true);
        jScrollPane2.setViewportView(commentsTree);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addReplyToCommentMenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addReplyToCommentMenuitemActionPerformed
        if(commentsTree.getSelectionPath() != null){
            Comment comment = (Comment)((DefaultMutableTreeNode)commentsTree.getSelectionPath().getLastPathComponent()).getUserObject();
            new ReplyDialog(thread, comment, user, this).setVisible(true);
        }
        else
            UI.getInstance().displayError("No comment selected to reply to.", this);
    }//GEN-LAST:event_addReplyToCommentMenuitemActionPerformed

    private void addReplyToThreadMenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addReplyToThreadMenuitemActionPerformed
        new ReplyDialog(thread, null, user, this).setVisible(true);
    }//GEN-LAST:event_addReplyToThreadMenuitemActionPerformed

    private void viewVideoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewVideoMenuItemActionPerformed
        if(videosList.getSelectedValue() != null)
            new VideoDialog((Video)videosList.getSelectedValue(), this).setVisible(true);
        else
            UI.getInstance().displayError("Select a video to play.", this);
    }//GEN-LAST:event_viewVideoMenuItemActionPerformed

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
            java.util.logging.Logger.getLogger(ThreadDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThreadDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThreadDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThreadDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThreadDialog dialog = new ThreadDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JMenuItem addReplyToCommentMenuitem;
    private javax.swing.JMenuItem addReplyToThreadMenuitem;
    private javax.swing.JPopupMenu commentsMenu;
    private javax.swing.JTree commentsTree;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea textarea;
    private javax.swing.JList videosList;
    private javax.swing.JPopupMenu videosMenu;
    private javax.swing.JMenuItem viewVideoMenuItem;
    // End of variables declaration//GEN-END:variables
}
