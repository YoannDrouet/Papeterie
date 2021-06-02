package ihm;

import bll.BLLException;
import bll.CatalogueManager;
import bll.bo.Article;
import bll.bo.Couleur;
import bll.bo.Ramette;
import bll.bo.Stylo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {
    private JPanel panneauPrincipal, panneauSaisie, panneauBouton, panneauBoutonType, panneauBoutonGrammage;
    private JLabel reference, designation, marque, stock, prix, typeArticle, grammage, couleur, compteur;
    private JTextField tReference, tDesignation, tMarque, tStock, tPrix;
    private JRadioButton typeRamette, typeStylo;
    private JCheckBox premierGrammage, deuxiemeGrammage;
    private ButtonGroup groupeType, groupeGrammage;
    private JComboBox choixCouleur;
    private JButton precedant, ajouter, sauvegarder, supprimer, suivant;

    private String[] listeCouleur = {null,"Rouge", "Vert", "Bleur", "Noir"};
    private Color couleurApp = new Color( 245, 238, 183, 221);

    private CatalogueManager cm = CatalogueManager.getInstance();
    private List<Article> catalogue = new ArrayList<>();
    private int index = 0;
    private Article expose;

    public GUI() {
        super("Mon application");
        catalogue = cm.getCatalogue();
        if (!catalogue.isEmpty()) {
            expose = catalogue.get(index);
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(getPanneauPrincipal());
        Image icone = new ImageIcon("ressources/Gohan.png").getImage();
        this.setIconImage(icone);
        this.pack();
        this.setVisible(true);
    }

    public JPanel getPanneauPrincipal(){
        if (panneauPrincipal == null){
            panneauPrincipal = new JPanel();
            panneauPrincipal.setBackground(couleurApp);
            panneauPrincipal.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridy = 0;
            gbc.insets = new Insets(10,10,10,10);
            panneauPrincipal.add(getPanneauSaisie(), gbc);
            gbc.gridy = 1;
            panneauPrincipal.add(getPanneauBouton(), gbc);
        }
        return panneauPrincipal;
    }

    public JPanel getPanneauSaisie() {
        if (panneauSaisie == null){
            panneauSaisie = new JPanel();
            panneauSaisie.setBackground(couleurApp);
            panneauSaisie.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.ipady = 5;
            gbc.insets = new Insets(0,0,5,10);
            panneauSaisie.add(getReference(),gbc);
            gbc.gridy = 1;
            panneauSaisie.add(getDesignation(), gbc);
            gbc.gridy = 2;
            panneauSaisie.add(getMarque(), gbc);
            gbc.gridy = 3;
            panneauSaisie.add(getStock(), gbc);
            gbc.gridy = 4;
            panneauSaisie.add(getPrix(),gbc);
            gbc.gridy = 5;
            panneauSaisie.add(getTypeArticle(), gbc);
            gbc.gridy = 6;
            panneauSaisie.add(getGrammage(), gbc);
            gbc.gridy = 7;
            panneauSaisie.add(getCouleur(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 0;
            panneauSaisie.add(getTReference(), gbc);
            gbc.gridy = 1;
            panneauSaisie.add(getTDesignation(), gbc);
            gbc.gridy = 2;
            panneauSaisie.add(getTMarque(), gbc);
            gbc.gridy = 3;
            panneauSaisie.add(getTStock(), gbc);
            gbc.gridy = 4;
            panneauSaisie.add(getTPrix(),gbc);
            gbc.gridy = 5;
            panneauSaisie.add(getPanneauBoutonType(), gbc);
            gbc.gridy = 6;
            panneauSaisie.add(getPanneauBoutonGrammage(), gbc);
            gbc.gridy = 7;
            panneauSaisie.add(getChoixCouleur(), gbc);
            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.gridwidth = 2;
            panneauSaisie.add(getCompteur(), gbc);
        }
        return panneauSaisie;
    }

    public JPanel getPanneauBouton(){
        if (panneauBouton == null){
            panneauBouton = new JPanel();
            panneauBouton.add(getPrecedant());
            panneauBouton.add(getAjouter());
            panneauBouton.add(getSauvegarder());
            panneauBouton.add(getSupprimer());
            panneauBouton.add(getSuivant());
            panneauBouton.setBackground(couleurApp);
        }
        return panneauBouton;
    }

    public JPanel getPanneauBoutonType(){
        if (panneauBoutonType == null){
            panneauBoutonType = new JPanel();
            panneauBoutonType.setBackground(couleurApp);
            getGroupeType();
            panneauBoutonType.setLayout(new BorderLayout());
            panneauBoutonType.add(getTypeRamette(), BorderLayout.NORTH);
            panneauBoutonType.add(getTypeStylo(), BorderLayout.SOUTH);
        }
        return panneauBoutonType;
    }

    public JPanel getPanneauBoutonGrammage(){
        if (panneauBoutonGrammage == null){
            panneauBoutonGrammage = new JPanel();
            panneauBoutonGrammage.setBackground(couleurApp);
            getGroupeGrammage();
            panneauBoutonGrammage.setLayout(new BorderLayout());
            panneauBoutonGrammage.add(getPremierGrammage(), BorderLayout.NORTH);
            panneauBoutonGrammage.add(getDeuxiemeGrammage(), BorderLayout.SOUTH);
        }
        return panneauBoutonGrammage;
    }

    public JLabel getReference() {
        if (reference == null){
            reference = new JLabel("Référence");
        }
        return reference;
    }

    public JLabel getDesignation() {
        if (designation == null){
            designation = new JLabel("Désignation");
        }
        return designation;
    }

    public JLabel getMarque() {
        if (marque == null){
            marque = new JLabel("Marque");
        }
        return marque;
    }

    public JLabel getStock() {
        if (stock == null){
            stock = new JLabel("Stock");
        }
        return stock;
    }

    public JLabel getPrix() {
        if (prix == null){
            prix = new JLabel("Prix");
        }
        return prix;
    }

    public JLabel getTypeArticle(){
        if (typeArticle == null){
            typeArticle = new JLabel("Type");
        }
        return typeArticle;
    }

    public JLabel getGrammage(){
        if (grammage == null){
            grammage = new JLabel("Grammage");
        }
        return grammage;
    }

    public JLabel getCouleur(){
        if (couleur == null){
            couleur = new JLabel("Couleur");
        }
        return couleur;
    }

    public JLabel getCompteur(){
        if (compteur == null){
            String label;
            if (catalogue.isEmpty()){
                label = "0/0";
            }else {
                label = index+1 +"/"+catalogue.size();
            }
            couleur = new JLabel(label);
        }
        return couleur;
    }

    public JTextField getTReference() {
        if (tReference == null){
            tReference = new JTextField(25);
            if (!catalogue.isEmpty()) {
                tReference.setText(expose.getReference());
            }
        }
        return tReference;
    }

    public JTextField getTDesignation() {
        if (tDesignation == null){
            tDesignation = new JTextField(25);
            if (!catalogue.isEmpty()) {
                tDesignation.setText(expose.getDesignation());
            }
        }
        return tDesignation;
    }

    public JTextField getTMarque() {
        if (tMarque == null){
            tMarque = new JTextField(25);
            if (!catalogue.isEmpty()) {
                tMarque.setText(expose.getMarque());
            }
        }
        return tMarque;
    }

    public JTextField getTStock() {
        if (tStock == null){
            tStock = new JTextField(25);
            if (!catalogue.isEmpty()){
                tStock.setText(String.valueOf(expose.getQteStock()));
            }
        }
        return tStock;
    }

    public JTextField getTPrix() {
        if (tPrix == null){
            tPrix = new JTextField(25);
            if (!catalogue.isEmpty()) {
                tPrix.setText(String.valueOf(expose.getPrixUnitaire()));
            }
        }
        return tPrix;
    }

    public JRadioButton getTypeRamette(){
        if (typeRamette == null){
            typeRamette = new JRadioButton("Ramette");
            typeRamette.setBackground(couleurApp);
            if (!catalogue.isEmpty() && expose instanceof Ramette){
                typeRamette.doClick();
                getChoixCouleur().setEnabled(false);
            }
            typeRamette.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    premierGrammage.doClick();
                    premierGrammage.setEnabled(true);
                    deuxiemeGrammage.setEnabled(true);
                    getChoixCouleur().setEnabled(false);
                }
            });
        }
        return typeRamette;
    }

    public JRadioButton getTypeStylo(){
        if (typeStylo == null){
            typeStylo = new JRadioButton("Stylo");
            typeStylo.setBackground(couleurApp);
            if (!catalogue.isEmpty() && expose instanceof Stylo){
                typeStylo.doClick();
                getPremierGrammage().setEnabled(false);
                getDeuxiemeGrammage().setEnabled(false);
            }
            typeStylo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    premierGrammage.setEnabled(false);
                    deuxiemeGrammage.setEnabled(false);
                    choixCouleur.setEnabled(true);
                }
            });
        }
        return typeStylo;
    }

    public JCheckBox getPremierGrammage(){
        if (premierGrammage == null){
            premierGrammage = new JCheckBox("80 grammes");
            premierGrammage.setActionCommand("80");
            premierGrammage.setMargin(new Insets(0,0,0,0));
            premierGrammage.setBackground(couleurApp);
            if (!catalogue.isEmpty() && expose instanceof Ramette && ((Ramette) expose).getGrammage() == 80){
                premierGrammage.doClick();
                getDeuxiemeGrammage().setEnabled(true);
            }else {
                premierGrammage.setEnabled(false);
            }
        }
        return premierGrammage;
    }

    public JCheckBox getDeuxiemeGrammage(){
        if (deuxiemeGrammage == null){
            deuxiemeGrammage = new JCheckBox("100 grammes");
            deuxiemeGrammage.setActionCommand("100");
            deuxiemeGrammage.setMargin(new Insets(0,0,0,0));
            deuxiemeGrammage.setBackground(couleurApp);
            if (!catalogue.isEmpty() && expose instanceof Ramette && ((Ramette) expose).getGrammage() == 100){
                deuxiemeGrammage.doClick();
                getPremierGrammage().setEnabled(true);
            }else {
                deuxiemeGrammage.setEnabled(false);
            }
        }
        return deuxiemeGrammage;
    }

    public ButtonGroup getGroupeType(){
        if (groupeType == null){
            groupeType = new ButtonGroup();
            groupeType.add(getTypeRamette());
            groupeType.add(getTypeStylo());
        }
        return groupeType;
    }

    public ButtonGroup getGroupeGrammage(){
        if (groupeGrammage == null){
            groupeGrammage = new ButtonGroup();
            groupeGrammage.add(getPremierGrammage());
            groupeGrammage.add(getDeuxiemeGrammage());
        }
        return groupeGrammage;
    }

    public JComboBox<Couleur> getChoixCouleur(){
        if (choixCouleur == null){
            choixCouleur = new JComboBox<>(Couleur.values());
            if (!catalogue.isEmpty() && expose instanceof Stylo){
                choixCouleur.setSelectedItem(Couleur.valueOf(((Stylo) expose).getCouleur()));
            }else {
                choixCouleur.setEnabled(false);
            }
        }
        return choixCouleur;
    }

    public JButton getPrecedant() {
        if (precedant == null){
            ImageIcon icon = new ImageIcon("ressources/Back24.gif");
            precedant = new JButton(icon);
            precedant.setToolTipText("Précédant");
            precedant.setPreferredSize(new Dimension(55,40));
            if (catalogue.isEmpty()){
                precedant.setEnabled(false);
            }
            precedant.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (index == 0){
                        index = catalogue.size()-1;
                    }else {
                        index--;
                    }
                    getCompteur().setText(index+1+"/"+catalogue.size());
                    getCompteur().repaint();
                    expose = catalogue.get(index);
                    changerAffichageArticle(expose);
                }
            });
        }
        return precedant;
    }

    public JButton getAjouter() {
        if (ajouter == null){
            Icon icon = new ImageIcon("ressources/New24.gif");
            ajouter = new JButton(icon);
            ajouter.setToolTipText("Ajouter");
            ajouter.setPreferredSize(new Dimension(55,40));
            ajouter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Article a;
                    if (verifChamps()){
                        if (typeRamette.isSelected()){
                            a = new Ramette(tReference.getText(),
                                    tMarque.getText(),
                                    tDesignation.getText(),
                                    Float.parseFloat(tPrix.getText()),
                                    Integer.parseInt(tStock.getText()) ,
                                    Integer.parseInt(groupeGrammage.getSelection().getActionCommand()));
                        }else {
                            a = new Stylo(tReference.getText(),
                                    tMarque.getText(),
                                    tDesignation.getText(),
                                    Float.parseFloat(tPrix.getText()),
                                    Integer.parseInt(tStock.getText()),
                                    choixCouleur.getSelectedItem().toString());
                        }
                        try {
                            cm.addArticle(a);
                            index++;
                            if (expose == null){
                                expose = catalogue.get(index);
                            }
                            catalogue = cm.getCatalogue();
                            getCompteur().setText(index+1+"/"+catalogue.size());
                            getCompteur().repaint();
                            getPrecedant().setEnabled(true);
                            getSauvegarder().setEnabled(true);
                            getSupprimer().setEnabled(true);
                            getSuivant().setEnabled(true);
                        } catch (BLLException bllException) {

                        }
                    }
                }
            });
        }
        return ajouter;
    }

    public JButton getSauvegarder() {
        if (sauvegarder == null){
            ImageIcon icon = new ImageIcon("ressources/Save24.gif");
            sauvegarder = new JButton(icon);
            sauvegarder.setToolTipText("Sauvegarder");
            sauvegarder.setPreferredSize(new Dimension(55,40));
            if (catalogue.isEmpty()){
                sauvegarder.setEnabled(false);
            }
            sauvegarder.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Article a;

                    if (verifChamps()){
                        if (typeRamette.isSelected()){
                            a = new Ramette(expose.getIdArticle(),
                                    tReference.getText(),
                                    tMarque.getText(),
                                    tDesignation.getText(),
                                    Float.parseFloat(tPrix.getText()),
                                    Integer.parseInt(tStock.getText()) ,
                                    Integer.parseInt(groupeGrammage.getSelection().getActionCommand()));
                        }else {
                            a = new Stylo(expose.getIdArticle(),
                                    tReference.getText(),
                                    tMarque.getText(),
                                    tDesignation.getText(),
                                    Float.parseFloat(tPrix.getText()),
                                    Integer.parseInt(tStock.getText()),
                                    choixCouleur.getSelectedItem().toString());
                        }
                        try {
                            cm.updateArticle(a);
                        } catch (BLLException bllException) {

                        }
                    }
                }
            });
        }
        return sauvegarder;
    }

    public JButton getSupprimer() {
        if (supprimer == null){
            ImageIcon icon = new ImageIcon("ressources/Delete24.gif");
            supprimer = new JButton(icon);
            supprimer.setToolTipText("Supprimer");
            supprimer.setPreferredSize(new Dimension(55,40));
            if (catalogue.isEmpty()){
                supprimer.setEnabled(false);
            }
            supprimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        cm.removeArticle(expose.getIdArticle());
                        catalogue = cm.getCatalogue();
                        if (!catalogue.isEmpty()){
                            expose = catalogue.get(index);
                            changerAffichageArticle(expose);
                            getCompteur().setText(index+1+"/"+catalogue.size());
                            getCompteur().repaint();
                        } else {
                            getCompteur().setText("0/0");
                            getCompteur().repaint();
                            getTReference().setText(null);
                            getTMarque().setText(null);
                            getTDesignation().setText(null);
                            getTStock().setText(null);
                            getTPrix().setText(null);
                            getGroupeType().clearSelection();
                            getPremierGrammage().doClick(0);
                            getPremierGrammage().setEnabled(false);
                            getDeuxiemeGrammage().doClick(0);
                            getDeuxiemeGrammage().setEnabled(false);
                            groupeGrammage.clearSelection();
                            getChoixCouleur().setEnabled(false);
                            getPrecedant().setEnabled(false);
                            getSauvegarder().setEnabled(false);
                            getSupprimer().setEnabled(false);
                            getSuivant().setEnabled(false);
                        }
                    } catch (BLLException bllException) {
                        bllException.printStackTrace();
                    }
                }
            });
        }
        return supprimer;
    }

    public JButton getSuivant() {
        if (suivant == null){
            ImageIcon icon = new ImageIcon("ressources/Forward24.gif");
            suivant = new JButton(icon);
            suivant.setToolTipText("Suivant");
            suivant.setPreferredSize(new Dimension(55,40));
            if (catalogue.isEmpty()){
                suivant.setEnabled(false);
            }
            suivant.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (index+1 == catalogue.size()){
                        index = 0;
                    }else{
                        index++;
                    }
                    getCompteur().setText(index+1+"/"+catalogue.size());
                    getCompteur().repaint();
                    expose = catalogue.get(index);
                    changerAffichageArticle(expose);
                }
            });
        }
        return suivant;
    }

    public void changerAffichageArticle(Article a){
        getTReference().setText(expose.getReference());
        getTDesignation().setText(expose.getDesignation());
        getTMarque().setText(expose.getMarque());
        getTStock().setText(String.valueOf(expose.getQteStock()));
        getTPrix().setText(String.valueOf(expose.getPrixUnitaire()));
        if (expose instanceof Ramette){
            getTypeRamette().doClick();
            getPremierGrammage().setEnabled(true);
            getDeuxiemeGrammage().setEnabled(true);
            if (((Ramette) expose).getGrammage() == 80){
                getPremierGrammage().doClick();
            } else {
                getDeuxiemeGrammage().doClick();
            }
            getChoixCouleur().setEnabled(false);
        } else {
            getTypeStylo().doClick();
            getChoixCouleur().setEnabled(true);
            getChoixCouleur().setSelectedItem(Couleur.valueOf(((Stylo) expose).getCouleur()));
            getPremierGrammage().setEnabled(false);
            getDeuxiemeGrammage().setEnabled(false);
        }
    }

    public boolean verifChamps(){
        boolean ok = true;
        if (tReference.getText().isEmpty() || tReference.getText().trim().equals("")){
            getTReference().setBorder(BorderFactory.createLineBorder(Color.RED));
            ok = false;
        }
        if (tMarque.getText().isEmpty() || tMarque.getText().trim().equals("")){
            getTMarque().setBorder(BorderFactory.createLineBorder(Color.RED));
            ok = false;
        }
        if (tDesignation.getText().trim().equals("") || tDesignation.getText().isEmpty()){
            getTDesignation().setBorder(BorderFactory.createLineBorder(Color.RED));
            ok = false;
        }
        if (tStock.getText().isEmpty() || tStock.getText().trim().equals("")){
            getTStock().setBorder(BorderFactory.createLineBorder(Color.RED));
            ok = false;
        }
        if (tPrix.getText().isEmpty() || tPrix.getText().trim().equals("")){
            getTPrix().setBorder(BorderFactory.createLineBorder(Color.RED));
            ok = false;
        }
        return ok;
    }
}
