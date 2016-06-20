package org.fast.core.kit.encoder.md5;

import org.fast.core.kit.encoder.Encoder;


public class MD5Encoder extends Encoder {

    @Override
    public String encode(String data, String key) {
        return MD5.once(data + key);
    }

    @Override
    public String decode(String data, String key) {
        throw new UnsupportedOperationException("Not supplorted");
    }

    @Override
    public boolean isValid(String code, String data, String key) {
        return MD5.once(data + key).equals(code);
    }

}
