package pojo;

import utility.CommonUtil;

public class PokemonEncrypter {

	/** 
	 * Fields to be encrypted: [name]
	 */
	public static Pokemon encryptObject(Pokemon objectToBeEncrypted) {

		// Encrypting name
		objectToBeEncrypted.setName(CommonUtil.encrypt(objectToBeEncrypted.getName()));

		return objectToBeEncrypted;
	}
}
