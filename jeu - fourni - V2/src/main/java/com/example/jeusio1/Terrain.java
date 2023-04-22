package com.example.jeusio1;

import java.util.Random;


//=============================================================================
/**
* Classe Terrain
*  - représente le "sol" du plateau de jeu
*  - contient essentiellement un tableau de cases
 */
//=============================================================================
public class Terrain {
    /** Largeur du terrain, en nombre de cases */
    protected int largeur;
    /** Hauteur du terrain, en nombre de cases */
    protected int hauteur;
    /** Tableau de cases (à 2 dimensions) */
    protected CaseTerrain[][] tabCases;

    /**
     *  <b>Constructeur</b> qui crée un terrain "vide" (cases dont la nature est laissée par défaut).
     * @param larg Largeur du terrain, en nombre de cases
     * @param haut Hauteur du terrain, en nombre de cases
     */
    public Terrain(int larg, int haut){
        int l=larg;
        int h=haut;
        if (l<5) l=5;
        if (h<5) h=5;
        if (l>30) l=30;
        if (h>25) h=25;

        largeur=l;
        hauteur=h;

        tabCases = new CaseTerrain[l][h];

        for (int x=0; x<l; x++){
            for(int y=0; y<h; y++){
                tabCases[x][y] = new CaseTerrain(x*40,y*40);
            }
        }
    }

    /**
     * <strong>Constructeur</strong> qui prend une carte en entrée pour définir la nature des cases.
     * @param carte Tableau d'entiers à deux dimensions. Chaque entier doit correspondre
     *              au code d'un type de CaseTerrain (voir les valeurs dans cette classe).
     * @param larg Largeur du terrain, doit correspondre à la 2ème dimension de la carte
     * @param haut Hauteur du terrain, doit correspondre à la 1ère dimension de la carte
     */
    public Terrain(int[][] carte,int larg, int haut){
        int l=larg;
        int h=haut;
        if (l<5) l=5;
        if (h<5) h=5;
        if (l>30) l=30;
        if (h>25) h=25;

        largeur=l;
        hauteur=h;

        tabCases = new CaseTerrain[l][h];

        for (int x=0; x<l; x++){
            for(int y=0; y<h; y++){
                // TODO : créer la case du terrain en fonction de la carte.
                //        Attention, la coordonnée x,y dans tabCases correspond à y,x dans la carte
                tabCases[x][y] = new CaseTerrain(x*40,y*40, carte[y][x]);
            }
        }
    }

    /**
     * Crée des murs sur le terrain, avec un placement aléatoire
     * @param nbMurs
     */
    public void genererMurs(int nbMurs){
        Random geneAlea = new Random();

        // TODO : programmer la génération d'un mur
        //        Consulter la documentation de Random.
        for(int i = 0; i < nbMurs; ++i) {

            int colAlea = geneAlea.nextInt(largeur);
            int ligAlea = geneAlea.nextInt(hauteur);
            tabCases[colAlea][ligAlea].setType(CaseTerrain.MUR);
        }
    }

    /**
     * Vérifie si une case est libre (= contient une "dalle", pas un mur ou autre obstacle)
     * @param col Colonne de la case désignée.
     * @param lig Ligne de la case désignée.
     * @return Booléen, vrai si la case et libre, faux sinon.
     */
    public boolean caseEstLibre(int col, int lig){
        return tabCases[col][lig].getType()==CaseTerrain.DALLE;
    }



    //=========================================================================
    // Section des get/set
    //=========================================================================
    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public CaseTerrain[][] getTabCases() {
        return tabCases;
    }

    public void setTabCases(CaseTerrain[][] tabCases) {
        this.tabCases = tabCases;
    }
}
