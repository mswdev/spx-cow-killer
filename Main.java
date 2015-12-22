package scripts.SPXCowKiller;

import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import scripts.SPXCowKiller.api.Node;
import scripts.SPXCowKiller.nodes.BuryBones;
import scripts.SPXCowKiller.nodes.KillCow;
import scripts.SPXCowKiller.nodes.PickupItems;
import scripts.SPXCowKiller.nodes.WalkToCows;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Sphiinx on 12/21/2015.
 */
@ScriptManifest(authors = "Sphiinx", category = "Combat", name = "SPX Cow Killer")
public class Main extends Script {

    public static ArrayList<Node> nodes = new ArrayList<>();
    private final int RANDOM_MOUSE_SPEED = General.random(100, 120);
    private boolean GUI_COMPLETE = false;

    @Override
    public void run() {
        // Set the GUI
        GUI GUI = new GUI();
        GUI.setVisible(true);
        while (!GUI_COMPLETE) {
            sleep(300);
        }
        GUI.setVisible(false);
        // Set our Mouse speed
        Mouse.setSpeed(RANDOM_MOUSE_SPEED);
        // Adding our node collection
        Collections.addAll(nodes, new WalkToCows(), new BuryBones(), new KillCow(), new PickupItems());
        // Loop
        loop(20, 40);
        // Start of our loop
    }

    private void loop(int min, int max) {
        while (true) {
            for (final Node node : nodes) {
                if (node.validate()) {
                    node.execute();
                    General.sleep(General.random(min, max));
                }
            }
        }
        // End of our loop
    }

    // GUI
    public class GUI extends javax.swing.JFrame {

        public GUI() {
            initComponents();
        }

        @SuppressWarnings("unchecked")
        private void initComponents() {

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            buttonGroup1 = new javax.swing.ButtonGroup();
            jLabel1 = new javax.swing.JLabel();
            jPanel1 = new javax.swing.JPanel();
            buryBones = new javax.swing.JRadioButton();
            bankHides = new javax.swing.JRadioButton();
            start = new javax.swing.JButton();

            jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 24));
            jLabel1.setText("SPX Cow Killer");

            jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Options", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 0, 14))); // NOI18N

            buttonGroup1.add(buryBones);
            buryBones.setText("Bury Bones");
            buryBones.setFocusPainted(false);

            buttonGroup1.add(bankHides);
            bankHides.setText("Bank Hides");
            bankHides.setFocusPainted(false);

            start.setText("Start");
            start.setFocusPainted(false);
            start.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    startActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(60, 60, 60)
                                                    .addComponent(buryBones)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(bankHides))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(96, 96, 96)
                                                    .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(68, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(22, 22, 22)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(buryBones)
                                            .addComponent(bankHides))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                    .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(85, 85, 85)
                                    .addComponent(jLabel1)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap())
            );

            pack();
        }

        private void startActionPerformed(java.awt.event.ActionEvent evt) {
            GUI_COMPLETE = true;
        }


        private javax.swing.JRadioButton bankHides;
        private javax.swing.JRadioButton buryBones;
        private javax.swing.ButtonGroup buttonGroup1;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JButton start;
    }
    // GUI

}

