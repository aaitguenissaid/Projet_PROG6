package Vue;

import java.io.File;

public class MainMenu extends javax.swing.JPanel {
    CollecteurEvenements cc;
    public MainMenu(CollecteurEvenements ctrl) {
        cc=ctrl;
        initComponents();
    }
    private void initComponents() {

        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler24 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler26 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler25 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jButton3 = new javax.swing.JButton();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler17 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jButton4 = new javax.swing.JButton();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        filler16 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler23 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler22 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jButton5 = new javax.swing.JButton();
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler14 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));

        setBackground(new java.awt.Color(120, 171, 191));
        setLayout(new java.awt.GridLayout(8, 3));
        add(filler1);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(241, 242, 216));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Avalam");
        add(jLabel1);

        jPanel1.setBackground(new java.awt.Color(120, 171, 191));
        jPanel1.setLayout(new java.awt.GridLayout(1, 5, 5, 5));
        jPanel1.add(filler9);
        jPanel1.add(filler8);

        jButton2.setBackground(new java.awt.Color(120, 171, 191));
        jButton2.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/icons/outline_settings_black_24dp.png")))); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jPanel1.add(jButton2);

        jButton1.setBackground(new java.awt.Color(120, 171, 191));
        jButton1.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/icons/outline_account_circle_black_24dp.png")))); // NOI18N
        jButton1.setMaximumSize(new java.awt.Dimension(58, 58));
        jButton1.setMinimumSize(new java.awt.Dimension(58, 58));
        jButton1.setPreferredSize(new java.awt.Dimension(58, 58));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        add(jPanel1);
        add(filler3);
        add(filler24);
        add(filler26);
        add(filler25);

        jButton3.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jButton3.setBackground(new java.awt.Color(132, 166, 81));
        jButton3.setForeground(new java.awt.Color(241, 242, 216));
        jButton3.setText("Jouer en local");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3);
        add(filler10);
        add(filler4);
        add(filler5);
        add(filler11);
        add(filler17);

        jButton4.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jButton4.setBackground(new java.awt.Color(132, 166, 81));
        jButton4.setForeground(new java.awt.Color(241, 242, 216));
        jButton4.setText("Reprendre une partie");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        add(jButton4);
        add(filler6);
        add(filler7);
        add(filler16);
        add(filler23);
        add(filler22);

        jButton5.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jButton5.setBackground(new java.awt.Color(132, 166, 81));
        jButton5.setForeground(new java.awt.Color(241, 242, 216));
        jButton5.setText("Règles du jeu");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        add(jButton5);
        add(filler13);
        add(filler12);
        add(filler14);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:Settings
        cc.parametres();
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:Acount
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here: Lockal game
        cc.jouer_en_local();
    }

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:Sved erly game
    }

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here: Rules
        cc.reagles();
    }


    // Variables declaration - do not modify
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler13;
    private javax.swing.Box.Filler filler14;
    private javax.swing.Box.Filler filler16;
    private javax.swing.Box.Filler filler17;
    private javax.swing.Box.Filler filler22;
    private javax.swing.Box.Filler filler23;
    private javax.swing.Box.Filler filler24;
    private javax.swing.Box.Filler filler25;
    private javax.swing.Box.Filler filler26;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration
}
