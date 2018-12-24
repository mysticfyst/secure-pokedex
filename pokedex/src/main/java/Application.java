import pojo.Pokemon;
import pojo.Trainer;

import java.util.Arrays;

public class Application {

  public static void main(String args[]) {
    Pokemon charmander = new Pokemon(1,"Charmander", 2);
    Pokemon snorlax = new Pokemon(2, "Snorlax", 9);
    Trainer red = new Trainer("tr001", "Red", charmander ,Arrays.asList(charmander,
        snorlax), null, null);
    Encrypter.encryptObject(red);
    System.out.println();
  }
}
