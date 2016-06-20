package org.fast.core.kit.encoder.des;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.fast.core.exception.BusinessRuntimeException;
import org.fast.core.interceptor.SignInterceptor.ErrorCode;
import org.fast.core.kit.encoder.base64.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字符串 DESede(3DES) 加密
 */
public class DES3 {

    private static final Logger LOG = LoggerFactory.getLogger(DES3.class);

    // 向量
    private final static String iv = "01234567";
    // 加解密统一使用的编码方式
    private final static String encoding = "UTF-8";

    /**
     * 3DES加密
     * 
     * @param plainText 普通文本
     * @param secretKey 密钥
     * @return
     * @throws Exception
     */
    public static String encode(String plainText, String secretKey) {
        Key deskey = null;
        try {
            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);

            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
            byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
            return Base64.encode(encryptData);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessRuntimeException(ErrorCode.Coder.ENCODE_ERROR, "code data error");
        }
    }

    /**
     * 3DES解密
     * 
     * @param encryptText 加密文本
     * @param secretKey 密钥
     * @return
     * @throws Exception
     */
    public static String decode(String encryptText, String secretKey) {
        Key deskey = null;
        try {
            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

            byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));

            return new String(decryptData, encoding);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessRuntimeException(ErrorCode.Coder.DECODE_ERROR, "decode data error");
        }
    }
}
