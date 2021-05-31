package Vue;

import Modele.PaletteDeCouleurs;

import java.io.File;

public class MainMenu extends javax.swing.JPanel {
    CollecteurEvenements cc;
    PaletteDeCouleurs palette;
    public MainMenu(CollecteurEvenements ctrl) {
        cc=ctrl;
        palette = cc.getPalette();
        initComponents();
    }
    void initComponents() {
        removeAll();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        filler = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        setToutCouleurs();
        setLayout(new java.awt.GridLayout(8, 3));
        grid_insérer_des_cellules_vides(1);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Avalam");
        add(jLabel1);

        jPanel1.setLayout(new java.awt.GridLayout(1, 5, 5, 5));
        jPanel1.add(filler);
        jPanel1.add(filler);

        jButton2.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/icons/outline_settings_black_24dp.png")))); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jPanel1.add(jButton2);

        jButton1.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/icons/outline_account_circle_black_24dp.png")))); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel1.add(jButton1);

        add(jPanel1);


        grid_insérer_des_cellules_vides(4);
        jButton3.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jButton3.setText("Jouer en local");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        add(jButton3);
        grid_insérer_des_cellules_vides(5);

        jButton4.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jButton4.setText("Reprendre une partie");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        add(jButton4);
        grid_insérer_des_cellules_vides(5);

        jButton5.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jButton5.setText("Règles du jeu");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        add(jButton5);
        grid_insérer_des_cellules_vides(4);
    }
    private void grid_insérer_des_cellules_vides (int k) {
        for(int i=0;i<k;i++){
            add(new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767)));
        }
    }

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {
        cc.parametres();
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:Acount
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NomsDeJoueurs dialog = new NomsDeJoueurs(new javax.swing.JFrame(), true,cc);
                dialog.setVisible(true);
            }
        });
    }

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DebutJeuDialog dialog = new DebutJeuDialog(new javax.swing.JFrame(), true,cc);
                dialog.setVisible(true);
            }
        });
    }

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {
        cc.reprendre_une_partie();
    }

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {
        cc.reagles();
    }
    public void setToutCouleurs(){
        setBackground(palette.Couleur1);
        jPanel1.setBackground(palette.Couleur1);
        jLabel1.setForeground(palette.Couleur3);
        jButton2.setBackground(palette.Couleur1);
        jButton1.setBackground(palette.Couleur1);
        jButton3.setBackground(palette.Couleur2);
        jButton3.setForeground(palette.Couleur3);
        jButton4.setBackground(palette.Couleur2);
        jButton4.setForeground(palette.Couleur3);
        jButton5.setBackground(palette.Couleur2);
        jButton5.setForeground(palette.Couleur3);
    }
    private javax.swing.Box.Filler filler;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
}
