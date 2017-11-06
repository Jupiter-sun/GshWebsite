package cn.yfjz.core.util;

public class APICipherUtil {
    private static String strKey = "1qaz2wsx3edc4rfv!@#$%^&*()5tgb6yhn7ujm8ik,9ol.0p;/";

    /** 加密工具     */
    private static CustomPrivateKeyCipher cipher = new CustomPrivateKeyCipher(strKey + strKey.toUpperCase());
    
    /**  
     * 加密字符串  
     *   
     * @param strIn  
     *            需加密的字符串  
     * @return 加密后的字符串  
     * @throws Exception  
     */
    public static String encrypt(String strIn) {
      return cipher.encrypt(strIn);
    }

    /**  
     * 解密字符串  
     *   
     * @param strIn  
     *            需解密的字符串  
     * @return 解密后的字符串  
     * @throws Exception  
     */
    public static String decrypt(String strIn) {
        return cipher.decrypt(strIn);
    }
    
}
