package org.fast.core.kit.encoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Encoder {

    private static final Logger LOG = LoggerFactory.getLogger(Encoder.class);

    protected String key = "d9cb7516e5105e9cdb41946d816eec83";

    public String encode(String data) {
        LOG.debug("encodeKey: {}", key);
        return encode(data, key);
    }

    public boolean isValid(String code, String data) {
        return isValid(code, data, key);
    }

    public abstract String encode(String data, String key);

    public String decode(String data) {
        LOG.debug("decodeKey: {}", key);
        return decode(data, key);
    }

    public abstract String decode(String data, String key);

    public abstract boolean isValid(String code, String data, String key);

    public void setKey(String key) {
        this.key = key;
    }

}
