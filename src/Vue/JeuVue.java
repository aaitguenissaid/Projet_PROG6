package Vue;

import Global.Configuration;
import Modele.PaletteDeCouleurs;
import Structures.Mouvement;

import java.awt.*;
import java.io.File;

public class JeuVue extends javax.swing.JPanel {
    CollecteurEvenements cc;
    AireDeDessin a;
    PaletteDeCouleurs palette;
    public JeuVue(CollecteurEvenements ctrl, AireDeDessin comp) {
        cc=ctrl;
        a=comp;
        palette = cc.getPalette();
        initComponents();
    }

    void initComponents() {
        removeAll();

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jLabel1 = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jPanel7 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanel12 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanel13 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        setToutCouleurs();
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));
        h=new HistoriqueTimeLine(a.jeu.getHistorique());
        cc.setHistAffControl(h);
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jButton1.setIcon(new javax.swing.ImageIcon(Configuration.instance().contenuFichier(Configuration.ICON_BACK)));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel1.add(jButton1);
        jPanel1.add(filler1);

        jLabel1.setFont(new java.awt.Font("Ubuntu", Font.BOLD, 18)); // NOI18N
        jLabel1.setText("Jeu Avalam");
        jPanel1.add(jLabel1);
        jPanel1.add(filler2);

        jButton2.setIcon(new javax.swing.ImageIcon(Configuration.instance().contenuFichier(Configuration.ICON_HELP)));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(evt -> jButton2ActionPerformed(evt));
        jPanel1.add(jButton2);

        add(jPanel1);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jPanel3.setLayout(new java.awt.CardLayout());


        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 508, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 421, Short.MAX_VALUE)
        );

        jPanel3.add(a, "card2");

        jPanel2.add(jPanel3);

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));
        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel2.setText("Tour : ");
        jLabel2.setToolTipText("");
        jPanel5.add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel3.setText("X"); //Nom Joueur
        jPanel5.add(jLabel3);

        jPanel4.add(jPanel5);
        jPanel4.add(filler3);

        jPanel6.setLayout(new java.awt.GridLayout());


        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Historique:");
        jPanel6.add(jLabel4);

        jPanel4.add(jPanel6);

        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.LINE_AXIS));

        jButton3.setIcon(new javax.swing.ImageIcon(Configuration.instance().contenuFichier(Configuration.ICON_SKIP_PREVIOUS)));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(Boolean.parseBoolean(Configuration.instance().get(Configuration.EST_AUTORISE_HISTORIQUE)))
                    jButton3MouseClicked(evt);
            }
        });
        jPanel9.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(Configuration.instance().contenuFichier(Configuration.ICON_ARROW_LEFT)));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(Boolean.parseBoolean(Configuration.instance().get(Configuration.EST_AUTORISE_HISTORIQUE)))
                    jButton4MouseClicked(evt);
            }
        });
        jPanel9.add(jButton4);

        jButton9.setIcon(new javax.swing.ImageIcon(Configuration.instance().contenuFichier(Configuration.ICON_PLAY)));
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(Boolean.parseBoolean(Configuration.instance().get(Configuration.EST_AUTORISE_HISTORIQUE)))
                    jButton9MouseClicked(evt);
            }
        });
        jPanel9.add(jButton9);

        jButton10.setIcon(new javax.swing.ImageIcon(Configuration.instance().contenuFichier(Configuration.ICON_ARROW_RIGHT)));
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(Boolean.parseBoolean(Configuration.instance().get(Configuration.EST_AUTORISE_HISTORIQUE)))
                    jButton10MouseClicked(evt);
            }
        });
        jPanel9.add(jButton10);

        jButton11.setIcon(new javax.swing.ImageIcon(Configuration.instance().contenuFichier(Configuration.ICON_SKIP_NEXT)));
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(Boolean.parseBoolean(Configuration.instance().get(Configuration.EST_AUTORISE_HISTORIQUE)))
                    jButton11MouseClicked(evt);
            }
        });
        jPanel9.add(jButton11);

        jPanel4.add(jPanel9);
        h.setHistorique(cc.getJeu().getHistorique());
        jPanel4.add(h);
        jPanel4.add(filler6);

        jPanel7.setLayout(new java.awt.GridLayout(4, 0));

        jButton5.setText("Relancer une partie");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jPanel7.add(jButton5);

        jButton6.setText("Abandonner");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        jPanel7.add(jButton6);

        jButton7.setText("Suggestion");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(Boolean.parseBoolean(Configuration.instance().get(Configuration.EST_AUTORISE_SUGGESTION)))
                    jButton7MouseClicked(evt);
            }
        });
        jPanel7.add(jButton7);

        jButton8.setText("Sauvegarder la partie");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });
        jPanel7.add(jButton8);

        jPanel4.add(jPanel7);
        jPanel4.add(filler5);

        jPanel10.setLayout(new java.awt.GridLayout());

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Statistiques");
        jPanel10.add(jLabel5);

        jPanel4.add(jPanel10);

        jPanel11.setLayout(new javax.swing.BoxLayout(jPanel11, javax.swing.BoxLayout.LINE_AXIS));
        jPanel11.add(filler8);

        jPanel12.setLayout(new java.awt.GridLayout(2, 1));

        jLabel6.setText("jLabel6");
        jPanel12.add(jLabel6);

        jLabel8.setText("jLabel8");
        jPanel12.add(jLabel8);

        jPanel11.add(jPanel12);
        jPanel11.add(filler9);

        jPanel13.setLayout(new java.awt.GridLayout(2, 1));

        jLabel7.setText("jLabel7");
        jPanel13.add(jLabel7);

        jLabel9.setText("jLabel9");
        jPanel13.add(jLabel9);

        jPanel11.add(jPanel13);
        jPanel11.add(filler10);

        jPanel4.add(jPanel11);

        jPanel2.add(jPanel4);

        add(jPanel2);

    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        cc.reagles();
    }

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here: Reagles
        cc.reagles();
    }

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:|<
        cc.last_historique();
        h.setHistorique(cc.getJeu().getHistorique());
        h.repaint();
    }

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:<
        cc.next_historique();
        h.setHistorique(cc.getJeu().getHistorique());
        h.repaint();
    }

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here: Relancer une partie
        cc.relancerPartie();
    }

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here: Abandonner
        cc.abandonner();
    }

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        cc.demanderSuggestion();
        }

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        cc.enregistrer_la_partie();
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here: Back
        cc.mainmenu();
    }

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here: Stop historique []
        cc.play_pause_historique();
        h.setHistorique(cc.getJeu().getHistorique());
        h.repaint();
    }

    private void jButton10MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:>
        cc.previous_historique();
        h.setHistorique(cc.getJeu().getHistorique());
        h.repaint();
    }

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:>|
        cc.first_historique();
        h.setHistorique(cc.getJeu().getHistorique());
        h.repaint();
    }

    public void pauseBoutonHistorique() {
        jButton9.setIcon(new javax.swing.ImageIcon(Configuration.instance().contenuFichier(Configuration.ICON_PAUSE)));
    }
    public void playBoutonHistorique() {
        jButton9.setIcon(new javax.swing.ImageIcon(Configuration.instance().contenuFichier(Configuration.ICON_PLAY)));
    }
    public void eteindreHistorique(){
        jButton3.setEnabled(false);
        jButton4.setEnabled(false);
        jButton9.setEnabled(false);
        jButton10.setEnabled(false);
        jButton11.setEnabled(false);
    }
    public void allumerHistorique(){
        jButton3.setEnabled(true);
        jButton4.setEnabled(true);
        jButton9.setEnabled(true);
        jButton10.setEnabled(true);
        jButton11.setEnabled(true);
    }
    public void eteindreSuggestion(){
        jButton7.setEnabled(false);
    }
    public void allumerSuggestion(){
        jButton7.setEnabled(true);
    }
    public void eteindreRelancerPartie() {jButton5.setEnabled(false);}
    public void allumerRelancerPartie() {jButton5.setEnabled(true);}
    public void eteindreAbandonner() {jButton6.setEnabled(false);}
    public void allumerAbandonner() {jButton6.setEnabled(true);}
    public void setNomDeJ1(String nom){
        jLabel6.setText(nom);
    }
    public void setNomDeJ2(String nom){
        jLabel7.setText(nom);
    }
    public void setScoreJ1(String text){
        jLabel8.setText(text);
    }
    public void setScoreJ2(String text){
        jLabel9.setText(text);
    }
    public void setTour(String text,Color c){
        jLabel3.setText(text);
        jLabel3.setForeground(c);
    }
    public void setToutCouleurs(){
        setBackground(palette.Couleur1);
        jPanel1.setBackground(palette.Couleur1);
        jButton1.setBackground(palette.Couleur1);
        jLabel1.setForeground(palette.Couleur7);
        jButton2.setBackground(palette.Couleur1);
        jPanel2.setBackground(palette.Couleur1);
        jPanel3.setBackground(palette.Couleur1);
        jPanel8.setBackground(palette.Couleur1);
        jPanel4.setBackground(palette.Couleur6);
        jPanel5.setBackground(palette.Couleur6);
        jLabel2.setBackground(palette.Couleur7);
        jLabel2.setForeground(palette.Couleur7);
        jLabel3.setBackground(palette.Couleur1);
        jPanel6.setBackground(palette.Couleur6);
        jLabel4.setBackground(palette.Couleur1);
        jLabel4.setForeground(palette.Couleur7);
        jPanel9.setBackground(palette.Couleur1);
        jButton3.setBackground(palette.Couleur2);
        jButton4.setBackground(palette.Couleur2);
        jButton9.setBackground(palette.Couleur2);
        jButton10.setBackground(palette.Couleur2);
        jButton11.setBackground(palette.Couleur2);
        jButton5.setBackground(palette.Couleur2);
        jButton5.setForeground(palette.Couleur7);
        jButton6.setBackground(palette.Couleur2);
        jButton6.setForeground(palette.Couleur7);
        jButton7.setBackground(palette.Couleur2);
        jButton7.setForeground(palette.Couleur7);
        jButton8.setBackground(palette.Couleur2);
        jButton8.setForeground(palette.Couleur7);
        jPanel10.setBackground(palette.Couleur6);
        jLabel5.setBackground(palette.Couleur1);
        jPanel11.setBackground(palette.Couleur6);
        jPanel12.setBackground(palette.Couleur6);
        jLabel6.setBackground(palette.Couleur7);
        jLabel8.setBackground(palette.Couleur1);
        jPanel13.setBackground(palette.Couleur6);
        jLabel7.setBackground(palette.Couleur1);
        jLabel9.setBackground(palette.Couleur1);
        jLabel1.setForeground(palette.Couleur7);
        jLabel2.setForeground(palette.Couleur7);
        jLabel4.setForeground(palette.Couleur7);
        jLabel5.setForeground(palette.Couleur7);
        jLabel6.setForeground(palette.Couleur3);
        jLabel7.setForeground(palette.Couleur4);
        jLabel8.setForeground(palette.Couleur7);
        jLabel9.setForeground(palette.Couleur7);
        if(Boolean.parseBoolean(Configuration.instance().get(Configuration.EST_AUTORISE_HISTORIQUE))){
            allumerHistorique();
        }else{
            eteindreHistorique();
        }

        if(Boolean.parseBoolean(Configuration.instance().get(Configuration.EST_AUTORISE_SUGGESTION))){
            allumerSuggestion();
        }else{
            eteindreSuggestion();
        }
    }
    // Variables declaration - do not modify
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private HistoriqueTimeLine h;

}
