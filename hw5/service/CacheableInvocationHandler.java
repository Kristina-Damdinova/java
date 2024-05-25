package edu.phystech.hw5.service;

import edu.phystech.hw5.annotation.Cacheable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kzlv4natoly
 */

public class CacheableInvocationHandler implements InvocationHandler {
    private final Object target;
    private final Map<String, Object> cache = new HashMap<>();
    public CacheableInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] arguments) throws Throwable
    {
        if (method.isAnnotationPresent(Cacheable.class)) {
            String key = method.getName() + Arrays.toString(arguments);
            if (cache.containsKey(key)) {
                return cache.get(key);
            } else {
                Object result = method.invoke(target, arguments);
                cache.put(key, result);
                return result;
            }
        }
       return method.invoke(target, arguments);
    }

}
