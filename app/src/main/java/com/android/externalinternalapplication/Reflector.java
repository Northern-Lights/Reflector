package com.android.externalinternalapplication;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by armen on 9/19/16.
 */
public class Reflector {

    static String TAG = "Reflector";

    public static Class<Class> getClass(String name) {
        Log.i(TAG, "Getting class " + name);
        Class c = null;
        try {
            c = Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return c;
        }
    }

    public static Method getMethod(String name, Class c) {
        Log.i(TAG, "Getting method " + name);
        Method m = null;
        try {
            m = c.getDeclaredMethod(name);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            return m;
        }
    }

    public static Field getField(String name, Class c) {
        Log.i(TAG, "Getting field " + name);
        Field f = null;
        try {
            f = c.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            return f;
        }
    }

    public static Object execMethod(Method m) {
        Log.i(TAG, "Executing method " + m.getName());
        Object ret = null;
        try {
            ret = m.invoke(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            return ret;
        }
    }
}
