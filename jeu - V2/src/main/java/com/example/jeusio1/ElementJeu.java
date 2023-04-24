package com.example.jeusio1;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//=============================================================================
/**
 * Classe ElementJeu, représente un élément affichable.
 */
//=============================================================================
public class ElementJeu {
    /** Abscisse de la position. Unité = case (et non pixel). */
    protected int col;
    /** Ordonnée de la position. Unité = case (et non pixel). */
    protected int lig;
    /** Image associé à l'élément */
    protected ImageView image;
    /** Est-ce que l'élément est affiché ? */
    protected boolean estVisible;

    /**
     * Constructeur par défaut, l'image est une image par défaut (points d'interrogation).
     */
    public ElementJeu(){
        col =0;
        lig =0;
        chargerImage("question-mark.png");
    }

    /**
     * Constructeur qui prend l'image de l'élément en entrée.
     * @param nomImg Nom d'une image qui se trouve dans le dossier resources/com.example.jeusio1 .
     */
    public ElementJeu(String nomImg){
        col =0;
        lig =0;
        chargerImage(nomImg);
    }

    /**
     * Chargement de l'image.
     * Méthode à usage interne à la classe.
     * @param nomImage Nom d'une image qui se trouve dans le dossier resources/com.example.jeusio1 .
     */
    protected void chargerImage(String nomImage){
        Image img= new Image(getClass().getResource(nomImage).toString());
        image = new ImageView(img);
        image.setLayoutX(col *40+4); // 40 : taille des cases
        image.setLayoutY(lig *40+4);
    }


    /**
     * Déplacement de l'item, relativement à sa position courante
     * @param depX Nombre de cases de déplacement sur la ligne.
     * @param depY Nombre de cases de déplacement sur la colonne;
     */
    public void deplacerDe(int depX, int depY) {
        col +=depX;
        lig +=depY;

        image.setLayoutX(col *40+4);
        image.setLayoutY(lig *40+4);

        System.out.println("x,y : "+ col +","+ lig);
    }

    //=========================================================================
    // Section des get/set
    //=========================================================================
    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
        image.setLayoutX(col *40+4);
    }

    public int getLig() {
        return lig;
    }

    public void setLig(int lig) {
        this.lig = lig;
        image.setLayoutY(lig *40+4);
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public boolean getEstVisible() {
        return estVisible;
    }

    public void setEstVisible(boolean estVisible) {
        this.estVisible = estVisible;
    }

}
