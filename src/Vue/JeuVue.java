package Vue;

import java.io.File;

public class JeuVue extends javax.swing.JPanel {
        CollecteurEvenements cc;
        AireDeDessin a;
        public JeuVue(CollecteurEvenements ctrl, AireDeDessin comp) {
            cc=ctrl;
            a=comp;
            initComponents();
        }
        private void initComponents() {
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
            jButton3 = new javax.swing.JButton();
            jButton4 = new javax.swing.JButton();
            filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
            jPanel7 = new javax.swing.JPanel();
            jButton5 = new javax.swing.JButton();
            jButton6 = new javax.swing.JButton();
            jButton7 = new javax.swing.JButton();
            jButton8 = new javax.swing.JButton();
            filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));

            setBackground(new java.awt.Color(120, 171, 191));
            setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

            jPanel1.setBackground(new java.awt.Color(120, 171, 191));
            jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

            jButton1.setBackground(new java.awt.Color(120, 171, 191));
            jButton1.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/icons/outline_arrow_back_ios_black_24dp.png")))); // NOI18N
            jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jButton1MouseClicked(evt);
                }
            });
            jPanel1.add(jButton1);
            jPanel1.add(filler1);

            jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(241, 242, 216));
            jLabel1.setText("Jeu Avalam");
            jPanel1.add(jLabel1);
            jPanel1.add(filler2);

            jButton2.setBackground(new java.awt.Color(120, 171, 191));
            jButton2.setIcon(new javax.swing.ImageIcon(String.valueOf(new File("ressources/icons/outline_help_outline_black_24dp.png")))); // NOI18N
            jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jButton2MouseClicked(evt);
                }
            });
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
                }
            });
            jPanel1.add(jButton2);

            add(jPanel1);

            jPanel2.setBackground(new java.awt.Color(120, 171, 191));
            jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

            jPanel3.setBackground(new java.awt.Color(120, 171, 191));
            jPanel3.setLayout(new java.awt.CardLayout());

            jPanel8.setBackground(new java.awt.Color(120, 171, 191));

            javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
            jPanel8.setLayout(jPanel8Layout);
            jPanel8Layout.setHorizontalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 531, Short.MAX_VALUE)
            );
            jPanel8Layout.setVerticalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 412, Short.MAX_VALUE)
            );

            jPanel3.add(a, "card2");

            jPanel2.add(jPanel3);

            jPanel4.setBackground(new java.awt.Color(120, 171, 191));
            jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));

            jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

            jLabel2.setBackground(new java.awt.Color(120, 171, 191));
            jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
            jLabel2.setText("Tour:");
            jPanel5.add(jLabel2);

            jLabel3.setBackground(new java.awt.Color(120, 171, 191));
            jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
            jLabel3.setText("Nom_joueur(Noir)");
            jPanel5.add(jLabel3);

            jPanel4.add(jPanel5);
            jPanel4.add(filler3);

            jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

            jLabel4.setBackground(new java.awt.Color(120, 171, 191));
            jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
            jLabel4.setText("Historique:");
            jPanel6.add(jLabel4);

            jButton3.setBackground(new java.awt.Color(132, 166, 81));
            jButton3.setText("<");
            jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jButton3MouseClicked(evt);
                }
            });
            jPanel6.add(jButton3);

            jButton4.setBackground(new java.awt.Color(132, 166, 81));
            jButton4.setText(">");
            jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jButton4MouseClicked(evt);
                }
            });
            jPanel6.add(jButton4);

            jPanel4.add(jPanel6);
            jPanel4.add(filler4);

            jPanel7.setLayout(new java.awt.GridLayout(4, 0));

            jButton5.setBackground(new java.awt.Color(132, 166, 81));
            jButton5.setForeground(new java.awt.Color(241, 242, 216));
            jButton5.setText("Relancer une partie");
            jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jButton5MouseClicked(evt);
                }
            });
            jPanel7.add(jButton5);

            jButton6.setBackground(new java.awt.Color(132, 166, 81));
            jButton6.setForeground(new java.awt.Color(241, 242, 216));
            jButton6.setText("Abandonner");
            jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jButton6MouseClicked(evt);
                }
            });
            jPanel7.add(jButton6);

            jButton7.setBackground(new java.awt.Color(132, 166, 81));
            jButton7.setForeground(new java.awt.Color(241, 242, 216));
            jButton7.setText("Suggestion");
            jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jButton7MouseClicked(evt);
                }
            });
            jPanel7.add(jButton7);

            jButton8.setBackground(new java.awt.Color(132, 166, 81));
            jButton8.setForeground(new java.awt.Color(241, 242, 216));
            jButton8.setText("Sauvegarder la partie");
            jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jButton8MouseClicked(evt);
                }
            });
            jPanel7.add(jButton8);

            jPanel4.add(jPanel7);
            jPanel4.add(filler5);

            jPanel2.add(jPanel4);

            add(jPanel2);
        }

        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
        }

        private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {
            // TODO add your handling code here: Reagles
        }

        private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {
            // TODO add your handling code here:
        }

        private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {
            // TODO add your handling code here:
        }

        private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {
            // TODO add your handling code here:
        }

        private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {
            // TODO add your handling code here:
        }

        private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {
            // TODO add your handling code here:
        }

        private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {
            // TODO add your handling code here:
        }

        private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
            // TODO add your handling code here: Back
            cc.mainmenu();
        }


        // Variables declaration - do not modify
        private javax.swing.Box.Filler filler1;
        private javax.swing.Box.Filler filler2;
        private javax.swing.Box.Filler filler3;
        private javax.swing.Box.Filler filler4;
        private javax.swing.Box.Filler filler5;
        private javax.swing.JButton jButton1;
        private javax.swing.JButton jButton2;
        private javax.swing.JButton jButton3;
        private javax.swing.JButton jButton4;
        private javax.swing.JButton jButton5;
        private javax.swing.JButton jButton6;
        private javax.swing.JButton jButton7;
        private javax.swing.JButton jButton8;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JPanel jPanel4;
        private javax.swing.JPanel jPanel5;
        private javax.swing.JPanel jPanel6;
        private javax.swing.JPanel jPanel7;
        private javax.swing.JPanel jPanel8;
        // End of variables declaration
    }
