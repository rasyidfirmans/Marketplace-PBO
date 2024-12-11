/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.pbo.marketplace.ui.sections;

import java.util.ArrayList;
import java.util.List;

import com.pbo.marketplace.domain.entities.User;
import com.pbo.marketplace.retrieve.DatabaseRetrieve;
import com.pbo.marketplace.ui.components.AccountCard;

/**
 *
 * @author DELL
 */
public class AccountSection extends javax.swing.JPanel {

    /**
     * Creates new form AccountCard
     */
    public AccountSection() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        List<User> userList = new DatabaseRetrieve().JSONParser(User.class,
                "src/main/java/com/pbo/marketplace/database/users.json");

        if (userList == null) {
            userList = new ArrayList<>();
        }

        setPreferredSize(new java.awt.Dimension(1440, 700));
        setLayout(new java.awt.GridBagLayout());

        for (User user : userList) {
            if (user.getEmail().equals("rasyidnf.id@gmail.com")) {
                add(new AccountCard(user.getName(), user.getEmail(), String.valueOf(user.getBalance())),
                        new java.awt.GridBagConstraints());

            }
        }

    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
