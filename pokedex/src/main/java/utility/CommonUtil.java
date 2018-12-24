package utility;

public class CommonUtil {

  public static String encrypt(String plainText) {
    if (plainText == null) {
      return null;
    }
    return "**" + plainText + "**";
  }

  public static String decrypt(String encryptedText) {
    if (encryptedText == null) {
      return null;
    }
    return encryptedText.substring(2, encryptedText.length() - 2);
  }
}
