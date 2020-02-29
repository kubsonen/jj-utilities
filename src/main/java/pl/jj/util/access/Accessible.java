package pl.jj.util.access;

import java.lang.reflect.Method;

public class Accessible {

    private Class<?>[] fieldGenericTypes;
    private Class<?> fieldType;
    private Method getter;
    private Method setter;

    private Accessible(Class<?>[] fieldGenericTypes, Class<?> fieldType, Method getter, Method setter) {
        this.fieldGenericTypes = fieldGenericTypes;
        this.fieldType = fieldType;
        this.getter = getter;
        this.setter = setter;
    }

    static Accessible accessible(Class<?>[] fieldGenericTypes, Class<?> fieldType, Method getter, Method setter) {
        return new Accessible(fieldGenericTypes, fieldType, getter, setter);
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
