package pojo;

import utility.CommonUtil;

public class BadgeEncrypter {

	/** 
	 * Fields to be encrypted: [gymTrainerSpeciality]
	 */
	public static Badge encryptObject(Badge objectToBeEncrypted) {

		// Encrypting gymTrainerSpeciality
		objectToBeEncrypted.setGymTrainerSpeciality(CommonUtil.encrypt(objectToBeEncrypted.getGymTrainerSpeciality()));

		return objectToBeEncrypted;
	}
}
