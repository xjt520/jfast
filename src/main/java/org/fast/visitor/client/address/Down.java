package org.fast.visitor.client.address;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.fast.core.kit.StreamKit;
import org.fast.visitor.htmlunit.imp.HtmlunitVisitorImpl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xjt520 on 15/10/17.
 */
public class Down {

    private static final Log logger = Log.getLog(Down.class);

    public static void main(String[] args) {
        fetch();
    }

    public static void fetch() {
        String url = PropKit.use("url.properties").get("address");
        logger.info(url);
        HtmlunitVisitorImpl vistor = new HtmlunitVisitorImpl();
        //vistor.setJsEnabled(false);
        try {
            Map<String,String> map = new HashMap<String, String>();
            Page page = vistor.getUrl(url, null);
            HtmlPage htmlPage = (HtmlPage) page;
            List<HtmlElement> list = (List<HtmlElement>) htmlPage.getByXPath("//p[@class='MsoNormal']");
            for (HtmlElement htmlElement : list){
                String text = htmlElement.asText().trim();
                if (text.length()>0){
                    String code = text.substring(0,6);
                    String name = text.substring(7, text.length()).trim();
                    String level = "";
                    String parent = "";
                    String nickName = "";
                    if (code.endsWith("0000")){
                        level = "1";
                        parent = "000000";
                        nickName = name;
                    }else if (code.endsWith("00")){
                        level = "2";
                        parent = code.substring(0,2)+"0000";
                        if (name.equals("市辖区")){
                            nickName = map.get(parent);
                        }else if (name.equals("县")){
                            nickName = map.get(parent);
                        }else {
                            nickName = name;
                        }
                    }else {
                        level = "3";
                        parent = code.substring(0,4)+"00";
                        nickName = name;
                    }
                    map.put(code, name);
                    logger.info(code + "-" + name + "-" + level + "-" + parent + "-" + nickName);
                    //Record record = new Record();
                    //record.set("code",code);
                    //record.set("name",name);
                    //record.set("level",level);
                    //record.set("parent",parent);
                    //record.set("nickName",nickName);
                    //Db.save("area",record);
                }
                logger.info(String.valueOf(map.size()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
