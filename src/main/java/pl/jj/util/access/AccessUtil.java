package pl.jj.util.access;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
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
    private final Class<?> accessObjectClass;
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
        this(accessObject.getClass());
    }

    public AccessUtil(Class<?> accessObjectClass) {
        this.accessObjectClass = accessObjectClass;
        startAccess();
    }

    public Accessible accessible(String property) {
        return Accessible.accessible(
                property,
                fieldGeneric(property),
                fieldClass(property),
                getter(property),
                setter(property)
        );
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

        log("Start access for class: " + accessObjectClass.getName());

        Class<?> aoc = accessObjectClass;
        Set<Method> methods = new HashSet<>();
        while (aoc != null) {
            methods.addAll(Arrays.asList(aoc.getMethods()));
            aoc = aoc.getSuperclass();
        }

        log("Found methods " + methods.size() + " :");
        for (Method m : methods)
            log("Method: " + m.getName());

        for (Method m: methods) {
            String methodName = m.getName();
            if (methodName.startsWith(GETTER_BOOL_PREFIX)) {
                final String fieldName = methodName.substring(2);
                if (methods.stream().anyMatch(method -> method.getName().equals(GETTER_PREFIX + fieldName)))
                    throw new IllegalStateException("Field with name: " + fieldName + " have got get and is getter method");
                String fn = fieldName.substring(0,1).toLowerCase() + fieldName.substring(1);
                log("Found bool getter method for field: " + fn);
                performGetterMethod(fn, m);
            } else if (methodName.startsWith(GETTER_PREFIX)) {
                final String fieldName = methodName.substring(3);
                if (methods.stream().anyMatch(method -> method.getName().equals(GETTER_BOOL_PREFIX + fieldName)))
                    throw new IllegalStateException("Field with name: " + fieldName + " have got get and is getter method");
                String fn = fieldName.substring(0,1).toLowerCase() + fieldName.substring(1);
                log("Found getter method for field: " + fn);
                performGetterMethod(fn, m);
            } else if (methodName.startsWith(SETTER_PREFIX)) {
                String fieldName = methodName.substring(3);
                fieldName = fieldName.substring(0,1).toLowerCase() + fieldName.substring(1);
                if (setterMap == null)
                    setterMap = new HashMap<>();
                log("Found setter method for field: " + fieldName);
                setterMap.put(fieldName, m);
            }
        }
    }

    private void performGetterMethod(String property, Method method) {
        if (getterMap == null)
            getterMap = new HashMap<>();
        getterMap.put(property, method);

        Class<?> getterType = method.getReturnType();
        if (fieldClassMap == null)
            fieldClassMap = new HashMap<>();
        fieldClassMap.put(property, getterType);

        Type type = method.getGenericReturnType();
        if (type instanceof ParameterizedType) {
            log("Type for field " + property + " in method name " + method.getName() + " is not null with class " + type.getClass().getName());
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

                fieldGenericMap.put(property, fieldGenericTypes);
            }
        }
    }

    private void log(String log) {
        if (LOG_ENABLE)
            LOGGER.log(Level.INFO, LONG_PAUSE + " => " + log);
    }

}
