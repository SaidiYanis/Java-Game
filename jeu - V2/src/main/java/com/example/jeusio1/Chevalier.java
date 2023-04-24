package com.example.jeusio1;

//=============================================================================
/**
 * Classe Chevalier, adversaire du joueur.
 */
//=============================================================================
public class Chevalier extends ElementJeu{

    /** Indique combien de déplacement le chevalier peut faire
     * avant de marquer une pause.
     */
    protected int autonomie;

    /**
     * Constructeur par défaut. L'autonomie par défaut est fixée à 5.
     */
    public Chevalier(){
        super("knight.png");
        autonomie = 5;
    }


}
