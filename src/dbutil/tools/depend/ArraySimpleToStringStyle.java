package dbutil.tools.depend;

import org.apache.commons.lang.builder.ToStringStyle;

public class ArraySimpleToStringStyle extends ToStringStyle {

    /**
     *
     */
    private static final long serialVersionUID = 6513085741320833354L;

    public static final ArraySimpleToStringStyle ARRAY_SIMPLE_STYLE = new ArraySimpleToStringStyle();

    ArraySimpleToStringStyle() {
        super();
        this.setUseClassName(false);
        this.setUseIdentityHashCode(false);
        this.setUseFieldNames(false);
        this.setContentStart("");
        this.setContentEnd("");
        this.setArrayStart("");
        this.setArrayEnd("");
    }

    /**
     * <p>
     * Ensure <code>Singleton</ode> after serialization.
     * </p>
     *
     * @return the singleton
     */
    private Object readResolve() {
        return ARRAY_SIMPLE_STYLE;
    }

}

