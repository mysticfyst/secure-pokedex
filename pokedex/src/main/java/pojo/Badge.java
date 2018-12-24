package pojo;

import annotations.EncryptedField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Badge {

  private String cityName;

  private String gymName;

  private Trainer gymTrainer;

  @EncryptedField
  private String gymTrainerSpeciality;
}
