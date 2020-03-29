package ru.job4j.tracker.di;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class Context {
    private Map<String, Object> els = new HashMap<>();

    public void reg(Class cl) {
        Constructor[] constructors = cl.getConstructors();
        if (constructors.length > 1) {
            throw new IllegalStateException("Class has multiple constructors: " + cl.getCanonicalName());
        }
        Constructor con = constructors[0];
        List<Object> args = new ArrayList<>();
        for (Class arg : con.getParameterTypes()) {
            args.add(get(arg));
        }
        try {
            els.put(cl.getCanonicalName(), con.newInstance(args.toArray()));
        } catch (Exception e) {
            throw new IllegalStateException("Can't create an instance of " + cl.getCanonicalName(), e);
        }
    }


    public <T> T get(Class<T> inst) {
        String name = inst.getCanonicalName();
        int pointIndex = name.lastIndexOf(".");
        name = name.substring(pointIndex + 1);

        for (String key : els.keySet()) {
            if (key.contains(name)) {
                return (T) els.get(key);
            }
        }
        throw new IllegalStateException("Object doesn't found in context: " + inst.getCanonicalName());
    }
}
