package Vue;

import Global.Configuration;
import Modele.Jeu;
import Modele.PaletteDeCouleurs;

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
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
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
        jPanel10 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        setToutCouleurs();
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));


        jButton1.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/icons/outline_arrow_back_ios_black_24dp.png"))));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel2.add(jButton1);
        jPanel2.add(filler1);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18));
        jLabel1.setText("Parametres");
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
        jCheckBox1.setSelected(!Boolean.parseBoolean(Configuration.instance().get(Configuration.RELANCE_AUTOMATIQUE)));
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

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("IA");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel7.add(jLabel3);

        jPanel3.setAlignmentX(0.0F);
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setText("Choisissez la complexité de l'intelligence artificielle :");
        jPanel3.add(jLabel2);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(Configuration.instance().lis("IA_names").split(",")));
        jComboBox2.setAlignmentX(0.0F);
        jComboBox2.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jComboBox2.setMaximumSize(new java.awt.Dimension(91, 32));
        jComboBox2.setSelectedItem(Configuration.instance().get(Configuration.IA_AFFRONTEMENT));
        jPanel3.add(jComboBox2);

        jPanel7.add(jPanel3);

        jPanel4.add(jPanel7);

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

    private void jCheckBox3ActionPerformed(ActionEvent evt) {
        Boolean b =Boolean.parseBoolean(Configuration.instance().get(Configuration.EST_AUTORISE_SUGGESTION));
        b=!b;
        Configuration.instance().set("estAutoriseSuggestion",b.toString());
    }

    private void jCheckBox2ActionPerformed(ActionEvent evt) {
        Boolean b =Boolean.parseBoolean(Configuration.instance().get(Configuration.EST_AUTORISE_HISTORIQUE));
        b=!b;
        Configuration.instance().set("estAutoriseHistorique",b.toString());
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here: back
        cc.revalidateInterface();
        cc.mainmenu();
    }

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        cc.enregistrer_la_partie();
    }

    private void jButton34MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here: Theme1
        palette.setSheme1();
        Configuration.instance().set(Configuration.COULEUR_THEME, "1");
        cc.revalidateInterface();
    }

    private void jButton35MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:Theme2
        palette.setSheme2();
        Configuration.instance().set(Configuration.COULEUR_THEME, "2");
        cc.revalidateInterface();
    }

    private void jButton36MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:Theme3
        palette.setSheme3();
        Configuration.instance().set(Configuration.COULEUR_THEME, "3");
        cc.revalidateInterface();
    }

    private void jButton37MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:Theme4
        palette.setSheme4();
        Configuration.instance().set(Configuration.COULEUR_THEME, "4");
        cc.revalidateInterface();
    }

    private void jButton38MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:Theme5
        palette.setSheme5();
        Configuration.instance().set(Configuration.COULEUR_THEME, "5");
        cc.revalidateInterface();
    }

    private void jButton31MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here: Sound1
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
        jLabel1.setBackground(palette.Couleur7);
        jPanel4.setBackground(palette.Couleur1);
        jPanel6.setBackground(palette.Couleur1);
        jLabel4.setBackground(palette.Couleur7);
        jCheckBox1.setBackground(palette.Couleur1);
        jCheckBox2.setBackground(palette.Couleur1);
        jCheckBox3.setBackground(palette.Couleur1);
        jPanel7.setBackground(palette.Couleur1);
        jLabel3.setBackground(palette.Couleur7);
        jPanel3.setBackground(palette.Couleur1);
        jLabel2.setBackground(palette.Couleur7);
        jComboBox2.setBackground(palette.Couleur1);
        jPanel5.setBackground(palette.Couleur1);
        jLabel5.setBackground(palette.Couleur7);
        jPanel8.setBackground(palette.Couleur1);
        jButton34.setBackground(new java.awt.Color(2, 172, 229));
        jButton35.setBackground(new java.awt.Color(43, 215, 83));
        jButton36.setBackground(new java.awt.Color(227, 68, 58));
        jButton37.setBackground(new java.awt.Color(2, 172, 229));
        jButton38.setBackground(new java.awt.Color(43, 215, 83));
        jPanel14.setBackground(palette.Couleur1);
        jPanel9.setBackground(palette.Couleur1);
        jLabel6.setBackground(palette.Couleur7);
        jPanel10.setBackground(palette.Couleur1);
        jLabel13.setBackground(palette.Couleur7);
        jButton31.setBackground(new java.awt.Color(2, 172, 229));
        jButton32.setBackground(new java.awt.Color(43, 215, 83));
        jButton33.setBackground(new java.awt.Color(227, 68, 58));
        jPanel1.setBackground(palette.Couleur1);
    }
    public void setThemeIcon(){
        jButton34.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/icons/outline_disabled_by_default_black_24dp.png"))));
        jButton35.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/icons/outline_disabled_by_default_black_24dp.png"))));
        jButton36.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/icons/outline_disabled_by_default_black_24dp.png"))));
        jButton37.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/icons/outline_disabled_by_default_black_24dp.png"))));
        jButton38.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/icons/outline_disabled_by_default_black_24dp.png"))));
        if (palette.colorSheme()==1) {
            jButton34.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/icons/outline_done_black_24dp.png"))));
        }else if (palette.colorSheme()==2) {
            jButton35.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/icons/outline_done_black_24dp.png"))));
        }else if (palette.colorSheme()==3) {
            jButton36.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/icons/outline_done_black_24dp.png"))));
        }else if (palette.colorSheme()==4) {
            jButton37.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/icons/outline_done_black_24dp.png"))));
        }else if (palette.colorSheme()==5) {
            jButton38.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/icons/outline_done_black_24dp.png"))));
        }
    }
    public void setSonIcon(){
        if(cc.getSonState()){
            jButton31.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/icons/outline_volume_up_black_24dp.png"))));
        }else{
            jButton31.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/icons/outline_volume_off_black_24dp.png"))));
        }
    }
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
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
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;

}
