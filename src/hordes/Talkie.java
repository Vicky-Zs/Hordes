package hordes;

class Talkie extends Hordes {
  public static void consult() {
    for (int i = 0; i < 25; i++) {
      for (int j = 0; j < 25; j++) {
        System.out.print("[" + i + ";" + j +"] : ");
        map[i][j].getTalkie();
        System.out.println();
      }
    }
  }

  public static void consult (int i, int j) {
    map[i][j].getTalkie();
  }
}
