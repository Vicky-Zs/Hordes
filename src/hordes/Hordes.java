package hordes;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Hordes {
	protected static Player[] p = new Player [20];
	// Création du tableau de joueur
	protected static int nb_p = 0;
	// Numero du joueur (pour la création de joueur)
	protected static Map[][] map = new Map [25][25];
	// Création du tableau des cases
	protected static ArrayList<String> alive = new ArrayList<>();
	// Liste des joueurs qui sont vivants
	protected static ArrayList<String> temp_mort = new ArrayList<>();
	// Liste des morts qui ont lieu durant la journée en cours
	protected static ArrayList<String> mort = new ArrayList<>();
	// Liste des morts qui ont eu lieu les 24 dernières heures
	protected static ArrayList<String> old_mort = new ArrayList<>();
	// Liste des morts qui ont eu lieu les 24 dernières heures
	protected static Scanner scan = new Scanner (System.in);
	// Permet d'avoir des entrées
	protected static Random r = new Random();
	// Permet d'avoir des nombres aléatoires
	protected static City city = new City();
	// Variable ville (unique)
	protected static int nb_day = 1;
	// Nombre de jour depuis le début (commence à 1)
	protected static int nb_z = 0;
	// Nombre de zombies qui attaquent la nuit
	protected static ArrayList<Integer> fiftyfifty = new ArrayList<>();
	// Permet de choisir la moité lors de l'attaque en ville
	protected static int nb_turn = 1;
	// Le numéro du tour, le tour 1 correspond de 0h à 2h, le tour de 2 de 2h à 4h, ...,
	// le tour 12 correspon de 22h à 00h. A minuit, c'est l'attaque des zombies.

	// Calcul zombies sur une case
		public static void zMap () {
			int k = 0;
			int rand = 0;
			for (int i = 0; i < 25; i++) { // Parcours case par case
				for (int j = 0; j < 25; j++) {
					rand = r.nextInt(10); // Calcul d'un nombre random entre 0 et 9
						// Si rand = 0, 1 ou 2 OU si case de la ville alors il n'y a pas de zombies.
						if ((rand < 3) || (i == 12 && j == 12)) {
							map[i][j].setZ(0); //0 zombie sur cette case
						}
						else {
							rand = r.nextInt(7)+1; //Calcul d'un nombre entre 1 et 7
							map[i][j].setZ(rand); //Il y aura entre 1 et 7 zombie(s) sur cette case
						}
						k ++; //Vérification
						// System.out.println("Sur la case [" + map[i][j].getCoor_x() + ";" + map[i][j].getCoor_y() + "], il y a " + map[i][j].getZ() + " zombies"); // Pour voir le nombre de zombies sur une case
				}
			}
			System.out.println("Les zombies ont bougé");
		}

		// Algo de changement de tour (toutes les 2 heures)
		public static void changingTurn (){
			for (int i = 0; i < nb_p; i++) { //On regarde chaque joueur
				if (alive.contains(p[i].getPseudo())) { // On vérifie que le joueur est en vie
					p[i].gain4ap();
					if (p[i].getAddict() != -1) { // On vérifie sa dépendance aux boissons énergisante
						p[i].addAddict();
					}
					if (p[i].getAddict() > 2) {
						p[i].lostPv(5);
					}
					if (p[i].getPV() < 1) {
						temp_mort.add(p[i].getPseudo());
						alive.remove(p[i].getPseudo());
					}
				}
			}
			nb_turn ++;
			if (nb_turn == 13) {
				nb_turn = 1;
				changingDay();
			}
		}

		public static void changingDay(){ //Algo de changement de jour (uniquement à 00h)
      int temp;
			fiftyfifty.clear(); // On réinitialise notre liste pour l'aléatoire
			while (mort.isEmpty() == false) {
				old_mort.add(mort.get(0)); // On archive les noms des morts
				mort.remove(0); // Et on les retire de la liste des morts les dernières 24h
			}
			while (temp_mort.isEmpty() == false) {
				mort.add(temp_mort.get(0)); // On met les morts de la journée dans la liste des morts les 24 dernières heures
				temp_mort.remove(0); // Et on les retire de la liste des morts de la journée
			}
			for (int i = 0; i < nb_p; i++){ //On vérifie si chaque joueur est en ville
				if (p[i].getInCity()) {
					p[i].resetDay(); // Enlève le statut "a bu" et "a mangé"
				}
				else {
					mort.add(p[i].getPseudo()); // Si ce n'est pas le cas, on l'ajoute à la liste des morts
					alive.remove(p[i].getPseudo()); // On le retire de la liste des vivants
				}
			}
			// Attaque des zombies sur la ville
			nb_z = r.nextInt(11) + (10 * nb_day); // On choisit un nombre aléatoire entre 0 et 10 que l'on ajoute à 10* le nombre du jour
			nb_day ++; // On ajout un au nombre de jour
			if (city.getDefense() <= nb_z) {
				System.out.println("Les zombies ont réussi à passer");
				for (int i = 0; i < nb_p; i++) {
					if (p[i].getPV() != 0){
						fiftyfifty.add(i); // On ajoute le numéro de chaque joueur qui est encore en vie
					}
				}
				for (int i = 0; i < (fiftyfifty.size()/2 + fiftyfifty.size() % 2); i++) { // Nombre de joueur divisé par 2 + le reste de la division euclidienne
					temp = fiftyfifty.get(r.nextInt(fiftyfifty.size())); // On prend un nombre aléatoire dans la liste fiftyfifty
					System.out.print(p[temp].getPseudo() + " "); // On annonce que le joueur est mort
					mort.add(p[temp].getPseudo()); // On l'ajoute à la liste
					alive.remove(p[temp].getPseudo()); // On le retire de la liste des vivants
				}
				System.out.println("sont morts durant l'attaque de cette nuit");
			}
		}



		/* ----------------------------------------------------------------------- */
		/* ------------------------------  JOURNAL  ------------------------------ */
		/* ----------------------------------------------------------------------- */
		// Affichage du journal reprenant les morts de la veille
		// Hameau obscur est le nom de la ville
		// J'ai pris le nom d'une de mes villes quand j'y jouais ;)
		public static void consultNewspaper() {
			System.out.println ("Hameau Obscur - Jour " + nb_day);
			if (nb_day == 1) {
				System.out.println("Bienvenue au Hameau Obscur !!\nNous avons espéré que les zombies nous oublie, mais ce n'est pas le cas.\nNous allons donc devoir nous organiser et tenir, le Hameau Obscur ne doit pas tomber !");
			}
			else if (mort.isEmpty()) {
				System.out.println("Il n'y a pas eu de mort, malgré l'attaque des " + nb_z + " zombies sur le Hameau Obscur");
			}
			else {
				System.out.print("Voici les morts de la veille : ");
				for (int i = 0; i < mort.size(); i++) {
					System.out.println(mort.get(i) + " ");
				}
			}
			System.out.println("\n \n");
		}

		/* ----------------------------------------------------------------------- */
		/* --------------------------------  JEU  -------------------------------- */
		/* ----------------------------------------------------------------------- */
		public static void game() {
			String ok;
			int in = 0;
			while (alive.size() > 1) {
				System.out.println("\n \n \nJour " + nb_day + " - Tour " + nb_turn);
				for (int i = 0; i < nb_p; i ++) {
					if (alive.contains(p[i].getPseudo())) {
						System.out.println("\n \nC'est au tour de " + p[i].getPseudo() + "\nTapez ok");
						ok = scan.next();
						System.out.println("Vous avez " + p[i].getNb_ap() + " pa");
						Menu.mainMenu(i);
					}
				}
				changingTurn();
			}
			while (mort.isEmpty() == false) {
				// On met tous les morts dans la liste des old_morts (pour le classement)
				old_mort.add(mort.get(0));
				mort.remove(0);
			}
			while (temp_mort.isEmpty() == false) {
				// On met tous les morts dans la liste des old_morts (pour le classement)
				old_mort.add(temp_mort.get(0));
				temp_mort.remove(0);
			}
			if (alive.isEmpty()) {
				System.out.println("Tout le monde est mort, personne n'a gagné");
			}
			else {
				System.out.println(alive.get(0) + " a gagné la partie ! Bravo !");
				// On met tous les morts dans la liste des old_morts (pour le classement)
				old_mort.add(alive.get(0));
			}
			System.out.println("Classement : ");
			for (int i = 0; i < old_mort.size(); i++){
				System.out.println ((i+1) + " : " + old_mort.get(i));
			}
		}
}
