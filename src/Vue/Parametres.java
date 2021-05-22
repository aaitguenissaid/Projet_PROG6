package Vue;
import Modele.PaletteDeCouleurs;
import Modele.Preferences;

import java.awt.CardLayout;
import java.io.File;


public class Parametres extends javax.swing.JPanel {
    CollecteurEvenements cc;
    CardLayout cardslayout;
    Preferences prefs;
    PaletteDeCouleurs palette;
    public Parametres(CollecteurEvenements ctrl) {
        cc=ctrl;
        prefs = new Preferences();
        palette = ctrl.getPalette();
        initComponents();
        cardslayout = (CardLayout)(jPanel2.getLayout());
    }
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanel9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jToggleButton16 = new javax.swing.JToggleButton();
        jToggleButton17 = new javax.swing.JToggleButton();
        jToggleButton18 = new javax.swing.JToggleButton();
        jToggleButton19 = new javax.swing.JToggleButton();
        jToggleButton20 = new javax.swing.JToggleButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jToggleButton21 = new javax.swing.JToggleButton();
        jToggleButton22 = new javax.swing.JToggleButton();
        jToggleButton23 = new javax.swing.JToggleButton();
        jToggleButton24 = new javax.swing.JToggleButton();
        jToggleButton25 = new javax.swing.JToggleButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jToggleButton26 = new javax.swing.JToggleButton();
        jToggleButton27 = new javax.swing.JToggleButton();
        jToggleButton28 = new javax.swing.JToggleButton();
        jToggleButton29 = new javax.swing.JToggleButton();
        jToggleButton30 = new javax.swing.JToggleButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();

        setBackground(palette.Couleur1);

        jPanel8.setBackground(palette.Couleur1);
        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel10.setBackground(palette.Couleur1);
        jPanel10.setLayout(new java.awt.GridBagLayout());

        jButton6.setBackground(palette.Couleur1);
        jButton6.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/icons/outline_arrow_back_ios_black_24dp.png")))); // NOI18N
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel10.add(jButton6, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel4.setForeground(palette.Couleur3);
        jLabel4.setText("Parametres");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 11.0;
        gridBagConstraints.weighty = 1.0;
        jPanel10.add(jLabel4, gridBagConstraints);
        jPanel10.add(filler1, new java.awt.GridBagConstraints());

        jPanel8.add(jPanel10);

        jPanel9.setBackground(palette.Couleur1);
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(palette.Couleur1);
        jPanel1.setLayout(new java.awt.GridLayout(7, 1));

        jButton1.setBackground(palette.Couleur2);
        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jButton1.setForeground(palette.Couleur3);
        jButton1.setText("General");
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

        jButton2.setBackground(palette.Couleur2);
        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jButton2.setForeground(palette.Couleur3);
        jButton2.setText("IA");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jPanel1.add(jButton2);

        jButton3.setBackground(palette.Couleur2);
        jButton3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jButton3.setForeground(palette.Couleur3);
        jButton3.setText("Affichage");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jPanel1.add(jButton3);

        jButton4.setBackground(palette.Couleur2);
        jButton4.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jButton4.setForeground(palette.Couleur3);
        jButton4.setText("Controles");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jPanel1.add(jButton4);

        jButton5.setBackground(palette.Couleur2);
        jButton5.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jButton5.setForeground(palette.Couleur3);
        jButton5.setText("Sons");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jPanel1.add(jButton5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 2.0;
        jPanel9.add(jPanel1, gridBagConstraints);

        jPanel2.setBackground(palette.Couleur1);
        jPanel2.setLayout(new java.awt.CardLayout());

        jPanel3.setBackground(palette.Couleur1);
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.PAGE_AXIS));
        jCheckBox1.setBackground(palette.Couleur1);
        jCheckBox1.setForeground(palette.Couleur3);
        jCheckBox1.setText("Recommencer une partie automatiquement lorsqu'une partie se termine");
        jCheckBox1.setSelected(Boolean.parseBoolean(prefs.get(Preferences.RELANCE_AUTOMATIQUE)));
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel3.add(jCheckBox1);

        jCheckBox2.setText("Interdire l'utilisation de l'historique");
        jCheckBox2.setBackground(palette.Couleur1);
        jCheckBox2.setForeground(palette.Couleur3);
        jCheckBox2.setSelected(Boolean.parseBoolean(prefs.get(Preferences.EST_AUTORISE_HISTORIQUE)));
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        jPanel3.add(jCheckBox2);

        jCheckBox3.setText("Interdire les suggestions des IAs");
        jCheckBox3.setBackground(palette.Couleur1);
        jCheckBox3.setForeground(palette.Couleur3);
        jCheckBox3.setSelected(Boolean.parseBoolean(prefs.get(Preferences.EST_AUTORISE_SUGGESTION)));
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });
        jPanel3.add(jCheckBox3);

        jPanel2.add(jPanel3, "card1");

        jPanel4.setBackground(palette.Couleur1);
        jPanel4.setLayout(new java.awt.GridLayout(5, 1));

        jPanel11.setBackground(palette.Couleur1);
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 25, 5));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("IA d'affrontement");
        jPanel11.add(jLabel1);

        jPanel4.add(jPanel11);

        jPanel15.setBackground(palette.Couleur1);
        jPanel15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 30, 5));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel15.add(jComboBox3);

        jPanel4.add(jPanel15);

        jPanel16.setBackground(palette.Couleur1);
        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 25, 5));

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel5.setText("IA démonstration");
        jPanel16.add(jLabel5);

        jPanel4.add(jPanel16);

        jPanel12.setBackground(palette.Couleur1);
        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel13.setBackground(palette.Couleur1);
        jPanel13.setLayout(new java.awt.GridLayout(1, 2));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel2.setText("Joueur A");
        jPanel13.add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel3.setText("Joueur B");
        jPanel13.add(jLabel3);

        jPanel12.add(jPanel13);

        jPanel14.setBackground(palette.Couleur1);
        jPanel14.setLayout(new java.awt.GridLayout(1, 2));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel14.add(jComboBox2);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel14.add(jComboBox1);

        jPanel12.add(jPanel14);

        jPanel4.add(jPanel12);

        jPanel2.add(jPanel4, "card2");

        jPanel5.setBackground(palette.Couleur1);
        jPanel5.setLayout(new java.awt.GridLayout(3, 1));

        jPanel17.setBackground(palette.Couleur1);
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 25);
        flowLayout1.setAlignOnBaseline(true);
        jPanel17.setLayout(flowLayout1);

        jLabel10.setText("Couleur du joueur 1 :");
        jPanel17.add(jLabel10);

        jToggleButton16.setBackground(new java.awt.Color(2, 172, 229));
        jToggleButton16.setText("   ");
        jPanel17.add(jToggleButton16);

        jToggleButton17.setBackground(new java.awt.Color(43, 215, 83));
        jToggleButton17.setText("   ");
        jPanel17.add(jToggleButton17);

        jToggleButton18.setBackground(new java.awt.Color(227, 68, 58));
        jToggleButton18.setText("   ");
        jPanel17.add(jToggleButton18);

        jToggleButton19.setBackground(new java.awt.Color(69, 45, 229));
        jToggleButton19.setText("   ");
        jPanel17.add(jToggleButton19);

        jToggleButton20.setBackground(new java.awt.Color(202, 228, 237));
        jToggleButton20.setText("   ");
        jPanel17.add(jToggleButton20);

        jPanel5.add(jPanel17);

        jPanel18.setBackground(palette.Couleur1);
        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 25);
        flowLayout2.setAlignOnBaseline(true);
        jPanel18.setLayout(flowLayout2);

        jLabel11.setText("Couleur du joueur 2 :");
        jPanel18.add(jLabel11);

        jToggleButton21.setBackground(new java.awt.Color(2, 172, 229));
        jToggleButton21.setText("   ");
        jPanel18.add(jToggleButton21);

        jToggleButton22.setBackground(new java.awt.Color(43, 215, 83));
        jToggleButton22.setText("   ");
        jPanel18.add(jToggleButton22);

        jToggleButton23.setBackground(new java.awt.Color(227, 68, 58));
        jToggleButton23.setText("   ");
        jPanel18.add(jToggleButton23);

        jToggleButton24.setBackground(new java.awt.Color(69, 45, 229));
        jToggleButton24.setText("   ");
        jPanel18.add(jToggleButton24);

        jToggleButton25.setBackground(new java.awt.Color(202, 228, 237));
        jToggleButton25.setText("   ");
        jPanel18.add(jToggleButton25);

        jPanel5.add(jPanel18);

        jPanel19.setBackground(palette.Couleur1);
        java.awt.FlowLayout flowLayout3 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 25);
        flowLayout3.setAlignOnBaseline(true);
        jPanel19.setLayout(flowLayout3);

        jLabel12.setText("Thème");
        jPanel19.add(jLabel12);

        jToggleButton26.setBackground(new java.awt.Color(2, 172, 229));
        jToggleButton26.setText("   ");
        jPanel19.add(jToggleButton26);

        jToggleButton27.setBackground(new java.awt.Color(43, 215, 83));
        jToggleButton27.setText("   ");
        jPanel19.add(jToggleButton27);

        jToggleButton28.setBackground(new java.awt.Color(227, 68, 58));
        jToggleButton28.setText("   ");
        jPanel19.add(jToggleButton28);

        jToggleButton29.setBackground(new java.awt.Color(69, 45, 229));
        jToggleButton29.setText("   ");
        jPanel19.add(jToggleButton29);

        jToggleButton30.setBackground(new java.awt.Color(202, 228, 237));
        jToggleButton30.setText("   ");
        jPanel19.add(jToggleButton30);

        jPanel5.add(jPanel19);

        jPanel2.add(jPanel5, "card3");

        jPanel6.setBackground(palette.Couleur1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 693, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 387, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel6, "card4");

        jPanel7.setBackground(palette.Couleur1);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 693, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 387, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel7, "card5");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(jPanel2, gridBagConstraints);
        jPanel2.getAccessibleContext().setAccessibleName("Cards");

        jPanel8.add(jPanel9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 771, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        cardslayout.show(jPanel2, "card1");
    }

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        cardslayout.show(jPanel2, "card2");
    }

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        cardslayout.show(jPanel2, "card3");
    }

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        cardslayout.show(jPanel2, "card4");
    }

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        cardslayout.show(jPanel2, "card5");
    }

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here: Back
        cc.mainmenu();
    }

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here: Relance Automatique
        if(prefs==null) prefs = new Preferences();
        boolean relanceAutomatique = Boolean.parseBoolean(prefs.get(Preferences.RELANCE_AUTOMATIQUE));
        prefs.set(Preferences.RELANCE_AUTOMATIQUE, String.valueOf(!relanceAutomatique));
    }

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here: Est Autorisé Historique
        if(prefs==null) prefs = new Preferences();
        boolean estAutoriseHistorique = Boolean.parseBoolean(prefs.get(Preferences.EST_AUTORISE_HISTORIQUE));
        prefs.set(Preferences.EST_AUTORISE_HISTORIQUE, String.valueOf(!estAutoriseHistorique));
    }

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here: Est Autorisé Suggestion
        if(prefs==null) prefs = new Preferences();
        boolean estAutoriseSuggestion = Boolean.parseBoolean(prefs.get(Preferences.EST_AUTORISE_SUGGESTION));
        prefs.set(Preferences.EST_AUTORISE_SUGGESTION, String.valueOf(!estAutoriseSuggestion));
    }

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }


    // Variables declaration - do not modify
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JToggleButton jToggleButton16;
    private javax.swing.JToggleButton jToggleButton17;
    private javax.swing.JToggleButton jToggleButton18;
    private javax.swing.JToggleButton jToggleButton19;
    private javax.swing.JToggleButton jToggleButton20;
    private javax.swing.JToggleButton jToggleButton21;
    private javax.swing.JToggleButton jToggleButton22;
    private javax.swing.JToggleButton jToggleButton23;
    private javax.swing.JToggleButton jToggleButton24;
    private javax.swing.JToggleButton jToggleButton25;
    private javax.swing.JToggleButton jToggleButton26;
    private javax.swing.JToggleButton jToggleButton27;
    private javax.swing.JToggleButton jToggleButton28;
    private javax.swing.JToggleButton jToggleButton29;
    private javax.swing.JToggleButton jToggleButton30;
    // End of variables declaration
}
