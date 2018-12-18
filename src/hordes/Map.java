package hordes;

import java.util.ArrayList;

public class Map {
	private int Coor_x; //Coordonée x de la case
	private int Coor_y; //Coordonée y de la case
	private int z; //Nombre de zombies sur la case
	private boolean search; // Permet de savoir si la zone a été entièrement fouillé
	private ArrayList<String> item; // Liste des objets étant sur cette case
	private ArrayList<String> hide_item; // Liste des objets cachés sur cette case
	private ArrayList<String> talkie; // Liste des objets mis à jour

	//Contructeur
	public Map(int Coor_x, int Coor_y) {
		this.Coor_x = Coor_x;
		this.Coor_y = Coor_y;
		this.z = 0;
		this.search = false;
		this.item = new ArrayList<>();
		this.hide_item = new ArrayList<>();
		this.talkie = new ArrayList<>();
	}

	//Affiche la liste des items non-cachés sur la case en question
	public void showItem() {
		if (item.isEmpty()) { //On cherche à savoir si la liste est vide
			System.out.println ("Il n'y a rien sur cette case");
		}
		else { //Si la liste n'est pas vide, on affiche la liste
			System.out.println ("Sur la cas, il y a : ");
			for (byte i = 0; i < item.size(); i++) {
				System.out.println(item.get(i) + " ");
			}
		}
	}

	// Permet d'afficher les items non-cachés pour l'ajout d'un item à votre inventaire
	public void bankItem() {
		if (item.isEmpty()) {
			System.out.println("Il n'y a aucun objet dans cette zone");
		}
		else {
			System.out.println("Quel objet voulez vous rammasser ?");
			for (byte i = 0; i < item.size(); i++) {
        System.out.println((i+1) + " : " + item.get(i) + " ");
			}
			System.out.println("0 = Revenir au menu principal");
		}
	}

	//Affiche la liste des items cachés sur la case en question
	public void showHideItem() {
		if (hide_item.isEmpty()) { //On cherche à savoir si la liste est vide
			System.out.println ("Il n'y a rien sur cette case");
		}
		else { //Si la liste n'est pas vide, on affiche la liste
			for (byte i = 0; i < hide_item.size(); i++) {
				System.out.print(hide_item.get(i) + " ");
			}
		}
		System.out.println("");
	}

	// Obtient la coordonée x de la case
	public int getCoor_x() {
		return Coor_x;
	}

	// Modifie la coordonée x de la case
	public void setCoor_x(int Coor_x) {
		this.Coor_x = Coor_x;
	}

	// Obtient la coordonée y de la case
	public int getCoor_y() {
		return Coor_y;
	}

	// Modifie la coordonée y de la case
	public void setCoor_y(int Coor_y) {
		this.Coor_y = Coor_y;
	}

	// Obtient le nombre de zombies sur la case
	public int getZ() {
		return z;
	}

	// Modifie le nombre de zombies sur la case
	public void setZ(int z) {
		this.z = z;
	}

	// Tue un zombie
	public void killZ(){
		this.z --;
	}

	// Modifie la liste des objets visibles de la case
	public void addItem(String item) {
		this.item.add(item);
	}

	// Enlève un élément de la Liste
	public String removeItem(int i) {
		String temp;
		temp = item.get(i);
		item.remove(i);
		return temp;
	}

	// Enlève un élément de la liste et retourne cette élément
	/*public String removeItem() {
		String i;
		int rand = (int) Math.random()*item.size();
		i = item.get(rand);
		item.remove(i);
		return i;
	}*/

	// Permet de connaître la taille de la liste
	public int sizeItem() {
		return item.size();
	}

	// Modifie la liste des objets cachés sur la case
	public void addHide_item(String hide_item) {
		this.hide_item.add(hide_item);
	}

	// Enlève un élément de la liste et retourne cette élément
	public String removeHide_item() {
		String i;
		int rand = (int) (Math.random() * hide_item.size());
		i = hide_item.get(rand);
		hide_item.remove(i);
		return i;
	}

	// Permet de savoir si une case a été intégralement fouillé
	public boolean getSearch() {
		return search;
	}

        // Permet de savoir si une case a été intégralement fouillé
	public void setSearch() {
		this.search = true;
	}

	// Permet de savoir si une case contient encore ou non des objets cachés
	public boolean isEmpty(){
		if (hide_item.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}

	public void talkie(){
		this.talkie = this.item;
	}

	public void getTalkie(){
		if (talkie.isEmpty()) { //On cherche à savoir si la liste est vide
			System.out.println ("Case vide");
		}
		else { //Si la liste n'est pas vide, on affiche la liste
			for (byte i = 0; i < talkie.size(); i++) {
				System.out.print(talkie.get(i) + " ");
			}
		}
		System.out.println("\n");
	}
}
