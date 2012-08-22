package userinterface;

import java.awt.Frame;

import kandisimulaatiojava.LatticeInitializer;
import kandisimulaatiojava.Phys;
import kandisimulaatiojava.Simulation;

public class NewSimulationDialog extends javax.swing.JDialog {

    private Simulation sim1;
    private Simulation sim2;
    
    private Frame parent;
    
    public NewSimulationDialog(Frame parent, Simulation sim1, Simulation sim2) {
        super(parent, true);
        this.sim1 = sim1;
        this.sim2 = sim2;
        this.parent = parent;
        initComponents();

        fieldN1.setText(String.valueOf(sim1.getNumberOfLatticeCells()));
        fieldMass.setText(String.valueOf(sim1.getMass()/ Phys.AU));
        fieldTemperature.setText(String.valueOf(sim1.getInitTemperature()));
        fieldDensity.setText(String.valueOf(sim1.getDensity()));
        fieldLCSize.setText(String.valueOf(Math.round(10000*sim1.getLatticeCellSize()*1e9)/10000.0));
        fieldEpsilon.setText(String.valueOf(sim1.getEpsilon()/ Phys.KB));
        fieldSigma.setText(String.valueOf(sim1.getSigma()*1e9));
        fieldTimestepfactor.setText(String.valueOf(sim1.getTimeStepFactor()));
        fieldTimestepfactor.setToolTipText("Coefficient for the default time step. Shorter time step will increase accuracy, but decrease performance.");
        fieldSeed.setText(String.valueOf(sim1.getSeed()));
        labelError.setVisible(false);
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        buttonFCC = new javax.swing.JRadioButton();
        buttonBCC = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        fieldN1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        fieldMass = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        fieldTemperature = new javax.swing.JTextField();
        buttonDensity = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        fieldEpsilon = new javax.swing.JTextField();
        fieldSigma = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        fieldDensity = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        fieldLCSize = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        fieldTimestepfactor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        fieldSeed = new javax.swing.JTextField();
        buttonOK = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        labelError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("New Simulation");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Lattice type:");

        buttonGroup1.add(buttonFCC);
        buttonFCC.setSelected(true);
        buttonFCC.setText("FCC");

        buttonGroup1.add(buttonBCC);
        buttonBCC.setText("BCC");

        jLabel3.setText("Number of lattice cells per dimension:");

        fieldN1.setText("jTextField1");
        fieldN1.setMaximumSize(new java.awt.Dimension(59, 20));
        fieldN1.setMinimumSize(new java.awt.Dimension(59, 20));

        jLabel5.setText("Atomic mass (AU):");

        fieldMass.setText("jTextField1");
        fieldMass.setMaximumSize(new java.awt.Dimension(59, 20));
        fieldMass.setMinimumSize(new java.awt.Dimension(59, 20));

        jLabel6.setText("Initial temperature (K):");

        fieldTemperature.setText("jTextField1");
        fieldTemperature.setMaximumSize(new java.awt.Dimension(59, 20));
        fieldTemperature.setMinimumSize(new java.awt.Dimension(59, 20));

        buttonGroup2.add(buttonDensity);
        buttonDensity.setSelected(true);
        buttonDensity.setText("Density (kg/m^3):");
        buttonDensity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDensityActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Lennard-Jones potential parameters:");

        jLabel8.setText("Epsilon (kB):");

        fieldEpsilon.setText("jTextField1");
        fieldEpsilon.setMaximumSize(new java.awt.Dimension(59, 20));
        fieldEpsilon.setMinimumSize(new java.awt.Dimension(59, 20));
        fieldEpsilon.setName("");

        fieldSigma.setText("jTextField1");
        fieldSigma.setMaximumSize(new java.awt.Dimension(59, 20));
        fieldSigma.setMinimumSize(new java.awt.Dimension(59, 20));

        jLabel9.setText("Sigma (nm):");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fieldEpsilon, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(fieldSigma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(fieldEpsilon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(fieldSigma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        fieldDensity.setText("jTextField1");
        fieldDensity.setMaximumSize(new java.awt.Dimension(59, 20));
        fieldDensity.setMinimumSize(new java.awt.Dimension(59, 20));

        buttonGroup2.add(jRadioButton1);
        jRadioButton1.setText("Lattice cell size (nm):");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        fieldLCSize.setText("jTextField1");
        fieldLCSize.setEnabled(false);
        fieldLCSize.setMaximumSize(new java.awt.Dimension(59, 20));
        fieldLCSize.setMinimumSize(new java.awt.Dimension(59, 20));

        jLabel10.setText("Time step factor:");

        fieldTimestepfactor.setText("jTextField1");
        fieldTimestepfactor.setMaximumSize(new java.awt.Dimension(59, 20));
        fieldTimestepfactor.setMinimumSize(new java.awt.Dimension(59, 20));

        jLabel11.setText("Seed (negative for random initSeed):");

        fieldSeed.setText("jTextField1");
        fieldSeed.setMaximumSize(new java.awt.Dimension(59, 20));
        fieldSeed.setMinimumSize(new java.awt.Dimension(59, 20));

        buttonOK.setText("OK");
        buttonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOKActionPerformed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        labelError.setForeground(new java.awt.Color(255, 0, 51));
        labelError.setText("jLabel12");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton1)
                            .addComponent(buttonDensity)
                            .addComponent(jLabel1))
                        .addGap(132, 132, 132)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldDensity, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(fieldLCSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fieldN1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(fieldMass, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fieldTemperature, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(buttonFCC)
                                .addGap(18, 18, 18)
                                .addComponent(buttonBCC))
                            .addComponent(labelError))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fieldSeed, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(fieldTimestepfactor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(buttonBCC)
                    .addComponent(buttonFCC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fieldN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fieldMass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fieldTemperature, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonDensity)
                    .addComponent(fieldDensity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(fieldLCSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(fieldTimestepfactor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(fieldSeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labelError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonOK)
                    .addComponent(buttonCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonDensityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDensityActionPerformed
        fieldDensity.setEnabled(true);
        fieldLCSize.setEnabled(false);
        
    }//GEN-LAST:event_buttonDensityActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOKActionPerformed
        if(buttonFCC.isSelected()) {
            sim1.setLatticeType(LatticeInitializer.FCC);
            sim2.setLatticeType(LatticeInitializer.FCC);
        }
        else if(buttonBCC.isSelected()) {
            sim1.setLatticeType(LatticeInitializer.BCC);
            sim2.setLatticeType(LatticeInitializer.BCC);
        }
        
        labelError.setVisible(true);

        try {
            if(!sim1.setNumberOfLatticeCells(Integer.parseInt(fieldN1.getText())) || !sim2.setNumberOfLatticeCells(Integer.parseInt(fieldN1.getText()))) throw new Exception();
        } catch (Exception e) {
            labelError.setText("Invalid number of lattice cells.");
            return;
        }

        try {
            if(!sim1.setMass(Phys.AU*Double.parseDouble(fieldMass.getText())) || !sim2.setMass(Phys.AU*Double.parseDouble(fieldMass.getText()))) throw new Exception();
        } catch (Exception e) {
            labelError.setText("Invalid mass.");
            return;
        }
        
        try {
            if(!sim1.setInitTemperature(Double.parseDouble(fieldTemperature.getText())) || !sim2.setInitTemperature(Double.parseDouble(fieldTemperature.getText()))) throw new Exception();
        } catch (Exception e) {
            labelError.setText("Invalid temperature.");
            return;
        }
        
        if(buttonDensity.isSelected()){
            try {
                if(!sim1.setDensity(Double.parseDouble(fieldDensity.getText())) || !sim2.setDensity(Double.parseDouble(fieldDensity.getText()))) throw new Exception();
            } catch (Exception e) {
                labelError.setText("Invalid density.");
                return;
            }
        }
        else{
            try {
                if(!sim1.setLatticeCellSize(1e-9*Double.parseDouble(fieldLCSize.getText())) || !sim2.setLatticeCellSize(1e-9*Double.parseDouble(fieldLCSize.getText()))) throw new Exception();
            } catch (Exception e) {
                labelError.setText("Invalid lattice cell size.");
                return;
            }
        }

        sim1.setEpsilon(Phys.KB*Double.parseDouble(fieldEpsilon.getText()));
        sim2.setEpsilon(Phys.KB*Double.parseDouble(fieldEpsilon.getText()));

        
        try {
            if(!sim1.setSigma(1e-9*Double.parseDouble(fieldSigma.getText())) || !sim2.setSigma(1e-9*Double.parseDouble(fieldSigma.getText()))) throw new Exception();
        } catch (Exception e) {
            labelError.setText("Invalid sigma.");
            return;
        }
        
        try {
            if(!sim1.setTimeStepFactor(Double.parseDouble(fieldTimestepfactor.getText())) || !sim2.setTimeStepFactor(Double.parseDouble(fieldTimestepfactor.getText()))) throw new Exception();
        } catch (Exception e) {
            labelError.setText("Invalid time step factor.");
            return;
        }
        
        try {
            sim1.setSeed(Integer.parseInt(fieldSeed.getText()));
            sim2.setSeed(Integer.parseInt(fieldSeed.getText()));
        } catch (Exception e) {
            labelError.setText("Invalid seed.");
            return;
        }
        
        if(!sim1.isNumberOfNeighbourCellsValid() || !sim2.isNumberOfNeighbourCellsValid()){
            labelError.setText("Too few cells for this sigma.");
            return;
        }
        
        labelError.setVisible(false);
        
        ((MainGui) parent).startLyaExpSolver();
        
        this.setVisible(false);
        parent.repaint();
    }//GEN-LAST:event_buttonOKActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        fieldDensity.setEnabled(false);
        fieldLCSize.setEnabled(true);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    
    
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewSimulationDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewSimulationDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewSimulationDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewSimulationDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                NewSimulationDialog dialog = new NewSimulationDialog(new javax.swing.JFrame(), null, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton buttonBCC;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JRadioButton buttonDensity;
    private javax.swing.JRadioButton buttonFCC;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton buttonOK;
    private javax.swing.JTextField fieldDensity;
    private javax.swing.JTextField fieldEpsilon;
    private javax.swing.JTextField fieldLCSize;
    private javax.swing.JTextField fieldMass;
    private javax.swing.JTextField fieldN1;
    private javax.swing.JTextField fieldSeed;
    private javax.swing.JTextField fieldSigma;
    private javax.swing.JTextField fieldTemperature;
    private javax.swing.JTextField fieldTimestepfactor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JLabel labelError;
    // End of variables declaration//GEN-END:variables
}
