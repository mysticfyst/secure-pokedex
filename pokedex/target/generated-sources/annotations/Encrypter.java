import utility.CommonUtil;

public class Encrypter {

	/** 
	 * Fields to be encrypted: [trainerID, trainerName]
	 */
	public static pojo.Trainer encryptObject(pojo.Trainer objectToBeEncrypted) {

		// Encrypting trainerID
		objectToBeEncrypted.setTrainerID(CommonUtil.encrypt(objectToBeEncrypted.getTrainerID()));

		// Encrypting trainerName
		objectToBeEncrypted.setTrainerName(CommonUtil.encrypt(objectToBeEncrypted.getTrainerName()));

		return objectToBeEncrypted;
	}
	/** 
	 * Fields to be encrypted: [gymTrainerSpeciality]
	 */
	public static pojo.Badge encryptObject(pojo.Badge objectToBeEncrypted) {

		// Encrypting gymTrainerSpeciality
		objectToBeEncrypted.setGymTrainerSpeciality(CommonUtil.encrypt(objectToBeEncrypted.getGymTrainerSpeciality()));

		return objectToBeEncrypted;
	}
	/** 
	 * Fields to be encrypted: [name]
	 */
	public static pojo.Pokemon encryptObject(pojo.Pokemon objectToBeEncrypted) {

		// Encrypting name
		objectToBeEncrypted.setName(CommonUtil.encrypt(objectToBeEncrypted.getName()));

		return objectToBeEncrypted;
	}
}
