package cn.com.ssii.core.favor;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <p/>
 * Created by kai on 29/09/15.
 */

abstract class Taste {

    protected final SharedPreferences sp;
    protected final String key;
    protected final String[] defaultValue;

    Taste(SharedPreferences sp, String key, String[] defaultValue) {
        this.sp = sp;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    @SuppressLint("CommitPrefEdits")
    void save(SharedPreferences.Editor editor, boolean commit) {
        if (!commit || Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD)
            editor.apply();
        else
            editor.commit();
    }

    void set(Object object) {
        save(editor(object), false);
    }

    abstract Object get();

    void commit(Object object) {
        save(editor(object), true);
    }

    abstract SharedPreferences.Editor editor(Object object);

    static class StringTaste extends Taste {

        StringTaste(SharedPreferences sp, String key, String[] defaultValue) {
            super(sp, key, defaultValue);
        }

        @Override
        public Object get() {
            return sp.getString(key, defaultValue[0]);
        }

        @SuppressLint("CommitPrefEdits")
        @Override
        SharedPreferences.Editor editor(Object object) {
            return sp.edit().putString(key, (String) object);
        }
    }

    static class IntTaste extends Taste {

        IntTaste(SharedPreferences sp, String key, String[] defaultValue) {
            super(sp, key, defaultValue);
        }

        @Override
        Object get() {
            return sp.getInt(key, defaultValue[0] == null ? 0 : Integer.valueOf(defaultValue[0]));
        }

        @SuppressLint("CommitPrefEdits")
        @Override
        SharedPreferences.Editor editor(Object object) {
            return sp.edit().putInt(key, (Integer) object);
        }
    }

    static class BoolTaste extends Taste {

        BoolTaste(SharedPreferences sp, String key, String[] defaultValue) {
            super(sp, key, defaultValue);
        }

        @Override
        Object get() {
            return sp.getBoolean(key, defaultValue[0] == null ? false : Boolean.valueOf(defaultValue[0]));
        }

        @SuppressLint("CommitPrefEdits")
        @Override
        SharedPreferences.Editor editor(Object object) {
            return sp.edit().putBoolean(key, (Boolean) object);
        }
    }

    static class FloatTaste extends Taste {
        FloatTaste(SharedPreferences sp, String key, String[] defaultValue) {
            super(sp, key, defaultValue);
        }

        @Override
        Object get() {
            return sp.getFloat(key, defaultValue[0] == null ? 0 : Float.valueOf(defaultValue[0]));
        }

        @SuppressLint("CommitPrefEdits")
        @Override
        SharedPreferences.Editor editor(Object object) {
            return sp.edit().putFloat(key, (Float) object);
        }
    }

    static class LongTaste extends Taste {
        LongTaste(SharedPreferences sp, String key, String[] defaultValue) {
            super(sp, key, defaultValue);
        }

        @Override
        Object get() {
            return sp.getLong(key, defaultValue[0] == null ? 0 : Long.valueOf(defaultValue[0]));
        }

        @SuppressLint("CommitPrefEdits")
        @Override
        SharedPreferences.Editor editor(Object object) {
            return sp.edit().putLong(key, (Long) object);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    static class StringSetTaste extends Taste {
        private final HashSet<String> dv;

        StringSetTaste(SharedPreferences sp, String key, String[] defaultValue) {
            super(sp, key, defaultValue);
            if (defaultValue != null && defaultValue.length > 0 && defaultValue[0] != null)
                dv = new HashSet<>(Arrays.asList(defaultValue));
            else dv = null;
        }

        @Override
        Object get() {
            return sp.getStringSet(key, dv);
        }

        @SuppressLint("CommitPrefEdits")
        @Override
        SharedPreferences.Editor editor(Object object) {
            return sp.edit().putStringSet(key, (Set<String>) object);
        }
    }

    static class SerializableTaste extends Taste {


        SerializableTaste(SharedPreferences sp, String key, String[] defaultValue) {
            super(sp, key, defaultValue);
        }

        public static byte[] serialize(Object obj) {
            try {
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                ObjectOutputStream o = new ObjectOutputStream(b);
                o.writeObject(obj);
                return b.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static Object deserialize(byte[] bytes) {
            try {
                ByteArrayInputStream b = new ByteArrayInputStream(bytes);
                ObjectInputStream o = new ObjectInputStream(b);
                return o.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        Object get() {
            String gets = sp.getString(key, null);
            if (TextUtils.isEmpty(gets))
                return null;
            byte[] bytes = Base64.decode(gets, Base64.DEFAULT);
            return deserialize(bytes);
        }

        @SuppressLint("CommitPrefEdits")
        @Override
        SharedPreferences.Editor editor(Object object) {
            return put(sp.edit(), object);
        }

        @SuppressLint("CommitPrefEdits")
        SharedPreferences.Editor put(SharedPreferences.Editor editor, Object object) {
            byte[] bytes = serialize(object);
            if (bytes != null)
                return editor.putString(key, Base64.encodeToString(bytes, Base64.DEFAULT));
            return null;
        }

        String getSerialized(Object object) {
            byte[] bytes = serialize(object);
            if (bytes != null)
                return Base64.encodeToString(bytes, Base64.DEFAULT);
            return null;
        }

    }

    static class EmptyTaste extends Taste {

        EmptyTaste(SharedPreferences sp, String key, String[] defaultValue) {
            super(sp, key, defaultValue);
        }

        @Override
        Object get() {
            return null;
        }

        @Override
        SharedPreferences.Editor editor(Object object) {
            return null;
        }

    }

}
