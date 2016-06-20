package org.fast.visitor.client.taobao;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.jfinal.kit.PropKit;
import org.fast.core.kit.StreamKit;
import org.fast.visitor.htmlunit.imp.HtmlunitVisitorImpl;

import java.io.File;
import java.util.List;

/**
 * Created by xjt520 on 15/10/17.
 */
public class DownImg {

    public static final String url = "http://detail.1688.com/offer/897766097.html?spm=0.0.0.0.cH9FUC";

    public static void main(String[] args) {


        HtmlunitVisitorImpl vistor = new HtmlunitVisitorImpl();
        //vistor.setJsEnabled(false);
        try {
            Page page = vistor.getUrl(url, null);
            HtmlPage htmlPage = (HtmlPage) page;
            System.err.println(htmlPage.asXml());
            List<DomElement> list = htmlPage.getElementsByTagName("img");
            for (DomElement domElement:list){
                String src = domElement.getAttribute("src");
                System.err.println(src);

                if(src.startsWith("http")){
                    String filePath = "/Users/xjt520/temp/imgs/"+System.currentTimeMillis()+".jpg";
                    File file = new File(filePath);
                    StreamKit.down(vistor.getUrl(src,null).getWebResponse().getContentAsStream(),true,true,filePath);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
