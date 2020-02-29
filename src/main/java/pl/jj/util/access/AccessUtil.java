package pl.jj.util.access;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class AccessUtil {

    private static final String LONG_PAUSE;
    private static final boolean LOG_ENABLE = false;
    private static final String SETTER_PREFIX = "set";
    private static final String GETTER_PREFIX = "get";
    private static final String GETTER_BOOL_PREFIX = "is";

    private final Logger LOGGER = Logger.getLogger(getClass().getName());
    private final Object accessObject;
    private Map<String, Class<?>[]> fieldGenericMap;
    private Map<String, Class<?>> fieldClassMap;
    private Map<String, Method> getterMap;
    private Map<String, Method> setterMap;

    static {
        StringBuilder sb = new StringBuilder();
        IntStream.rangeClosed(0, 60).forEach(i -> sb.append(" "));
        LONG_PAUSE = sb.toString();
    }

    public AccessUtil(Object accessObject) {
        this.accessObject = accessObject;
        startAccess();
    }

    public Class<?>[] fieldGeneric(String property) {
        if (fieldGenericMap != null && fieldGenericMap.containsKey(property))
            return fieldGenericMap.get(property);
        return null;
    }

    public Class<?> fieldClass(String property) {
        if (fieldClassMap != null && fieldClassMap.containsKey(property))
            return fieldClassMap.get(property);
        return null;
    }

    public Method getter(String property) {
        if (getterMap != null && getterMap.containsKey(property))
            return getterMap.get(property);
        return null;
    }

    public Method setter(String property) {
        if (setterMap != null && setterMap.containsKey(property))
            return setterMap.get(property);
        return null;
    }

    private void startAccess() {
        log("Start access for class: " + accessObject.getClass().getName());

        Class<?> accessObjectClass = accessObject.getClass();

        Field[] fields = accessObjectClass.getDeclaredFields();
        log("Found fields: ");
        for (Field f : fields)
            log("Field: " + f.getName());

        Method[] methods = accessObjectClass.getDeclaredMethods();
        log("Found methods:");
        for (Method m : methods)
            log("Method: " + m.getName());

        for (Field f : fields) {

            Class<?> fieldClass = f.getType();
            final String fieldName = f.getName();
            final String property;
            if (f.isAnnotationPresent(JField.class)) property = f.getAnnotation(JField.class).value();
            else property = fieldName;

            if (fieldClassMap == null)
                fieldClassMap = new HashMap<>();
            fieldClassMap.put(fieldName, fieldClass);

            //Field generic
            Type type = f.getGenericType();
            if (type instanceof ParameterizedType) {
                log("Type for field " + fieldName + " is not null with class " + type.getClass().getName());
                Type[] types = ((ParameterizedType) type).getActualTypeArguments();
                if (types.length > 0) {
                    if (fieldGenericMap == null)
                        fieldGenericMap = new HashMap<>();

                    Class<?>[] fieldGenericTypes = Arrays.stream(types)
                            .filter(t -> t instanceof Class<?>)
                            .map(t -> (Class<?>) t)
                            .toArray(Class<?>[]::new);

                    for (Class<?> aClass : fieldGenericTypes)
                        log("Found type: " + aClass.getName());

                    fieldGenericMap.put(fieldName, fieldGenericTypes);
                }
            }

            final String setterMethodName = SETTER_PREFIX + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            final String getterMethodName = GETTER_PREFIX + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            final String getterBooleanMethodName = GETTER_BOOL_PREFIX + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

            log("Field name: " + fieldName);
            log("Field class: " + fieldClass.getName());
            log("Search setter: " + setterMethodName);
            log("Search getter: " + getterMethodName);
            log("Search boolean getter: " + getterBooleanMethodName);

            //Find getter boolean methods
            boolean foundBooleanGetter = false;
            if (fieldClass.equals(Boolean.class) || fieldClass.equals(boolean.class)) {
                log("Field " + fieldName + " is boolean.");
                for (Method m : methods) {
                    String mName = m.getName();
                    if (mName.equals(getterBooleanMethodName)) {
                        if (getterMap == null)
                            getterMap = new HashMap<>();
                        getterMap.put(property, m);
                        foundBooleanGetter = true;
                        break;
                    }
                }
            }

            //Find setter and getter if need
            boolean foundGetter = foundBooleanGetter;
            boolean foundSetter = false;
            for (Method m : methods) {
                String mName = m.getName();
                if (!foundGetter)
                    if (mName.equals(getterMethodName)) {
                        if (getterMap == null)
                            getterMap = new HashMap<>();
                        getterMap.put(property, m);
                        foundGetter = true;
                    }
                if (!foundSetter)
                    if (mName.equals(setterMethodName)) {
                        if (setterMap == null)
                            setterMap = new HashMap<>();
                        setterMap.put(property, m);
                        foundSetter = true;
                    }
                if (foundGetter && foundSetter)
                    break;
            }
        }

        if (getterMap != null) {
            log("Found getter for fields: " + getterMap.size());
            for (String s : getterMap.keySet())
                log("Getter for field: " + s);
        }

        if (setterMap != null) {
            log("Found setter for fields: " + setterMap.size());
            for (String s : setterMap.keySet())
                log("Setter for field: " + s);
        }

    }

    private void log(String log) {
        if (LOG_ENABLE)
            LOGGER.log(Level.INFO, LONG_PAUSE + " => " + log);
    }

}
