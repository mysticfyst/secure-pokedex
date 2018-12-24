package pojo;

import annotations.EncryptedField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {

  private ItemType itemType;

  public enum ItemType {
    POTION,
    POKEBALL,
    OTHER
  }
}
