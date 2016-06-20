package condition;

import java.lang.annotation.*;

/**
 * if modelClass has this interface , the sql will append this value
 * @OrderBy( "id desc")
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Inherited
public @interface OrderBy {
	public String value();
}
