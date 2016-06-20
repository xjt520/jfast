package org.fast.core.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xjt520 on 16/4/29.
 */
public class DictSelectTag extends BodyTagSupport {

    private String id = "";
    private String name;
    private String dictType;
    private String value;
    private String nullLabel;
    private String disabled = "";
    private String onclick = "";
    private String onchange = "";
    private String style = "";
    private String extAttr = "";

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDictType()
    {
        return this.dictType;
    }

    public void setDictType(String dictType)
    {
        this.dictType = dictType;
    }

    public String getValue()
    {
        return this.value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getNullLabel()
    {
        return this.nullLabel;
    }

    public void setNullLabel(String nullLabel)
    {
        this.nullLabel = nullLabel;
    }

    public String getId()
    {
        return this.id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getStyle()
    {
        return this.style;
    }

    public void setStyle(String style)
    {
        this.style = style;
    }

    public String getExtAttr()
    {
        return this.extAttr;
    }

    public void setExtAttr(String extAttr)
    {
        this.extAttr = extAttr;
    }

    public String getDisabled()
    {
        return this.disabled;
    }

    public void setDisabled(String disabled)
    {
        this.disabled = disabled;
    }

    public String getOnchange()
    {
        return this.onchange;
    }

    public void setOnchange(String onchange)
    {
        this.onchange = onchange;
    }

    public String getOnclick()
    {
        return this.onclick;
    }

    public void setOnclick(String onclick)
    {
        this.onclick = onclick;
    }

    public int doEndTag()
            throws JspException
    {
        try
        {
            StringBuffer sb = new StringBuffer();
            sb.append("<select ");
            if (!"".equals(this.id))
            {
                sb.append("id=\"");
                sb.append(this.id+ "_" +System.currentTimeMillis());
                sb.append("\" ");
            }
            sb.append("name=\"");
            sb.append(this.name);
            sb.append("\" ");
            if ("disabled".equals(this.disabled))
            {
                sb.append("disabled=\"");
                sb.append(this.disabled);
                sb.append("\" ");
            }
            if (!"".equals(this.onclick))
            {
                sb.append("onclick=\"");
                sb.append(this.onclick);
                sb.append("\" ");
            }
            if (!"".equals(this.onchange))
            {
                sb.append("onchange=\"");
                sb.append(this.onchange);
                sb.append("\" ");
            }
            if (!"".equals(this.style))
            {
                sb.append("style=\"");
                sb.append(this.style);
                sb.append("\" ");
            }
            if (!"".equals(this.extAttr))
            {
                sb.append(this.extAttr);
                sb.append(" ");
            }
            sb.append(">\r\n");

            List<Dictionary> list = new ArrayList<Dictionary>();//CacheUtils.getDictByType(this.dictType);
            if (this.nullLabel != null)
            {
                sb.append("<option value=\"\">");
                sb.append(this.nullLabel);
                sb.append("</option>\r\n");
            }
            for (Dictionary dict : list)
            {
                sb.append("<option value=\"");
                sb.append(dict.getId());
                sb.append("\"");
                if (dict.getId().equals(this.value)) {
                    sb.append(" selected=\"selected\"");
                }
                sb.append(">");

                sb.append(dict.getCode());
                sb.append("</option>\r\n");
            }
            sb.append("</select>");
            this.pageContext.getOut().print(sb.toString());
        }
        catch (Exception localException) {}
        return 6;
    }
}
