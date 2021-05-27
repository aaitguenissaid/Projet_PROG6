package Vue;

import Modele.PaletteDeCouleurs;

import javax.swing.*;
import java.awt.*;
import java.io.File;

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
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jLabel1 = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        setToutCouleurs();
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("Reagles");
        jPanel2.add(jLabel1);
        jPanel2.add(filler2);

        add(jPanel2);
        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.PAGE_AXIS));
        jPanel8.add(addImageToPanel("12"));
        jPanel8.add(addImageToPanel("1"));
        jPanel8.add(addTextToPanel("<html><h1 style='color:"+fontColor+";padding:5px;' >Préparation du jeu</h1></html>"));
        jPanel8.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">Disposer sur les trous les 48 pions en alternant les couleurs.</p></html>"));
        jPanel8.add(addImageToPanel("2"));
        jPanel8.add(addTextToPanel("<html><h1 style='color:"+fontColor+";padding:5px;'>Régles</h1></html>"));
        jPanel8.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">Chaque joueur choisit sa couleur.</p></html>"));
        jPanel8.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">Chaque joueur a le droit de jouer avec tous les pions, y compris ceux de l'adversaire.</p></html>"));
        jPanel8.add(addTextToPanel("<html><h1 style='color:"+fontColor+";padding:5px;'>But du jeu</h1></html>"));
        jPanel8.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">Posséder un maximum de tours dont le pion supérieur est de la couleur choisie.</p></html>"));
        jPanel8.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">Une tour est constituée de 1 à 5 pions. Jamais plus.</p></html>"));
        jPanel8.add(addImageToPanel("3"));
        jPanel8.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">Un joueur est le propriétaire d’une tour lorsqu’un pion de sa couleur en occupe le sommet.</p></html>"));
        jPanel8.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">Un pion isolé est également considéré comme une tour.</p></html>"));
        jPanel8.add(addImageToPanel("4"));
        jPanel8.add(addTextToPanel("<html><h1 style='color:"+fontColor+";padding:5px;'>Déplacements</h1></html>"));
        jPanel8.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">A tour de rôle, chaque joueur effectue un seul déplacement à partir de n‘importe quel pion ou pile de pions. Chacun est donc libre de jouer où bon lui semble.</p></html>"));
        jPanel8.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">Un déplacement consiste à empiler le ou les pions sur un emplacement directement voisin (diagonale = ok) déjà occupé par un ou plusieurs pions (jamais sur un trou vide).</p></html>"));
        jPanel8.add(addImageToPanel("5"));
        jPanel8.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">=> Chaque coup joué équivaut donc à libérer un emplacement.</p></html>"));
        jPanel8.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">=> Il est interdit de passer au-dessus d’un trou vide.</p></html>"));
        jPanel8.add(addImageToPanel("6"));
        jPanel8.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">On déplace obligatoirement tous les pions situés sur un emplacement (de 1 a 4. Une tour de 5 pions ne se déplace pas).</p></html>"));
        jPanel8.add(addImageToPanel("7"));
        jPanel8.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">Une pile de pions ne peut qu’augmenter, jamais diminuer.</p></html>"));
        jPanel8.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">Un pion ou une tour, isolés de tous les côtés, ne pourront donc plus bouger ni changer de propriétaire.</p></html>"));
        jPanel8.add(addTextToPanel("<html><h1 style='color:"+fontColor+";padding:5px;'>Fin de la partie</h1></html>"));
        jPanel8.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">La partie se poursuit tant qu’on peut effectuer des empilements. Le jeu se termine lorsque plus aucun déplacement n’est possible. Seul le dernier étage compte.</p></html>"));
        jPanel8.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">=> Le vainqueur est le joueur totalisant le plus grand nombre de pions isolés ou de sommets de tours de sa couleur.</p></html>"));
        jPanel8.add(addImageToPanel("8"));
        jPanel8.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">= Qu’une tour compte 1,2... ou 5 pions, elle vaut toujours UN point.</p></html>"));
        jPanel8.add(addTextToPanel("<html><p style=\"color:"+fontColor+";padding:10px; text-align: justify;  text-justify: inter-word;\">En cas d’égalité, c'est celui qui a le plus de tours de 5 pions (avec le pion de sa couleur au sommet) qui a gagné.</p></html>"));
        jScrollPane5.setViewportView(jPanel8);
        jScrollPane5.getVerticalScrollBar().setUnitIncrement(16);
        add(jScrollPane5);
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        cc.reaglesBack();
    }
    JPanel addImageToPanel(String nom){
        JPanel panel= new JPanel();
        panel.setBackground(palette.Couleur1);
        panel.setLayout(new java.awt.GridLayout());
        JLabel label = new JLabel();
        label.setBackground(palette.Couleur1);
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/img/"+nom+".png"))));
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
        jPanel2.setBackground(palette.Couleur1);
        jButton1.setBackground(palette.Couleur1);
        jLabel1.setBackground(palette.Couleur1);
        jPanel8.setBackground(palette.Couleur1);
        fontColor = String.format("#%02x%02x%02x", palette.Couleur3.getRed(), palette.Couleur3.getGreen(), palette.Couleur3.getBlue());
    }
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane5;
}
