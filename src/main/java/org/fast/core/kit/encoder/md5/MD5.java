package org.fast.core.kit.encoder.md5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.fast.core.exception.BusinessRuntimeException;
import org.fast.core.interceptor.SignInterceptor.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MD5 {

    private static final Logger LOG = LoggerFactory.getLogger(MD5.class);

    public final static String hex(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    public final static String code(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return hex(md.digest(message.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessRuntimeException(ErrorCode.Coder.ENCODE_ERROR, "encode data error");
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessRuntimeException(ErrorCode.Coder.ENCODE_ERROR, "encode data error");
        }
    }

    /**
     * 32bit once encryption of MD5
     */
    public final static String once(String s) {
        return encryption(s, 1);
    }

    /**
     * 32bit twice encryption of MD5
     */
    public final static String twice(String s) {
        return encryption(s, 2);
    }

    /**
     * number times 32bit encryption of MD5
     */
    public final static String encryption(String s, int num) {
        for (int i = 0; i < num; i++) {
            s = MD5.code(s);
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(MD5.once("data+UcIChY69ahRZe4NvCUc1RjSqITrFlYKtimestamp20140414163653version1d9cb7516e5105e9cdb41946d816eec83"));
    }
}
