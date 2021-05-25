package Vue;

import javax.swing.*;
import java.awt.*;
import java.io.File;


public class Reagles extends javax.swing.JPanel {

    public Reagles(CollecteurEvenements ctrl) {
        initComponents();
    }

    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jTextArea4 = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jTextArea5 = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jTextArea6 = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel2.setBackground(new java.awt.Color(120, 171, 191));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jButton1.setBackground(new java.awt.Color(120, 171, 191));
        jButton1.setText("<");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 56;
        gridBagConstraints.ipady = 35;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(jButton1, gridBagConstraints);

        jLabel1.setBackground(new java.awt.Color(120, 171, 191));
        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("Reagles");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(23, 272, 0, 363);
        jPanel2.add(jLabel1, gridBagConstraints);

        add(jPanel2);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel8.setText("Preparation du jeu");
        jPanel3.add(jLabel8);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.PAGE_AXIS));
        jPanel1.add(jPanel4);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setLineWrap(true);
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.setEditable(false);
        jTextArea2.setText("Disposer sur les pantalons les 48 purs.ions en altrenant les couleurs.\n\nReagles:\n\nChaque joueur choisit sa couleur.\n\nChaque joueur a le droit de jouer avec tous les pions, y compris ceux de l'adversaire.\n\n");
        jPanel1.add(jTextArea2);

        jLabel2.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/img/1.png"))));
        jPanel1.add(jLabel2);

        jPanel3.add(jPanel1);

        jLabel9.setText("But du jeu");
        jPanel3.add(jLabel9);

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.PAGE_AXIS));
        jPanel5.add(jPanel6);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setLineWrap(true);
        jTextArea3.setWrapStyleWord(true);
        jTextArea3.setEditable(false);
        jTextArea3.setText("Posséder un maximum de tours dont le pion supérieur est de la couleur choisie.\n\nUne tournée est constituée de 1 à 5. Jamais plus.\n\nUn joueur est le propriétaire d'une tour mécanisée pion de sa couleur en occupe le sommet.\n\nUn pion isolé est également considéré comme une tour.");
        jPanel5.add(jTextArea3);

        jLabel7.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/img/3.png")))); // NOI18N
        jLabel7.setToolTipText("");
        jPanel5.add(jLabel7);

        jPanel3.add(jPanel5);

        jLabel14.setText("Deplacements");
        jPanel3.add(jLabel14);

        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.LINE_AXIS));

        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.PAGE_AXIS));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setEditable(false);
        jTextArea1.setText("A tour de role, chaque joueur effectue un seul déplacement à partir de n'import quel pioon ou pile de pions. Chacun est donc libre de jouer ou bon lui semble.\n\nUn déplacement consiste en un empiler le ou les pions sur un emplacement directement voisin (diagonale = ok) deja occupe par un ou plusieurs pions (jamais sur un trou vide).\n\n=> Chaque coup joue equivaut donc a liberer un emplacement.\n\n=> Il est interdit de passer au-dessus d'un trou vide.");

        jPanel9.add(jTextArea1);

        jPanel8.add(jPanel9);

        jLabel15.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/img/5.png")))); // NOI18N
        jPanel8.add(jLabel15);

        jPanel3.add(jPanel8);

        jPanel10.setLayout(new javax.swing.BoxLayout(jPanel10, javax.swing.BoxLayout.LINE_AXIS));

        jPanel11.setLayout(new javax.swing.BoxLayout(jPanel11, javax.swing.BoxLayout.PAGE_AXIS));

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jTextArea4.setLineWrap(true);
        jTextArea4.setWrapStyleWord(true);
        jTextArea4.setEditable(false);
        jTextArea4.setText("On deplace Obligatoirement tous les pions situes sur un emplacement (de 1 à 4. Une tour de 5 pions ne deplace pas).\n\nUne pile de pions ne peut augmenter, jamais diminuer.\n\nUn pion ou une tournée, les isoles de tous les cotes, ne pourront donc plus bouger ni changer de propriétaire.");

        jPanel11.add(jTextArea4);

        jPanel10.add(jPanel11);

        jLabel16.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/img/6.png"))));
        jPanel10.add(jLabel16);

        jPanel3.add(jPanel10);

        jLabel17.setText("Fin de la partie");
        jPanel3.add(jLabel17);

        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.LINE_AXIS));

        jPanel13.setLayout(new javax.swing.BoxLayout(jPanel13, javax.swing.BoxLayout.PAGE_AXIS));

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jTextArea5.setLineWrap(true);
        jTextArea5.setWrapStyleWord(true);
        jTextArea5.setEditable(false);
        jTextArea5.setText("La partie se poursuit tant qu'on peut effectuer des empilements. Le jeu se termine lorsque \nplus aucun déplacement n'est possible.\n\nLe plateau n'est donc plus constitué que de tours ou de pions isoles.\n\nSeul le dernier etage compte.\n\n=> Le vainqueur est le joueur totalisant le plus grand nombre de pions isoles ou de sommets de tours de couleur.");

        jPanel13.add(jTextArea5);

        jPanel12.add(jPanel13);

        jLabel18.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/img/7.png"))));
        jPanel12.add(jLabel18);

        jPanel3.add(jPanel12);

        jPanel14.setLayout(new javax.swing.BoxLayout(jPanel14, javax.swing.BoxLayout.LINE_AXIS));

        jPanel15.setLayout(new javax.swing.BoxLayout(jPanel15, javax.swing.BoxLayout.PAGE_AXIS));

        jTextArea6.setColumns(20);
        jTextArea6.setRows(5);
        jTextArea6.setLineWrap(true);
        jTextArea6.setWrapStyleWord(true);
        jTextArea6.setEditable(false);
        jTextArea6.setText("=> Qu'une tour compte 1,2 ... ou 5 pions, elle vaut toujours UN point.\n\nEn cas d'égalité, c'est celui qui a le plus de tours de 5 pions (avec le pion de sa couleur au sommet) qui a gagné.\n\nBon jeu!");

        jPanel15.add(jTextArea6);

        jPanel14.add(jPanel15);

        jLabel19.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/img/4.png")))); // NOI18N
        jPanel14.add(jLabel19);

        jPanel3.add(jPanel14);

        jLabel20.setText("Demo");
        jPanel3.add(jLabel20);

        jPanel16.setLayout(new java.awt.GridLayout(1, 3));

        jPanel7.setLayout(new java.awt.GridLayout(2, 1));

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("jLabel21");
        jPanel7.add(jLabel23);

        jButton2.setText("jButton2");
        jPanel7.add(jButton2);

        jPanel16.add(jPanel7);

        jPanel18.setLayout(new java.awt.GridLayout(2, 1));

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("jLabel21");
        jPanel18.add(jLabel22);

        jButton3.setText("jButton3");
        jPanel18.add(jButton3);

        jPanel16.add(jPanel18);

        jPanel19.setLayout(new java.awt.GridLayout(2, 1));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("jLabel21");
        jPanel19.add(jLabel21);

        jButton4.setText("jButton4");
        jPanel19.add(jButton4);

        jPanel16.add(jPanel19);

        jPanel3.add(jPanel16);

        jScrollPane1.setViewportView(jPanel3);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jScrollPane1.getVerticalScrollBar().setValue(0);
                jScrollPane1.getVerticalScrollBar().setUnitIncrement(15);
            }
        });
        add(jScrollPane1);
    }// </editor-fold>

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }


    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    // End of variables declaration
}
