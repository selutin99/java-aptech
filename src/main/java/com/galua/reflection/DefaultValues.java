package com.galua.reflection;

import com.galua.reflection.annotation.DefaultClass;
import com.galua.reflection.annotation.DefaultMethod;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DefaultValues {

    private final Map<Class<?>, List<Class<?>>> defaultTargetClasses = new HashMap<>();
    private final Map<Class<?>, List<Map<Method, String>>> defaultMethodsFields = new HashMap<>();

    public void setDefaults(Object... objects) {
        // initialize process
        initializeDefaultClasses();
        initializeDefaultMethods();

        // setting process
        List<Object> objectsList = Arrays.asList(objects);
        for (Object object : objectsList) {
            setDefaultValuesForObject(object);
        }
    }

    private void initializeDefaultClasses() {
        Set<Class<?>> defaultClasses = new Reflections().getTypesAnnotatedWith(DefaultClass.class);

        for (Class<?> defaultClass : defaultClasses) {
            for (DefaultClass defaultClassAnnotation : defaultClass.getAnnotationsByType(DefaultClass.class)) {
                defaultTargetClasses.put(defaultClass, Arrays.asList(defaultClassAnnotation.targets()));
            }
        }
    }

    private void initializeDefaultMethods() {
        defaultTargetClasses.forEach((defaultClass, targetClassesList) -> {
            for (Method defaultClassMethod : Arrays.asList(defaultClass.getDeclaredMethods())) {
                DefaultMethod defaultClassMethodAnnotation = defaultClassMethod.getAnnotation(DefaultMethod.class);

                if (defaultClassMethodAnnotation != null) {

                    if (defaultMethodsFields.containsKey(defaultClass)) {
                        defaultMethodsFields.get(defaultClass).add(
                                ImmutableMap.of(defaultClassMethod, defaultClassMethodAnnotation.fieldName())
                        );
                    } else {
                        List<Map<Method, String>> methodListMap = new ArrayList<>();
                        methodListMap.add(
                                ImmutableMap.of(defaultClassMethod, defaultClassMethodAnnotation.fieldName())
                        );
                        defaultMethodsFields.put(defaultClass, methodListMap);
                    }
                }
            }
        });
    }

    private void setDefaultValuesForObject(Object object) {
        defaultTargetClasses.forEach((defaultClass, defaultTargetClassesList) -> {
            for (Class defaultTargetClass : defaultTargetClassesList) {
                if (object.getClass().equals(defaultTargetClass)) {
                    setDefaultValueViaMethod(defaultClass, object);
                }
            }
        });
    }

    private void setDefaultValueViaMethod(Class<?> currentDefaultClass, Object currentObject) {
        defaultMethodsFields.forEach((defaultClass, methodsList) -> {
            if (defaultClass.equals(currentDefaultClass)) {
                List<Map<Method, String>> currentMethodsList = defaultMethodsFields.get(currentDefaultClass);
                for (Map<Method, String> methodFieldMap : currentMethodsList) {
                    for (Map.Entry<Method, String> entry : methodFieldMap.entrySet()) {
                        try {
                            Class<?> objectFieldType = currentObject.getClass().getDeclaredField(entry.getValue()).getType();
                            if (objectFieldType.equals(entry.getKey().getReturnType())) {
                                FieldUtils.writeField(
                                        currentObject,
                                        entry.getValue(),
                                        entry.getKey().invoke(defaultClass.newInstance()),
                                        true
                                );
                            }
                        } catch (Exception e) {
                            System.out.println("Incorrect default method type or value for: " + entry.getKey() + " | " + entry.getValue());
                        }
                    }
                }
            }
        });
    }
}
