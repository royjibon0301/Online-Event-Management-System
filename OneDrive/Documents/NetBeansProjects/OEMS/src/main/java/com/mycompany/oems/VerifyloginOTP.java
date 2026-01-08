 
package com.mycompany.oems;

import javax.swing.JOptionPane;
import java.util.*;
public class VerifyloginOTP extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VerifyloginOTP.class.getName());

    private String userEmail;
    private String userRole;
    private String generatedOTP;
    private long otpTimestamp;
    
    public VerifyloginOTP() {
        initComponents();
    }

public VerifyloginOTP(String email, String role) {
    this.userEmail = email;
    this.userRole = role;
    initComponents();
    if (role == null || role.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Warning: Role is NULL (Check Login Page)");
        }
    sendOTP();
}

private void sendOTP() {
        generatedOTP = String.valueOf(new Random().nextInt(900000) + 100000);
        otpTimestamp = System.currentTimeMillis();

        String subject = "Your Login OTP - Online Event Management System";
        String message = "Your OTP is: " + generatedOTP + "\nValid for 5 minutes.";

        EmailSender.send(userEmail, subject, message);

        JOptionPane.showMessageDialog(this, 
                "OTP sent to: " + userEmail,
                "OTP Sent",
                JOptionPane.INFORMATION_MESSAGE);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtOTP = new javax.swing.JTextField();
        btnVerify = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setText("VerifyLoginOTP");

        jLabel2.setText("Enter OTP");

        btnVerify.setBackground(new java.awt.Color(0, 51, 255));
        btnVerify.setForeground(new java.awt.Color(255, 255, 255));
        btnVerify.setText("Verify OTP");
        btnVerify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVerify, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtOTP, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVerify, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifyActionPerformed


        String userOTP = txtOTP.getText().trim();

    if(userOTP.isEmpty()){
        JOptionPane.showMessageDialog(this, "Enter OTP first!");
        return;
    }

    long now = System.currentTimeMillis();

    // OTP expired check
    if(now - otpTimestamp > 5 * 60 * 1000){
        JOptionPane.showMessageDialog(this, "OTP expired! Please login again.");
        this.dispose();
        return;
    }
    if(!generatedOTP.equals(userOTP)){
        JOptionPane.showMessageDialog(this, "Invalid OTP!");
        return;
    }
    JOptionPane.showMessageDialog(this, "OTP Verified Successfully!");

    switch (userRole) {
        case "Admin":
            new AdminHome().setVisible(true);
            break;
        case "Organizer":
            new OrganizerHome().setVisible(true);
            break;
        case "Participant":
            new ParticipantHome().setVisible(true);
            break;
        default:
            JOptionPane.showMessageDialog(this, "Unknown Role!");
            return;
    }

    this.dispose();
    }//GEN-LAST:event_btnVerifyActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> new VerifyloginOTP().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVerify;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtOTP;
    // End of variables declaration//GEN-END:variables
}
