package com.example.jeusio1;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//=============================================================================
/**
 * Classe CaseTerrain, représente une case du terrain
 * => carré de 40 pixels
 */
//=============================================================================
public class CaseTerrain {
    // Des constantes pour la nature du terrain
    public static final int DALLE = 0;
    public static final int MUR = 1;
    private static final int TAILLE = 40;


    /** Nature du terrain (dalle, mur...) */
    protected int type;
    /** Image qui est affichée pour cette case */
    protected ImageView image;

    /**
     * Constructeur, avec position donnée en pixels
     * Le type (par défaut) est DALLE.
     * @param x Abscisse de la case, en pixels.
     * @param y Ordonnée de la case, en pixels. Attention, y est orienté vers le bas.
     */
    public CaseTerrain(int x, int y){
        type = DALLE;
        Image img= new Image(getClass().getResource("stone.png").toString());
        image = new ImageView(img);
        image.setLayoutX(x);
        image.setLayoutY(y);
    }


    /**
     * Constructeur, avec position donnée en pixels et type de la case.
     * @param x Abscisse de la case, en pixels.
     * @param y Ordonnée de la case, en pixels. Attention, y orientée vers le bas.
     * @param t Type de la case, doit correspondre aux constantes définies.
     */
    public CaseTerrain(int x, int y, int t){
        type = t;
        Image img;
        switch (t)
        {
            case DALLE :
                img = new Image(getClass().getResource("stone.png").toString());
                break;
            case MUR :
                img= new Image(getClass().getResource("rock.png").toString());
                break;
            default : img = new Image(getClass().getResource("stone.png").toString());
        }

        image = new ImageView(img);
        image.setLayoutX(x);
        image.setLayoutY(y);
    }



    //=========================================================================
    // Section des get/set
    //=========================================================================
    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    /**
     * Produit l'association de l'image correspondant au type.
     * @param type
     */
    public void setType(int type) {
        this.type = type;
        Image img;
        switch (type)
        {
            case DALLE :
                img = new Image(getClass().getResource("stone.png").toString());
                break;
            case MUR :
                img= new Image(getClass().getResource("rock.png").toString());
                break;
            default : img = new Image(getClass().getResource("stone.png").toString());
        }
        image.setImage(img);
    }
}
