package condition;

import com.jfinal.kit.StrKit;

/**
 * and or 拼接
 */
public class DynamicBuilder {
	
    private StringBuilder builder = new StringBuilder();

    private void build(String name, Object value, String comparison) {
        if (null == value)
            value = "";
        builder.append(String.format("%s %s %s", name, comparison, value));
    }

    public DynamicBuilder and(String name, Object value) {
        return and(name, value, "=");
    }

    public DynamicBuilder and(String name, Object value, String comparison) {
        if (builder.length() > 0) {
            builder.append(" and ");
        }
        build(name, value, comparison);
        return this;
    }

    public DynamicBuilder or(String name, Object value) {
        return or(name, value, "=");
    }

    public DynamicBuilder or(String name, Object value, String comparison) {
        if (builder.length() > 0) {
            builder.append(" or ");
        }
        build(name, value, comparison);
        return this;
    }

    public DynamicBuilder orderBy(String value) {
        if (StrKit.isBlank(value)){
            return this;
        }
        if (builder.length() > 0) {
            builder.append(" order by ");
        }
        builder.append(String.format("%s", value));
        return this;
    }

    public DynamicBuilder and(DynamicBuilder query) {
        if (builder.length() > 0) {
            builder.append(" and ");
        }
        builder.append(String.format("(%s)", query.toString()));
        return this;
    }

    public DynamicBuilder or(DynamicBuilder query) {
        if (builder.length() > 0) {
            builder.append(" or ");
        }
        builder.append(String.format("(%s)", query.toString()));
        return this;
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
