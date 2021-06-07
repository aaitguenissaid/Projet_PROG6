package Vue;

import Global.Configuration;
import Modele.PaletteDeCouleurs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NomsDeJoueurs extends javax.swing.JDialog {
    CollecteurEvenements cc;
    PaletteDeCouleurs palette;
    public NomsDeJoueurs(Frame parent, boolean modal, CollecteurEvenements cc) {
        super(parent, modal);
        this.cc=cc;
        palette = cc.getPalette();
        initComponents();
    }

    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
         filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jLabel1 = new javax.swing.JLabel();
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
         jTextField1 = new javax.swing.JTextField();
       filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jPanel5 = new javax.swing.JPanel();
         filler23 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jLabel3 = new javax.swing.JLabel();
        filler28 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jTextField3 = new javax.swing.JTextField();
         filler34 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jPanel6 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        filler35 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());
        setToutCouleurs();


        jPanel1.setLayout(new java.awt.GridLayout(7, 1));
        jPanel1.add(filler11);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Choisissez un nom pour le premier joueur");
        jPanel1.add(jLabel1);
        jPanel1.add(filler12);

        jTextField1.setText(Configuration.instance().get(Configuration.PSEUDO_J1));
        jTextField1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    jButton1.setEnabled(false);
                    jButton1MouseClicked(null);
                }       else {
                  //  String str = jTextField1.getText()+e.toString();
                  //  if (str.equals(cc.getJeu().getNomJ1())){
                      //  jButton1.setEnabled(false);
                    //}else{
                    jButton1.setEnabled(true);//}
                }
            }
        });
        jPanel1.add(jTextField1);
        jPanel1.add(filler3);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButton2.setText("Sortir ");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jPanel2.add(jButton2);

        jButton1.setText("Sauvegarder ");

        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
                jButton1.setEnabled(false);
            }
        });
        jPanel2.add(jButton1);

        jPanel1.add(jPanel2);
        jPanel1.add(filler7);

        jTabbedPane1.addTab("premier joueur", jPanel1);

        jButton6.setEnabled(false);
        jButton1.setEnabled(false);
        jPanel5.setLayout(new java.awt.GridLayout(7, 1));
        jPanel5.add(filler23);
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Choisissez un nom pour le deuxième joueur");
        jPanel5.add(jLabel3);
        jPanel5.add(filler28);

        jTextField3.setText(Configuration.instance().get(Configuration.PSEUDO_J2));
        jTextField3.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    jButton6MouseClicked(null);
                    jButton6.setEnabled(false);
                }else {
                    //String str = jTextField3.getText()+e.toString();
                  //  if (str.equals(cc.getJeu().getNomJ2())){
                   //     jButton6.setEnabled(false);
                  //  }else{
                    jButton6.setEnabled(true);
                //}
                }
            }
        });
        jPanel5.add(jTextField3);
        jPanel5.add(filler34);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButton5.setText("Sortir ");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jPanel6.add(jButton5);
        jButton6.setText("Sauvegarder ");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
                jButton6.setEnabled(false);
            }
        });
        jPanel6.add(jButton6);

        jPanel5.add(jPanel6);
        jPanel5.add(filler35);

        jTabbedPane1.addTab("Deuxième joueur", jPanel5);

        getContentPane().add(jTabbedPane1, "card2");

        pack();
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        if(jTextField1.getText()!="") {
            cc.setNomJ1(jTextField1.getText());
            System.out.println(jTextField1.getText());
        }else{
            jButton1.setEnabled(true);
        }
    }
    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {
        dispose();
    }
    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {
        dispose();
    }
    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {
        if(jTextField3.getText()!=""){
            cc.setNomJ2(jTextField3.getText());
            System.out.println(jTextField3.getText());
        }else{
            jButton6.setEnabled(true);
        }
    }
    public void setToutCouleurs(){
        setBackground(palette.Couleur1);
        jTabbedPane1.setBackground(palette.Couleur1);
        jTabbedPane1.setForeground(palette.Couleur4);
        jPanel1.setBackground(palette.Couleur1);
        jLabel1.setBackground(palette.Couleur7);
        jTextField1.setBackground(palette.Couleur1);
        jPanel2.setBackground(palette.Couleur1);
        jButton2.setBackground(palette.Couleur2);
        jButton1.setBackground(palette.Couleur2);
        jPanel5.setBackground(palette.Couleur1);
        jLabel3.setBackground(palette.Couleur7);
        jTextField3.setBackground(palette.Couleur1);
        jPanel6.setBackground(palette.Couleur1);
        jButton5.setBackground(palette.Couleur2);
        jButton6.setBackground(palette.Couleur2);
        jLabel1.setForeground(palette.Couleur7);
        jLabel3.setForeground(palette.Couleur7);
        jButton1.setForeground(palette.Couleur3);
        jButton2.setForeground(palette.Couleur3);
        jButton6.setForeground(palette.Couleur3);
        jButton5.setForeground(palette.Couleur3);
    }
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler23;
    private javax.swing.Box.Filler filler28;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler34;
    private javax.swing.Box.Filler filler35;
    private javax.swing.Box.Filler filler7;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
}
