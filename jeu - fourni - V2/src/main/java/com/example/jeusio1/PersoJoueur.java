package com.example.jeusio1;

//=============================================================================
/**
 * Classe PersoJoueur, monstre contrôlé par le joueur.
 */
//=============================================================================
public class PersoJoueur extends ElementJeu {
    /** Nombre de points de vie */
    protected int pointsVie;
    /** Points de dégats de base (sans arme) */
    protected int baseDegats;
    /** Points d'armure de base (sans armure) */
    protected int baseArmure;
    /** Modificateur actuel pour les points de dégats (ajout au pts de base) */
    protected int degatsModificateur;
    /** Modificateur actuel pour les points d'armure (ajout au pts de base) */
    protected int armureModificateur;

    /**
     * Constructeur par défaut.
     * L'image est celle du monstre rouge, les pts de vie sont fixés à 20,
     * les dégats de base à 2 et les pts d'armure de base à 1.
     */
    public PersoJoueur(){
        super("monster.png");
        pointsVie = 20;

        baseDegats = 2;
        baseArmure = 1;
    }

}
