package com.example.jeusio1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

//=============================================================================
/**
 * JeuApplication : le coeur de l'application.
 * Contient l'intelligence du jeu, avec des liens vers : <br>
 * <ul>
 *     <li>un terrain</li>
 *     <li>un perso joueur</li>
 *     <li>un chevalier</li>
 *     <li>une liste d'items</li>
 *     <li>un contrôleur associé à la vue, pour les affichages</li>
 * </ul>
 */
//=============================================================================
public class JeuApplication extends Application {
    // Des constantes pour éviter de devoir mémoriser des "codes"
    private static final int HAUT   = 1;
    private static final int BAS    = 2;
    private static final int DROITE = 3;
    private static final int GAUCHE = 4;


    /** Pour accéder aux éléments de la vue, via son contrôleur */
    private JeuController controleur;

    private Terrain terrain;                                        // Le terrain (sol et murs)
    private PersoJoueur perso;                                      // Personnage du joueur (monstre)
    private Chevalier chevalier;                                    // Adversaire (chevalier)
    private ArrayList<Item> listeItems = new ArrayList<Item>();     // Objets présents sur la carte

    /** Permet d'éviter de traiter les appuis sur les touches tant que l'adversaire ne s'est pas déplacé */
    private boolean deplacementEnCours = false;


    /**
     * Démarrage de l'application, chargement de la vue de départ et init
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Chargement de la vue
        FXMLLoader fxmlLoader = new FXMLLoader(JeuApplication.class.getResource("jeu-view.fxml"));

        // Préparation de la scène
        Scene scene = new Scene(fxmlLoader.load(), 1020, 650);
        stage.setMaxWidth(1020);
        stage.setMaxHeight(650);
        stage.setTitle("Jeu SIO1");
        stage.setScene(scene);
        stage.show();

        // Récupération du contrôleur, pour accéder ensuite au "plateau de jeu"
        controleur = (JeuController) fxmlLoader.getController();

        // Création de l'état initial du plateau
        initialiserPlateau();

        // Mise en place de la gestion des appuis sur les touches
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (!deplacementEnCours) {
                    switch (keyEvent.getCode()) {
                        case Z:
                            deplacementEnCours = true;
                            traiterDeplacement(HAUT);
                            break;

                        case S:
                            deplacementEnCours = true;
                            traiterDeplacement(BAS);
                            break;

                        case Q:
                            deplacementEnCours = true;
                            traiterDeplacement(GAUCHE);
                            break;

                        case D:
                            deplacementEnCours = true;
                            traiterDeplacement(DROITE);
                            break;

                    }
                }
            }
        });
    }

    /**
     * Fonction de génération d'un nombre aléatoire.
     * Aleatoire "non linéaire" : les grandes valeurs sont plus rares
     * @param ratio Indique le niveau de rareté des grandes valeurs.
     */
    private float genererAleaRare(float ratio){
        Random geneAlea = new Random();
        float x,y;

        do {
            x = geneAlea.nextFloat();
            y = geneAlea.nextFloat();
        } while (y> 0.05*x);
        return 1-x;
    }

    /**
     * Initialisation du plateau de jeu
     */
    private void initialiserPlateau() {
        // Dessin du terrain
        /*
        // Construction du terrain à partir d'une carte
        int[][] map = {
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        terrain = new Terrain(map,25,12);
        */

        terrain = new Terrain(25,12);
        terrain.genererMurs(10);

        // ajout de chaque case du terrain au plateau de jeu
        for(int c=0; c< terrain.getLargeur(); c++){
            for (int l=0; l< terrain.getHauteur(); l++){
                // TODO : ajouter de chaque case du terrain au plateau de jeu
                //   => voir comment cela fonctionne ci-dessous pour les autres éléments
                controleur.plateauJeu.getChildren().add(terrain.tabCases[c][l].getImage());
            }
        }

        // Generation d'items (arme, armures)
        // TODO...
        // Créer une arme :
        Item arme1 = new Item(10, 5, Item.ARME);
        Item arme2 = new Item(16, 2, Item.ARME);
        Item armure1 = new Item(20,9,Item.ARMURE);
        controleur.plateauJeu.getChildren().add(arme1.getImage());
        controleur.plateauJeu.getChildren().add(arme2.getImage());
        controleur.plateauJeu.getChildren().add(armure1.getImage());

        // Chevalier
        chevalier = new Chevalier();
        chevalier.setCol(10);
        chevalier.setLig(10);
        controleur.plateauJeu.getChildren().add(chevalier.getImage());

        // Perso du joueur :
        perso = new PersoJoueur();
        perso.setCol(2);
        perso.setLig(2);
        controleur.plateauJeu.getChildren().add(perso.getImage());
    }


    /**
     * Déplacements du joueur, provoque un déplacement du (ou des) chevalier(s)
     */
    public void traiterDeplacement(int direction){
        switch (direction){
            case HAUT:
                if (perso.lig>0) {
                    if (terrain.caseEstLibre(perso.col, perso.lig-1) == true) {
                        perso.deplacerDe(0, -1);
                        controleur.message.setText("haut");
                    }
                }
                break;
            case BAS:
                if ( (perso.lig<terrain.hauteur-1) && (terrain.caseEstLibre(perso.col, perso.lig+1) == true) ){
                    perso.deplacerDe(0, 1);
                    controleur.message.setText("bas");
                }
                break;
            case DROITE:
                if ( (perso.col<terrain.largeur-1) && (terrain.caseEstLibre(perso.col+1, perso.lig) == true) ) {
                    perso.deplacerDe(1, 0);
                }
                break;
            case GAUCHE:
                if ( (perso.col>0) && (terrain.caseEstLibre(perso.col-1, perso.lig) == true) ) {
                    perso.deplacerDe(-1, 0);
                }
                break;
        }

        // Vérifier si un objet se trouve sur la case courante
        verifierCollecte();

        // Déplacement du chevalier, après un délai de 0.2s
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        deplacerChevalier();
                    }
                });
            }
        }, 200);

    }

    /**
     * Vérification de collecte d'un item par le joueur (et traitement de
     * cette collecte le cas échéant).
     */
    private void verifierCollecte() {
        // Parcours des items
        // controleur.message.setText("....");
        for (Item item :listeItems){
            if (!item.getEstVisible()) continue; // on ne s'occupe pas des éléments invisibles


        }
    }


    /**
     * Gestion automatique du déplacement du chevalier
     */
    public void deplacerChevalier(){
        boolean depEffectue=false;

        // TODO : concevoir et implémenter un algorithme de déplacement.

        // Le déplacement est terminé, on remet le booléen à false pour
        //  reprendre le traitement des appuis sur les touches
        deplacementEnCours=false;
    }


    /**
     * Méthode exécutée au lancement de l'application.
     * Appelle la méthode launch(), héritée.
     *
     */
    public static void main(String[] args) {
        launch();
    }
}