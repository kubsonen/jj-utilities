package pl.jj.util.access;

import java.lang.reflect.Method;

public final class Accessible {

    private final String property;
    private final Class<?>[] fieldGenericTypes;
    private final Class<?> fieldType;
    private final Method getter;
    private final Method setter;

    private Accessible(String property, Class<?>[] fieldGenericTypes, Class<?> fieldType, Method getter, Method setter) {
        this.property = property;
        this.fieldGenericTypes = fieldGenericTypes;
        this.fieldType = fieldType;
        this.getter = getter;
        this.setter = setter;
    }

    static Accessible accessible(String property, Class<?>[] fieldGenericTypes, Class<?> fieldType, Method getter, Method setter) {
        return new Accessible(property, fieldGenericTypes, fieldType, getter, setter);
    }

    public String getProperty() {
        return property;
    }

    public Class<?>[] getFieldGenericTypes() {
        return fieldGenericTypes;
    }

    public Class<?> getFieldType() {
        return fieldType;
    }

    public Method getGetter() {
        return getter;
    }

    public Method getSetter() {
        return setter;
    }

}
