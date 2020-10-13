package wang.momo.util;

import org.mindrot.jbcrypt.BCrypt;
import wang.momo.common.MoException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码加密  解密
 * @author rhettmm
 * @version 1.0
 * @date 2020/10/10 0:02
 */
public class EncryUtil {

    /**
     * 加密
     * @param password
     * @return
     */
    public static String bcryptEncode(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    /**
     * 密码密文、明文对比是都有一样
     * @param plaintext
     * @param hashed
     * @return
     */
    public static boolean bcyptDecode(String plaintext,String hashed){
        return BCrypt.checkpw(plaintext, hashed);
    }

    /**
     * MD5加密
     * @param plainText
     * @return
     * @throws Exception
     */
    public static String encryptByMD5(String plainText){
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new MoException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }




}
