package pojo;

import utility.CommonUtil;

public class TrainerEncrypter {

	/** 
	 * Fields to be encrypted: [trainerID, trainerName]
	 */
	public static Trainer encryptObject(Trainer objectToBeEncrypted) {

		// Encrypting trainerID
		objectToBeEncrypted.setTrainerID(CommonUtil.encrypt(objectToBeEncrypted.getTrainerID()));

		// Encrypting trainerName
		objectToBeEncrypted.setTrainerName(CommonUtil.encrypt(objectToBeEncrypted.getTrainerName()));

		return objectToBeEncrypted;
	}
}
