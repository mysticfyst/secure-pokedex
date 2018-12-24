package com.pokedex.pojo;

import annotations.EncryptedField;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Badge {

  private int id;

  private String cityName;

  private String gymName;

  private Trainer gymTrainer;

  @EncryptedField
  private String gymTrainerSpeciality;
}
