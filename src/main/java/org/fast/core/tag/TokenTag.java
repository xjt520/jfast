package org.fast.core.tag;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class TokenTag extends TagSupport {

    public int doEndTag()
            throws JspException {
        try {
            HttpSession session = ((HttpServletRequest) this.pageContext
                    .getRequest()).getSession();
            String token = UUID.randomUUID().toString().replace("-", "");
            Map tokens = null;
            Object tokensObj = session.getAttribute("token");
            if (tokensObj == null) {
                tokens = new HashMap();
                session.setAttribute("token", tokens);
            } else {
                tokens = (Map) tokensObj;
            }
            tokens.put(token, token);
            StringBuffer sb = new StringBuffer();
            sb.append("<input type=\"hidden\" name=\"token");
            sb.append("\" value=\"");
            sb.append(token);
            sb.append("\"/>");

            this.pageContext.getOut().print(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 6;
    }
}