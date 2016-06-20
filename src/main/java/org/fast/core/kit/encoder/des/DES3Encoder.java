package org.fast.core.kit.encoder.des;

import org.fast.core.kit.encoder.Encoder;


/**
 * 参考: http://www.iteye.com/topic/1127949
 */
public class DES3Encoder extends Encoder {
	
	public final static DES3Encoder me = new DES3Encoder();

    @Override
    public String encode(String data, String key) {
        return DES3.encode(data, key);
    }

    @Override
    public String decode(String data, String key) {
        return DES3.decode(data, key);
    }

    @Override
    public boolean isValid(String code, String data, String key) {
        throw new UnsupportedOperationException("Not supplorted");
    }

    public static void main(String[] args) {
        DES3Encoder d = new DES3Encoder();
        String a = "{\"ibeacon_id\":\"123456\"}";
        String s = d.encode(a);
        System.out.println(s);
        System.out.println(d.decode(s));
    }
}
