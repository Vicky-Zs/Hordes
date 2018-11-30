package hordes;

import java.util.ArrayList;

public class Map {
	private int Coor_x; //Coordonée x de la case
	private int Coor_y; //Coordonée y de la case
	private int z; //Nombre de zombies sur la case
	private ArrayList<String> item; // Liste des objets étant sur cette case
	private ArrayList<String> hide_item; // Liste des objets cachés sur cette case

	//Contructeur
	public Map(int Coor_x, int Coor_y) {
		this.Coor_x = Coor_x;
		this.Coor_y = Coor_y;
		this.z = 0;
		this.item = new ArrayList<>();
		this.hide_item = new ArrayList<>();
	}

	//Affiche la liste des items non caché sur la case en question
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

	// Modifie la liste des objets visibles de la case
	public void addItem(String item) {
		this.item.add(item);
	}

	// Enlève un élément de la liste et retourne cette élément
	public String removeItem() {
		String i;
		int rand = (int) Math.random()*item.size();
		i = item.get(rand);
		return i;
	}

	// Modifie la liste des objets cachés sur la case
	public void addHide_item(String hide_item) {
		this.hide_item.add(hide_item);
	}

	// Enlève un élément de la liste et retourne cette élément
	public String removeHide_item() {
		String i;
		int rand = (int) Math.random()*hide_item.size();
		i = item.get(rand);
		return i;
	}
}
