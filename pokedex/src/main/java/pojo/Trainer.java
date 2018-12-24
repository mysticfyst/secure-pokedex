package pojo;

import annotations.EncryptedField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {

  @EncryptedField
  private String trainerID;

  @EncryptedField
  private String trainerName;

  private Pokemon primaryPokemon;

  private List<Pokemon> pokemons;

  private Set<Badge> badgesWon;

  private Map<Item, Integer> inventory;

}
