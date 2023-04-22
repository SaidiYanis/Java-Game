package com.example.jeusio1;

//=============================================================================
/**
 * Item : objet "posé" sur le terrain.
 * Les items ne sont pas directement liés au terrain, ils sont liés à la classe
 * JeuApplication.
 */
//=============================================================================
public class Item extends ElementJeu{

    // Constantes pour les types d'items
    public static final int ARME = 0;
    public static final int ARMURE = 1;
    public static final int INCONNU = -1;

    /** Nom de l'item, affiché pour l'utilisateur  */
    protected String nom;
    /** Points de dégats de l'item */
    protected int degats;
    /** Points de protection de l'item */
    protected int protection;
    /** Nature de l'item, doit correspondre à une constante (arme, armure...) */
    protected int type;

    /**
     * Constructeur prenant en entrée une position et une image.
     * Le type est fixé à INCONNU.
     * @param colonne Colonne de l'item, en coordonnées du terrain (cases).
     * @param ligne Ligne de l'item, en coordonnées du terrain (cases).
     * @param nomImage Nom de l'image associée à la case.
     */
    public Item(int colonne, int ligne, String nomImage){
        super(nomImage);
        this.setCol(colonne); // permet de placer aussi l'image
        this.setLig(ligne);
        nom="un truc";
        degats=0;
        protection =0;
        estVisible = true;
        this.chargerImage("question-mark.png");
        type = INCONNU;
    }

    /**
     * Constructeur prenant en entrée une position et un type.
     * L'image est définie automatiquement en fonction du type.
     * @param colonne Colonne de l'item, en coordonnées du terrain (cases).
     * @param ligne Ligne de l'item, en coordonnées du terrain (cases).
     * @param type Entier qui doit correspondre à une constante de type (arme, armure...)
     */
    public Item(int colonne, int ligne, int type){
        super("question-mark.png");
        this.setCol(colonne); // permet de placer aussi l'image
        this.setLig(ligne);
        nom="un truc";
        degats=0;
        protection =0;
        estVisible = true;
        if (type==ARME){
            this.chargerImage("sword.png");
        } else if (type==ARMURE){
            this.chargerImage("shield.png");
        } else {
            type = INCONNU;
            this.chargerImage("question-mark.png");
        }
    }


    //=========================================================================
    // Section des get/set
    //=========================================================================
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }

    public String getNom() {
        return nom;
    }

    public int getDegats() {
        return degats;
    }

    public int getProtection() {
        return protection;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
