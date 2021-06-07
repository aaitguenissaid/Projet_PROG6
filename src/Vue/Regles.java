package Vue;

import Global.Configuration;
import Modele.PaletteDeCouleurs;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;

public class Regles extends javax.swing.JPanel {
    CollecteurEvenements cc;
    PaletteDeCouleurs palette;
    String fontColor;
    public Regles(CollecteurEvenements ctrl) {
        cc=ctrl;
        palette = cc.getPalette();
        initComponents();
    }
    void initComponents() {
        removeAll();
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jLabel1 = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        setToutCouleurs();
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));



        jPanel4.add(jPanel3);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jButton1.setIcon(new javax.swing.ImageIcon(Configuration.instance().contenuFichier(Configuration.ICON_BACK)));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel2.add(jButton1);
        jPanel2.add(filler1);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("Reagles");
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.8;
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.PAGE_AXIS));
        jPanel4.add(addImageToPanel("img12"));
        jPanel4.add(addImageToPanel("img1"));
        jPanel4.add(addTextToPanel("<html><h1 style='color:"+fontColor+";padding:5px;' >Préparation du jeu</h1></html>"));
        jPanel4.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">Disposer sur les trous les 48 pions en alternant les couleurs.</p></html>"));
        jPanel4.add(addImageToPanel("img2"));
        jPanel4.add(addTextToPanel("<html><h1 style='color:"+fontColor+";padding:5px;'>Régles</h1></html>"));
        jPanel4.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">Chaque joueur choisit sa couleur.</p></html>"));
        jPanel4.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">Chaque joueur a le droit de jouer avec tous les pions, y compris ceux de l'adversaire.</p></html>"));
        jPanel4.add(addTextToPanel("<html><h1 style='color:"+fontColor+";padding:5px;'>But du jeu</h1></html>"));
        jPanel4.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">Posséder un maximum de tours dont le pion supérieur est de la couleur choisie.</p></html>"));
        jPanel4.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">Une tour est constituée de 1 à 5 pions. Jamais plus.</p></html>"));
        jPanel4.add(addImageToPanel("img3"));
        jPanel4.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">Un joueur est le propriétaire d’une tour lorsqu’un pion de sa couleur en occupe le sommet.</p></html>"));
        jPanel4.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">Un pion isolé est également considéré comme une tour.</p></html>"));
        jPanel4.add(addImageToPanel("img4"));
        jPanel4.add(addTextToPanel("<html><h1 style='color:"+fontColor+";padding:5px;'>Déplacements</h1></html>"));
        jPanel4.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">A tour de rôle, chaque joueur effectue un seul déplacement à partir de n‘importe quel pion ou pile de pions. Chacun est donc libre de jouer où bon lui semble.</p></html>"));
        jPanel4.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">Un déplacement consiste à empiler le ou les pions sur un emplacement directement voisin (diagonale = ok) déjà occupé par un ou plusieurs pions (jamais sur un trou vide).</p></html>"));
        jPanel4.add(addImageToPanel("img5"));
        jPanel4.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">=> Chaque coup joué équivaut donc à libérer un emplacement.</p></html>"));
        jPanel4.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">=> Il est interdit de passer au-dessus d’un trou vide.</p></html>"));
        jPanel4.add(addImageToPanel("img6"));
        jPanel4.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">On déplace obligatoirement tous les pions situés sur un emplacement (de 1 a 4. Une tour de 5 pions ne se déplace pas).</p></html>"));
        jPanel4.add(addImageToPanel("img7"));
        jPanel4.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">Une pile de pions ne peut qu’augmenter, jamais diminuer.</p></html>"));
        jPanel4.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">Un pion ou une tour, isolés de tous les côtés, ne pourront donc plus bouger ni changer de propriétaire.</p></html>"));
        jPanel4.add(addTextToPanel("<html><h1 style='color:"+fontColor+";padding:5px;'>Fin de la partie</h1></html>"));
        jPanel4.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">La partie se poursuit tant qu’on peut effectuer des empilements. Le jeu se termine lorsque plus aucun déplacement n’est possible. Seul le dernier étage compte.</p></html>"));
        jPanel4.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">=> Le vainqueur est le joueur totalisant le plus grand nombre de pions isolés ou de sommets de tours de sa couleur.</p></html>"));
        jPanel4.add(addImageToPanel("img8"));
        jPanel4.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">=> Qu’une tour compte 1,2... ou 5 pions, elle vaut toujours UN point.</p></html>"));
        jPanel4.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">En cas d’égalité, c'est celui qui a le plus de tours de 5 pions (avec le pion de sa couleur au sommet) qui a gagné.</p></html>"));


        jPanel4.setPreferredSize(new Dimension(750,3200));
        jPanel1.add(jPanel4, gridBagConstraints);
        jScrollPane5.setViewportView(jPanel1);
        jScrollPane5.getVerticalScrollBar().setUnitIncrement(16);

        add(jScrollPane5);
    }


    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        cc.reaglesBack();
    }

    JPanel addImageToPanel(String nom){
        JPanel panel= new JPanel();
        panel.setBackground(palette.Couleur1);
        panel.setLayout(new java.awt.GridLayout());
        JLabel label = new JLabel();
        label.setBackground(palette.Couleur1);
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label.setIcon(new javax.swing.ImageIcon(Configuration.instance().contenuFichier(nom)));
        panel.add(label);
        return panel;
    }
    JPanel addTextToPanel(String text){
        JPanel panel= new JPanel();
        panel.setBackground(palette.Couleur1);
        panel.setLayout(new java.awt.GridLayout());
        JLabel label = new JLabel();
        label.setBackground(palette.Couleur1);
        label.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label.setText(text);
        label.setPreferredSize(new Dimension(getPreferredSize().width,label.getPreferredSize().height));
        panel.add(label);
        return panel;
    }
    public void setToutCouleurs(){
        jPanel1.setBackground(palette.Couleur1);
        jPanel2.setBackground(palette.Couleur1);
        jButton1.setBackground(palette.Couleur1);
        jLabel1.setBackground(palette.Couleur1);
        jLabel1.setForeground(palette.Couleur7);
        jPanel4.setBackground(palette.Couleur1);
        jPanel3.setBackground(palette.Couleur1);
        jPanel5.setBackground(palette.Couleur1);
        jLabel2.setBackground(palette.Couleur1);
        jLabel2.setForeground(palette.Couleur7);
        jLabel3.setForeground(palette.Couleur7);
        jLabel3.setBackground(palette.Couleur1);
        fontColor = String.format("#%02x%02x%02x", palette.Couleur3.getRed(), palette.Couleur3.getGreen(), palette.Couleur3.getBlue());
    }
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane5;
}
