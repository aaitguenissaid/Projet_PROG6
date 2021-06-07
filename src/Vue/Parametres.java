package Vue;

import Global.Configuration;
import Modele.Jeu;
import Modele.PaletteDeCouleurs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class Parametres extends javax.swing.JPanel {
    CollecteurEvenements cc;
    PaletteDeCouleurs palette;
    public Parametres(CollecteurEvenements ctrl) {
        cc=ctrl;
        palette = cc.getPalette();
        initComponents();
    }
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jLabel1 = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jButton31 = new javax.swing.JButton();
        jPanel11=new javax.swing.JPanel();
        jPanel12=new javax.swing.JPanel();
        setToutCouleurs();
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));


        jButton1.setIcon(new javax.swing.ImageIcon(Configuration.instance().contenuFichier(Configuration.ICON_BACK)));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel2.add(jButton1);
        jPanel2.add(filler1);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18));
        jLabel1.setText("Paramètres");
        jPanel2.add(jLabel1);
        jPanel2.add(filler2);

        add(jPanel2);

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {1};
        jPanel1Layout.rowHeights = new int[] {1};
        jPanel1Layout.columnWeights = new double[] {1.0};
        jPanel1Layout.rowWeights = new double[] {1.0};
        jPanel1.setLayout(jPanel1Layout);


        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("General");
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel6.add(jLabel4);

        jCheckBox1.setFont(new java.awt.Font("Ubuntu", 0, 14));
        jCheckBox1.setText("Recommencer une partie automatiquement lorsqu'une partie se termine");
        jCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jCheckBox1.setSelected(Boolean.parseBoolean(Configuration.instance().get(Configuration.RELANCE_AUTOMATIQUE)));
        jPanel6.add(jCheckBox1);

        jCheckBox2.setFont(new java.awt.Font("Ubuntu", 0, 14));
        jCheckBox2.setText("Interdire l'utilisation de l'historique");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        jCheckBox2.setSelected(!Boolean.parseBoolean(Configuration.instance().get(Configuration.EST_AUTORISE_HISTORIQUE)));
        jPanel6.add(jCheckBox2);

        jCheckBox3.setFont(new java.awt.Font("Ubuntu", 0, 14));
        jCheckBox3.setText("Interdire les suggestions des IAs");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });
        jCheckBox3.setSelected(!Boolean.parseBoolean(Configuration.instance().get(Configuration.EST_AUTORISE_SUGGESTION)));
        jPanel6.add(jCheckBox3);

        jPanel4.add(jPanel6);


        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel3.setText("IA");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel7.add(jLabel3);

        jPanel3.setAlignmentX(0.0F);
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, BoxLayout.PAGE_AXIS));

        jLabel2.setText("Choisissez la complexité de l'intelligence artificielle :");
        jPanel3.add(jLabel2);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(Configuration.instance().lis("IA_names").split(",")));
        jComboBox2.setAlignmentX(0.0F);
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jComboBox2.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jComboBox2.setMaximumSize(new java.awt.Dimension(91, 32));
        jComboBox2.setSelectedItem(Configuration.instance().get(Configuration.IA_AFFRONTEMENT));
        jPanel3.add(jComboBox2);

        jCheckBox4.setFont(new java.awt.Font("Ubuntu", 0, 14));
        jCheckBox4.setText("Laisser l'IA commencer");
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });
        jCheckBox4.setSelected(Boolean.parseBoolean(Configuration.instance().get(Configuration.IA_COMMENCE)));
        jPanel3.add(jCheckBox4);

        jPanel7.add(jPanel3);
        //
        jPanel11.setLayout(new java.awt.GridLayout(2, 2));
        jPanel11.setAlignmentX(0.0F);
        jPanel12.setAlignmentX(0.0F);
        jLabel7.setText("Complexité de la première IA");
        jPanel11.add(jLabel7);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(Configuration.instance().lis("IA_names").split(",")));
        jComboBox3.setSelectedItem(Configuration.instance().get(Configuration.IA_1));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jPanel11.add(jComboBox3);

        jLabel8.setText("Complexité de la deuxième IA");
        jPanel11.add(jLabel8);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(Configuration.instance().lis("IA_names").split(",")));
        jComboBox4.setSelectedItem(Configuration.instance().get(Configuration.IA_2));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        jPanel11.add(jComboBox4);
        jPanel12.setAlignmentX(0.0F);
        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.LINE_AXIS));
        jPanel12.add(jPanel11);

        jButton2.setText("Lancer la démo");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jPanel12.add(jButton2);


        ///
        jPanel4.add(jPanel7);
        jPanel12.setMaximumSize(new Dimension(800, 75));
        jLabel9.setText("DEMO");
        jLabel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel4.add(jLabel9);
        jPanel4.add(jPanel12);
        jPanel5.setAlignmentX(0.0F);
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Affichage");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel5.add(jLabel5);

        jPanel8.setAlignmentX(0.0F);
        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.LINE_AXIS));


        jButton34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton34MouseClicked(evt);
            }
        });
        jPanel8.add(jButton34);


        jButton35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton35MouseClicked(evt);
            }
        });
        jPanel8.add(jButton35);


        jButton36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton36MouseClicked(evt);
            }
        });
        jPanel8.add(jButton36);


        jButton37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton37MouseClicked(evt);
            }
        });
        jPanel8.add(jButton37);


        jButton38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton38MouseClicked(evt);
            }
        });
        jPanel8.add(jButton38);

        RenduDePalette p=new RenduDePalette(palette);
        p.setMaximumSize(new Dimension(100,100));
        jPanel8.add(p);

        jPanel5.add(jPanel8);

        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.PAGE_AXIS));
        jPanel10.setAlignmentX(0.0F);
        jPanel10.setLayout(new javax.swing.BoxLayout(jPanel10, javax.swing.BoxLayout.LINE_AXIS));
        jLabel13.setText("Son : ");
        jPanel10.add(jLabel13);

        jButton31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton31MouseClicked(evt);
            }
        });
        if(!Boolean.parseBoolean(Configuration.instance().get(Configuration.SON_ON))) {
            cc.deisabel_enabel_son();
            setSonIcon();
        }
        jPanel10.add(jButton31);



        jPanel9.add(jPanel10);

        jPanel5.add(jPanel9);

        jPanel4.add(jPanel5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weightx = 8.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel4, gridBagConstraints);

        jScrollPane5.setViewportView(jPanel1);

        add(jScrollPane5);
    }

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {
        // Handler pour la ComboBox de choix de l'IA_AFFRONTEMENT
        Configuration.instance().set(Configuration.IA_AFFRONTEMENT, jComboBox2.getSelectedItem().toString());
    }
    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {
        // Handler pour la ComboBox de choix de l'IA_1
        Configuration.instance().set(Configuration.IA_1, jComboBox3.getSelectedItem().toString());
    }
    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {
        // Handler pour la ComboBox de choix de l'IA_2
        Configuration.instance().set(Configuration.IA_2, jComboBox4.getSelectedItem().toString());
    }

    private void jCheckBox3ActionPerformed(ActionEvent evt) {
        // Handler pour le choix du paramètre général d'autorisation / interdiction des suggestions
        Boolean b =Boolean.parseBoolean(Configuration.instance().get(Configuration.EST_AUTORISE_SUGGESTION));
        b=!b;
        Configuration.instance().set(Configuration.EST_AUTORISE_SUGGESTION,b.toString());
    }
    private void jCheckBox4ActionPerformed(ActionEvent evt) {
        // Handler pour laisser commencer l'IA
        Boolean b =Boolean.parseBoolean(Configuration.instance().get(Configuration.IA_COMMENCE));
        b=!b;
        Configuration.instance().set(Configuration.IA_COMMENCE,b.toString());
    }

    private void jCheckBox2ActionPerformed(ActionEvent evt) {
        // Handler pour le choix du paramètre général d'autorisation / interdiction de l'historique
        Boolean b =Boolean.parseBoolean(Configuration.instance().get(Configuration.EST_AUTORISE_HISTORIQUE));
        b=!b;
        Configuration.instance().set(Configuration.EST_AUTORISE_HISTORIQUE,b.toString());
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        // Handler pour le bouton de retour
        cc.revalidateInterface();
        cc.mainmenu();
    }

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {
        // Handler pour le bouton de lancement de la démo
        cc.lancer_demo();
    }

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        // Handler pour le choix du paramètre général de relance automatique
        Boolean b =Boolean.parseBoolean(Configuration.instance().get(Configuration.RELANCE_AUTOMATIQUE));
        b=!b;
        Configuration.instance().set(Configuration.RELANCE_AUTOMATIQUE,b.toString());
    }

    private void jButton34MouseClicked(java.awt.event.MouseEvent evt) {
        // Handler pour le choix du Theme1
        palette.setSheme1();
        Configuration.instance().set(Configuration.COULEUR_THEME, "1");
        cc.revalidateInterface();
    }

    private void jButton35MouseClicked(java.awt.event.MouseEvent evt) {
        // Handler pour le choix du Theme2
        palette.setSheme2();
        Configuration.instance().set(Configuration.COULEUR_THEME, "2");
        cc.revalidateInterface();
    }

    private void jButton36MouseClicked(java.awt.event.MouseEvent evt) {
        // Handler pour le choix du Theme3
        palette.setSheme3();
        Configuration.instance().set(Configuration.COULEUR_THEME, "3");
        cc.revalidateInterface();
    }

    private void jButton37MouseClicked(java.awt.event.MouseEvent evt) {
        // Handler pour le choix du Theme4
        palette.setSheme4();
        Configuration.instance().set(Configuration.COULEUR_THEME, "4");
        cc.revalidateInterface();
    }

    private void jButton38MouseClicked(java.awt.event.MouseEvent evt) {
        // Handler pour le choix du Theme5
        palette.setSheme5();
        Configuration.instance().set(Configuration.COULEUR_THEME, "5");
        cc.revalidateInterface();
    }

    private void jButton31MouseClicked(java.awt.event.MouseEvent evt) {
        // Handler pour l'activation / la désactivation du son
        cc.deisabel_enabel_son();
        boolean son_on = Boolean.parseBoolean(Configuration.instance().get(Configuration.SON_ON));
        Configuration.instance().set(Configuration.SON_ON, String.valueOf(!son_on));
        setSonIcon();
    }


    public void setToutCouleurs() {
        setThemeIcon();
        setSonIcon();
        jPanel2.setBackground(palette.Couleur1);
        jButton1.setBackground(palette.Couleur1);
        jButton2.setBackground(palette.Couleur2);
        jLabel1.setBackground(palette.Couleur1);
        jLabel1.setForeground(palette.Couleur7);
        jLabel9.setBackground(palette.Couleur1);
        jLabel9.setForeground(palette.Couleur7);
        jLabel7.setBackground(palette.Couleur1);
        jLabel7.setForeground(palette.Couleur7);
        jLabel8.setBackground(palette.Couleur1);
        jLabel8.setForeground(palette.Couleur7);
        jPanel4.setBackground(palette.Couleur1);
        jPanel6.setBackground(palette.Couleur1);
        jLabel4.setBackground(palette.Couleur7);
        jLabel4.setForeground(palette.Couleur7);
        jCheckBox1.setBackground(palette.Couleur1);
        jCheckBox2.setBackground(palette.Couleur1);
        jCheckBox3.setBackground(palette.Couleur1);
        jCheckBox4.setBackground(palette.Couleur1);
        jPanel7.setBackground(palette.Couleur1);
        jLabel3.setBackground(palette.Couleur7);
        jLabel3.setForeground(palette.Couleur7);
        jPanel3.setBackground(palette.Couleur1);
        jLabel2.setBackground(palette.Couleur7);
        jLabel2.setForeground(palette.Couleur7);
        jComboBox2.setBackground(palette.Couleur1);
        jComboBox3.setBackground(palette.Couleur1);
        jComboBox4.setBackground(palette.Couleur1);
        jPanel5.setBackground(palette.Couleur1);
        jPanel11.setBackground(palette.Couleur1);
        jPanel12.setBackground(palette.Couleur1);
        jLabel5.setBackground(palette.Couleur7);
        jLabel5.setForeground(palette.Couleur7);
        jPanel8.setBackground(palette.Couleur1);
        jButton34.setBackground(palette.CouleurSh1);
        jButton35.setBackground(palette.CouleurSh2);
        jButton36.setBackground(palette.CouleurSh3);
        jButton37.setBackground(palette.CouleurSh4);
        jButton38.setBackground(palette.CouleurSh5);
        jPanel14.setBackground(palette.Couleur1);
        jPanel9.setBackground(palette.Couleur1);
        jLabel6.setBackground(palette.Couleur7);
        jLabel6.setForeground(palette.Couleur7);
        jPanel10.setBackground(palette.Couleur1);
        jLabel13.setBackground(palette.Couleur7);
        jLabel13.setForeground(palette.Couleur7);

        jButton31.setBackground(palette.Couleur2);
        jPanel1.setBackground(palette.Couleur1);
        jCheckBox1.setForeground(palette.Couleur7);
        jCheckBox2.setForeground(palette.Couleur7);
        jCheckBox3.setForeground(palette.Couleur7);
        jCheckBox4.setForeground(palette.Couleur7);
    }
    public void setThemeIcon(){
        byte[] icon_disabled = Configuration.instance().contenuFichier(Configuration.ICON_DISABLED);
        byte[] icon_done = Configuration.instance().contenuFichier(Configuration.ICON_DONE);
        jButton34.setIcon(new javax.swing.ImageIcon(icon_disabled));
        jButton35.setIcon(new javax.swing.ImageIcon(icon_disabled));
        jButton36.setIcon(new javax.swing.ImageIcon(icon_disabled));
        jButton37.setIcon(new javax.swing.ImageIcon(icon_disabled));
        jButton38.setIcon(new javax.swing.ImageIcon(icon_disabled));
        if (palette.colorSheme()==1) {
            jButton34.setIcon(new javax.swing.ImageIcon(icon_done));
        }else if (palette.colorSheme()==2) {
            jButton35.setIcon(new javax.swing.ImageIcon(icon_done));
        }else if (palette.colorSheme()==3) {
            jButton36.setIcon(new javax.swing.ImageIcon(icon_done));
        }else if (palette.colorSheme()==4) {
            jButton37.setIcon(new javax.swing.ImageIcon(icon_done));
        }else if (palette.colorSheme()==5) {
            jButton38.setIcon(new javax.swing.ImageIcon(icon_done));
        }
    }
    public void setSonIcon(){
        if(cc.getSonState()){
            jButton31.setIcon(new javax.swing.ImageIcon(Configuration.instance().contenuFichier(Configuration.ICON_VOLUME_UP)));
        }else{
            jButton31.setIcon(new javax.swing.ImageIcon(Configuration.instance().contenuFichier(Configuration.ICON_VOLUME_OFF)));
        }
    }
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;

}
