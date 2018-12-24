package pojo;

import annotations.EncryptedField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pokemon {

  private int pokeId;

  @EncryptedField
  private String name;

  private int age;

  public Pokemon() {

  }

  public Pokemon(int id, String name, int age) {
    this.pokeId = id;
    this.name = name;
    this.age = age;
  }
}
