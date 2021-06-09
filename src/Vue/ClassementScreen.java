package Vue;

import Global.Configuration;
import Modele.Classement;
import Modele.Jeu;
import Modele.PaletteDeCouleurs;
import Modele.Score;
import Structures.FAPListe;
import Structures.Iterateur;

import javax.swing.*;
import java.awt.*;

public class ClassementScreen extends javax.swing.JPanel {
    CollecteurEvenements cc;
    PaletteDeCouleurs palette;
    String fontColor;
    Iterateur<Score> it;
    public ClassementScreen(CollecteurEvenements ctrl) {
        cc=ctrl;
        palette = cc.getPalette();
        initComponents();
    }
    void initComponents() {
        removeAll();
        it = cc.getClassement().getList().iterateur();
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

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("Classement");
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
        jPanel4.add(addTitleToPanel());
        while (it.aProchain()) {
            Score s =  it.prochain();
            jPanel4.add(addLineToPanel(s.getPseudo(), s.getNbVictoires(), s.getNbParties(), s.getLesPoints()));
        }
        jPanel1.add(jPanel4, gridBagConstraints);
        jScrollPane5.setViewportView(jPanel1);
        jScrollPane5.getVerticalScrollBar().setUnitIncrement(16);

        add(jScrollPane5);
    }


    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        cc.mainmenu();
    }

    JPanel addLineToPanel(String nom, int nbVictoires, int nbParties, int victoires){

        JPanel jPanel6 =new JPanel();
        jPanel6.setBackground(palette.Couleur1);
        JLabel jLabel4 = new JLabel();
        JLabel jLabel5 = new JLabel();
        JLabel jLabel6 = new JLabel();
        JLabel jLabel7 = new JLabel();
        jLabel4.setBackground(palette.Couleur1);
        jLabel5.setBackground(palette.Couleur1);
        jLabel6.setBackground(palette.Couleur1);
        jLabel7.setBackground(palette.Couleur1);
        jLabel4.setForeground(palette.Couleur7);
        jLabel5.setForeground(palette.Couleur7);
        jLabel6.setForeground(palette.Couleur7);
        jLabel7.setForeground(palette.Couleur7);
        jPanel6.setLayout(new GridLayout(1, 4));//new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        jLabel4.setText(nom);
        jPanel6.add(jLabel4);
        jPanel6.add(new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0)));

        jLabel5.setText(String.valueOf(nbVictoires));
        jPanel6.add(jLabel5);

        jPanel6.add(new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0)));
        jLabel6.setText(String.valueOf(nbParties));
        jPanel6.add(jLabel6);

        jPanel6.add(new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0)));
        jLabel7.setText(String.valueOf(victoires));
        jPanel6.add(jLabel7);

        return jPanel6;
    }
    JPanel addTitleToPanel(){
        JPanel jPanel6 =new JPanel();
        jPanel6.setBackground(palette.Couleur1);
        JLabel jLabel4 = new JLabel();
        JLabel jLabel5 = new JLabel();
        JLabel jLabel6 = new JLabel();
        JLabel jLabel7 = new JLabel();
        jLabel4.setBackground(palette.Couleur1);
        jLabel5.setBackground(palette.Couleur1);
        jLabel6.setBackground(palette.Couleur1);
        jLabel7.setBackground(palette.Couleur1);
        jLabel4.setForeground(palette.Couleur7);
        jLabel5.setForeground(palette.Couleur7);
        jLabel6.setForeground(palette.Couleur7);
        jLabel7.setForeground(palette.Couleur7);
        jPanel6.setLayout(new GridLayout(1, 4));//new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        jLabel4.setText("Nom du joueur   ");
        jPanel6.add(jLabel4);
        jPanel6.add(new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0)));

        jLabel5.setText("Nombre victoires   ");
        jPanel6.add(jLabel5);

        jPanel6.add(new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0)));
        jLabel6.setText("Nombre Parties   ");
        jPanel6.add(jLabel6);

        jPanel6.add(new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0)));
        jLabel7.setText("Points");
        jPanel6.add(jLabel7);

        return jPanel6;
    }
    public void setToutCouleurs(){
        jPanel1.setBackground(palette.Couleur1);
        jPanel2.setBackground(palette.Couleur1);
        jButton1.setBackground(palette.Couleur1);
        jLabel1.setBackground(palette.Couleur1);
        jLabel1.setForeground(palette.Couleur7);
        jPanel4.setBackground(palette.Couleur1);
        jPanel5.setBackground(palette.Couleur1);
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
