package Vue;

import java.awt.*;

public class DebutJeuDialog extends javax.swing.JDialog {
    CollecteurEvenements cc;
    public DebutJeuDialog(Frame parent, boolean modal, CollecteurEvenements cc) {
        super(parent, modal);
        this.cc=cc;
        initComponents();
    }
    private void initComponents() {

        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jLabel1 = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(120, 171, 191));
        getContentPane().setLayout(new java.awt.GridLayout(5, 1));
        getContentPane().add(filler1);

        jLabel1.setBackground(new java.awt.Color(120, 171, 191));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        getContentPane().add(filler2);

        jPanel1.setBackground(new java.awt.Color(120, 171, 191));
        jPanel1.setLayout(new java.awt.GridLayout());

        jButton1.setBackground(new java.awt.Color(120, 171, 191));
        jButton1.setText("J vs J");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton2.setBackground(new java.awt.Color(120, 171, 191));
        jButton2.setText("j vs IA");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jPanel1.add(jButton2);

        getContentPane().add(jPanel1);
        getContentPane().add(filler3);

        pack();
    }


    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        cc.jouer_en_local();
        dispose();
    }
    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        cc.jouer_en_local();
        dispose();
    }
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
}
