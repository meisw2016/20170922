package cn.springcloud.boot.ssh.autoinstall.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaUtil {
    /**
     * 加密方法，默认为 RSA
     */
    private static final String ALGORITHM = "RSA";
    /**
     * 密钥的长度
     */
    private static int keySize = 1024;
    /**
     * 密钥对
     */
    public KeyPair keyPair;

    public RsaUtil() {
        this.keyPair = generateKeyPair();
    }

    public RsaUtil(int keySize) {
        this.keySize = keySize;
        this.keyPair = generateKeyPair();
    }

    /**
     * 产生 RSA 公钥私钥对
     *
     * @throws NoSuchAlgorithmException
     */
    private static KeyPair generateKeyPair() {
        SecureRandom secureRandom;
        KeyPairGenerator keyPairGenerator;
        KeyPair keyPair = null;
        try {
            /** 产生一个强安全的随机数 */
            secureRandom = new SecureRandom();
            /**  为RSA算法创建一个KeyPairGenerator对象  */
            keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            /**  利用上面的随机数据源初始化这个KeyPairGenerator对象  */
            keyPairGenerator.initialize(keySize, secureRandom);
            /**  生成密匙对  */
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyPair;
    }

    /**
     * 获取公私钥对
     *
     * @return
     */
    public static KeyPair getKeyPair() {
        return generateKeyPair();
    }


    public static String getKeyBase64(Key key) {
        byte[] keyBytes = key.getEncoded();
        return new BASE64Encoder().encode(keyBytes);
    }

    /**
     * 获取公钥的 Base64 编码
     *
     * @param keyPair
     * @return
     */
    public static String getPublicKeyBase64(KeyPair keyPair) {
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        return new BASE64Encoder().encode(publicKeyBytes);
    }

    public String getPublicKeyBase64() {
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        return new BASE64Encoder().encode(publicKeyBytes);
    }

    /**
     * 获取私钥的 Base64 编码
     *
     * @param keyPair
     * @return
     */
    public static String getPrivateKeyBase64(KeyPair keyPair) {
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        return new BASE64Encoder().encode(privateKeyBytes);
    }

    public String getPrivateKeyBase64() {
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        return new BASE64Encoder().encode(privateKeyBytes);
    }

    /**
     * 字符串转 PublicKey 对象
     *
     * @param key-字符串类型的 key
     * @return PublicKey 对象
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws IOException,
            NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 字符串转 PrivateKey 对象
     *
     * @param key-私钥 key
     * @return PrivateKey 对象
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws IOException,
            NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 加密方法
     *
     * @param password 源数据
     * @return 密文
     * @throws Exception
     */
    public static String encrypt(String password, Key publicKey) throws Exception {
        /**  得到Cipher对象来实现对源数据的RSA加密  */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] b = password.getBytes();
        /**  执行加密操作  */
        byte[] b1 = cipher.doFinal(b);
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(b1);
    }

    /**
     * @param cryptograph-密文
     * @param privateKey-私钥
     * @return 明文
     * @throws Exception
     */
    public static String decrypt(String cryptograph, Key privateKey) throws Exception {
        /**  得到Cipher对象对已用公钥加密的数据进行RSA解密  */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b1 = decoder.decodeBuffer(cryptograph);
        /**  执行解密操作  */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    /**
     * @param cryptograph-密文
     * @param privateKey-私钥
     * @return 明文
     * @throws Exception
     */
    public static String decrypt(String cryptograph, String privateKey) throws Exception {
        /**  得到Cipher对象对已用公钥加密的数据进行RSA解密  */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b1 = decoder.decodeBuffer(cryptograph);
        /**  执行解密操作  */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }
}
