package Vue;
import Global.Configuration;
import Modele.PaletteDeCouleurs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;


public class Parametres extends javax.swing.JPanel {
    CollecteurEvenements cc;
    CardLayout cardslayout;
    PaletteDeCouleurs palette;
    public Parametres(CollecteurEvenements ctrl) {
        cc=ctrl;
        palette = ctrl.getPalette();
        initComponents();
        cardslayout = (CardLayout)(jPanel2.getLayout());
    }
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        jPanel8 = new JPanel();
        jPanel10 = new JPanel();
        jButton6 = new JButton();
        jLabel4 = new JLabel();
        filler1 = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(32767, 0));
        jPanel9 = new JPanel();
        jPanel1 = new JPanel();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jButton4 = new JButton();
        jButton5 = new JButton();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();
        jCheckBox1 = new JCheckBox();
        jCheckBox2 = new JCheckBox();
        jCheckBox3 = new JCheckBox();
        jPanel4 = new JPanel();
        jPanel11 = new JPanel();
        jLabel1 = new JLabel();
        jPanel15 = new JPanel();
        jComboBox3 = new JComboBox<>();
        jPanel16 = new JPanel();
        jLabel5 = new JLabel();
        jPanel12 = new JPanel();
        jPanel13 = new JPanel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jPanel14 = new JPanel();
        jComboBox2 = new JComboBox<>();
        jComboBox1 = new JComboBox<>();
        jPanel5 = new JPanel();
        jPanel17 = new JPanel();
        jLabel10 = new JLabel();
        jToggleButton16 = new JToggleButton();
        jToggleButton17 = new JToggleButton();
        jToggleButton18 = new JToggleButton();
        jToggleButton19 = new JToggleButton();
        jToggleButton20 = new JToggleButton();
        jPanel18 = new JPanel();
        jLabel11 = new JLabel();
        jToggleButton21 = new JToggleButton();
        jToggleButton22 = new JToggleButton();
        jToggleButton23 = new JToggleButton();
        jToggleButton24 = new JToggleButton();
        jToggleButton25 = new JToggleButton();
        jPanel19 = new JPanel();
        jLabel12 = new JLabel();
        jButton26 = new JButton();
        jButton27 = new JButton();
        jButton28 = new JButton();
        jButton29 = new JButton();
        jButton30 = new JButton();
        jPanel6 = new JPanel();
        jPanel7 = new JPanel();

        setToutCouleurs();

        jPanel8.setLayout(new BoxLayout(jPanel8, BoxLayout.PAGE_AXIS));

        jPanel10.setLayout(new GridBagLayout());

        jButton6.setIcon(new ImageIcon(String.valueOf(new File("ressources/icons/outline_arrow_back_ios_black_24dp.png")))); // NOI18N
        jButton6.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel10.add(jButton6, gridBagConstraints);

        jLabel4.setFont(new Font("Ubuntu", 1, 18)); // NOI18N
        jLabel4.setText("Parametres");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 11.0;
        gridBagConstraints.weighty = 1.0;
        jPanel10.add(jLabel4, gridBagConstraints);
        jPanel10.add(filler1, new GridBagConstraints());

        jPanel8.add(jPanel10);

        jPanel9.setLayout(new GridBagLayout());

        jPanel1.setLayout(new GridLayout(7, 1));

        jButton1.setFont(new Font("Ubuntu", 1, 14)); // NOI18N
        jButton1.setText("General");
        jButton1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton2.setFont(new Font("Ubuntu", 1, 14)); // NOI18N
        jButton2.setText("IA");
        jButton2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jPanel1.add(jButton2);

        jButton3.setFont(new Font("Ubuntu", 1, 14)); // NOI18N
        jButton3.setText("Affichage");
        jButton3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jPanel1.add(jButton3);

        jButton4.setFont(new Font("Ubuntu", 1, 14)); // NOI18N
        jButton4.setText("Controles");
        jButton4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jPanel1.add(jButton4);

        jButton5.setFont(new Font("Ubuntu", 1, 14)); // NOI18N
        jButton5.setText("Sons");
        jButton5.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jPanel1.add(jButton5);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 2.0;
        jPanel9.add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new CardLayout());

        jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.PAGE_AXIS));
        jCheckBox1.setText("Recommencer une partie automatiquement lorsqu'une partie se termine");
        jCheckBox1.setSelected(Boolean.parseBoolean(prefs().get(Configuration.RELANCE_AUTOMATIQUE)));
        jCheckBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel3.add(jCheckBox1);

        jCheckBox2.setText("Interdire l'utilisation de l'historique");
        jCheckBox2.setSelected(Boolean.parseBoolean(prefs().get(Configuration.EST_AUTORISE_HISTORIQUE)));
        jCheckBox2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        jPanel3.add(jCheckBox2);

        jCheckBox3.setText("Interdire les suggestions des IAs");
        jCheckBox3.setSelected(Boolean.parseBoolean(prefs().get(Configuration.EST_AUTORISE_SUGGESTION)));
        jCheckBox3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });
        jPanel3.add(jCheckBox3);

        jPanel2.add(jPanel3, "card1");

        jPanel4.setLayout(new GridLayout(5, 1));

        jPanel11.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 5));

        jLabel1.setFont(new Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("IA d'affrontement");
        jPanel11.add(jLabel1);

        jPanel4.add(jPanel11);

        jPanel15.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 5));

        jComboBox3.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel15.add(jComboBox3);

        jPanel4.add(jPanel15);

        jPanel16.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 5));

        jLabel5.setFont(new Font("Ubuntu", 1, 18)); // NOI18N
        jLabel5.setText("IA démonstration");
        jPanel16.add(jLabel5);

        jPanel4.add(jPanel16);

        jPanel12.setLayout(new BoxLayout(jPanel12, BoxLayout.PAGE_AXIS));

        jPanel13.setLayout(new GridLayout(1, 2));

        jLabel2.setFont(new Font("Ubuntu", 1, 14)); // NOI18N
        jLabel2.setText("Joueur A");
        jPanel13.add(jLabel2);

        jLabel3.setFont(new Font("Ubuntu", 1, 14)); // NOI18N
        jLabel3.setText("Joueur B");
        jPanel13.add(jLabel3);

        jPanel12.add(jPanel13);

        jPanel14.setLayout(new GridLayout(1, 2));

        jComboBox2.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel14.add(jComboBox2);

        jComboBox1.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel14.add(jComboBox1);

        jPanel12.add(jPanel14);

        jPanel4.add(jPanel12);

        jPanel2.add(jPanel4, "card2");

        jPanel5.setLayout(new GridLayout(3, 1));

        FlowLayout flowLayout1 = new FlowLayout(FlowLayout.LEFT, 15, 25);
        flowLayout1.setAlignOnBaseline(true);
        jPanel17.setLayout(flowLayout1);

        jLabel10.setText("Couleur du joueur 1 :");
        jPanel17.add(jLabel10);


        jToggleButton16.setText("   ");
        jPanel17.add(jToggleButton16);


        jToggleButton17.setText("   ");
        jPanel17.add(jToggleButton17);


        jToggleButton18.setText("   ");
        jPanel17.add(jToggleButton18);


        jToggleButton19.setText("   ");
        jPanel17.add(jToggleButton19);


        jToggleButton20.setText("   ");
        jPanel17.add(jToggleButton20);

        jPanel5.add(jPanel17);

        FlowLayout flowLayout2 = new FlowLayout(FlowLayout.LEFT, 15, 25);
        flowLayout2.setAlignOnBaseline(true);
        jPanel18.setLayout(flowLayout2);

        jLabel11.setText("Couleur du joueur 2 :");
        jPanel18.add(jLabel11);

        jToggleButton21.setText("   ");
        jPanel18.add(jToggleButton21);


        jToggleButton22.setText("   ");
        jPanel18.add(jToggleButton22);


        jToggleButton23.setText("   ");
        jPanel18.add(jToggleButton23);


        jToggleButton24.setText("   ");
        jPanel18.add(jToggleButton24);


        jToggleButton25.setText("   ");
        jPanel18.add(jToggleButton25);

        jPanel5.add(jPanel18);

        FlowLayout flowLayout3 = new FlowLayout(FlowLayout.LEFT, 15, 25);
        flowLayout3.setAlignOnBaseline(true);
        jPanel19.setLayout(flowLayout3);

        jLabel12.setText("Thème");
        jPanel19.add(jLabel12);


        jButton26.setText("   ");
        jButton26.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton26MouseClicked(evt);
            }
        });
        jPanel19.add(jButton26);


        jButton27.setText("   ");
        jButton27.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton27MouseClicked(evt);
            }
        });
        jPanel19.add(jButton27);


        jButton28.setText("   ");
        jButton28.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton28MouseClicked(evt);
            }
        });
        jPanel19.add(jButton28);


        jButton29.setText("   ");
        jButton29.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton29MouseClicked(evt);
            }
        });
        jPanel19.add(jButton29);


        jButton30.setText("   ");
        jButton30.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton30MouseClicked(evt);
            }
        });
        jPanel19.add(jButton30);

        jPanel5.add(jPanel19);

        jPanel2.add(jPanel5, "card3");


        GroupLayout jPanel6Layout = new GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 693, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 387, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel6, "card4");


        GroupLayout jPanel7Layout = new GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 693, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 387, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel7, "card5");

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(jPanel2, gridBagConstraints);
        jPanel2.getAccessibleContext().setAccessibleName("Cards");

        jPanel8.add(jPanel9);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel8, GroupLayout.PREFERRED_SIZE, 771, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel8, GroupLayout.PREFERRED_SIZE, 445, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
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

    private void jCheckBox1ActionPerformed(ActionEvent evt) {
        // TODO add your handling code here: Relance Automatique
        boolean relanceAutomatique = Boolean.parseBoolean(prefs().get(Configuration.RELANCE_AUTOMATIQUE));
        prefs().set(Configuration.RELANCE_AUTOMATIQUE, String.valueOf(!relanceAutomatique));
    }

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here: Est Autorisé Historique
        boolean estAutoriseHistorique = Boolean.parseBoolean(prefs().get(Configuration.EST_AUTORISE_HISTORIQUE));
        prefs().set(Configuration.EST_AUTORISE_HISTORIQUE, String.valueOf(!estAutoriseHistorique));
    }

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here: Est Autorisé Suggestion
        boolean estAutoriseSuggestion = Boolean.parseBoolean(prefs().get(Configuration.EST_AUTORISE_SUGGESTION));
        prefs().set(Configuration.EST_AUTORISE_SUGGESTION, String.valueOf(!estAutoriseSuggestion));
    }

    private void jButton26MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        palette.setSheme1();
        cc.revalidateInterface();
     }
    private void jButton27MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        palette.setSheme2();
        cc.revalidateInterface();
    }
    private void jButton28MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        palette.setSheme3();
        cc.revalidateInterface();
    }
    private void jButton29MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        palette.setSheme4();
        cc.revalidateInterface();
    }
    private void jButton30MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        palette.setSheme5();
        cc.revalidateInterface();
    }

    public void setToutCouleurs(){
        setBackground(palette.Couleur1);
        jPanel8.setBackground(palette.Couleur1);
        jPanel10.setBackground(palette.Couleur1);
        jButton6.setBackground(palette.Couleur1);
        jLabel4.setForeground(palette.Couleur3);
        jPanel9.setBackground(palette.Couleur1);
        jPanel1.setBackground(palette.Couleur1);
        jButton1.setBackground(palette.Couleur2);
        jButton1.setForeground(palette.Couleur3);
        jButton2.setBackground(palette.Couleur2);
        jButton2.setForeground(palette.Couleur3);
        jButton3.setBackground(palette.Couleur2);
        jButton3.setForeground(palette.Couleur3);
        jButton4.setBackground(palette.Couleur2);
        jButton4.setForeground(palette.Couleur3);
        jButton5.setBackground(palette.Couleur2);
        jButton5.setForeground(palette.Couleur3);
        jPanel2.setBackground(palette.Couleur1);
        jPanel3.setBackground(palette.Couleur1);
        jCheckBox1.setBackground(palette.Couleur1);
        jCheckBox1.setForeground(palette.Couleur3);
        jCheckBox2.setBackground(palette.Couleur1);
        jCheckBox2.setForeground(palette.Couleur3);
        jCheckBox3.setBackground(palette.Couleur1);
        jCheckBox3.setForeground(palette.Couleur3);
        jPanel4.setBackground(palette.Couleur1);
        jPanel11.setBackground(palette.Couleur1);
        jPanel15.setBackground(palette.Couleur1);
        jPanel16.setBackground(palette.Couleur1);
        jPanel12.setBackground(palette.Couleur1);
        jPanel13.setBackground(palette.Couleur1);
        jPanel14.setBackground(palette.Couleur1);
        jPanel5.setBackground(palette.Couleur1);
        jPanel17.setBackground(palette.Couleur1);
        jToggleButton16.setBackground(new java.awt.Color(2, 172, 229));jToggleButton17.setBackground(new java.awt.Color(43, 215, 83));jToggleButton18.setBackground(new java.awt.Color(227, 68, 58));jToggleButton19.setBackground(new java.awt.Color(69, 45, 229));jToggleButton20.setBackground(new java.awt.Color(202, 228, 237));
        jPanel18.setBackground(palette.Couleur1);
        jToggleButton21.setBackground(new java.awt.Color(2, 172, 229));jToggleButton22.setBackground(new java.awt.Color(43, 215, 83));jToggleButton23.setBackground(new java.awt.Color(227, 68, 58));jToggleButton24.setBackground(new java.awt.Color(69, 45, 229));jToggleButton25.setBackground(new java.awt.Color(202, 228, 237));
        jPanel19.setBackground(palette.Couleur1);
        jButton26.setBackground(new java.awt.Color(2, 172, 229));jButton27.setBackground(new java.awt.Color(43, 215, 83));jButton28.setBackground(new java.awt.Color(227, 68, 58));jButton29.setBackground(new java.awt.Color(69, 45, 229));jButton30.setBackground(new java.awt.Color(202, 228, 237));
        jPanel6.setBackground(palette.Couleur1);
        jPanel7.setBackground(palette.Couleur1);
    }

    private Configuration prefs() {
        return Configuration.instance();
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
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton30;
    // End of variables declaration
}
