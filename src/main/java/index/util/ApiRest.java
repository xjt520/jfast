package index.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xjt520 on 16/2/17.
 */
public class ApiRest {

    private int code;
    private String msg;
    private List data;

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public void setData(Object data) {
        List list = new ArrayList();
        list.add(data);
        this.data = list;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
