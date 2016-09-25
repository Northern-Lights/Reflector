package com.android.externalinternalapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    Class c;
    Method m;
    Field f;
    String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getClassByName(View view) {
        EditText v = (EditText) findViewById(R.id.classIdentifier);
        Editable text = v.getText();
        Log.i(TAG, "getClass " + text.toString());
        c = Reflector.getClass(text.toString());
        TextView output = (TextView) findViewById(R.id.classOutput);
        try {
            output.setText("Class name: " + c.getName());
        } catch (NullPointerException e) {
            Log.e(TAG, "Couldn't get class " + text.toString());
        }
    }

    public void getMethod(View view) {
        if (c != null) {
            EditText v = (EditText) findViewById(R.id.declaredMethod);
            Editable text = v.getText();
            Log.i(TAG, "getMethod " + text.toString());
            m = Reflector.getMethod(text.toString(), c);

            TextView output = (TextView) findViewById(R.id.methodOutput);
            try {
                output.setText("Method name: " + m.getName());
            } catch (NullPointerException e) {
                Log.e(TAG, "Couldn't get method " + text.toString());
            }
        }
    }

    public void getField(View view) {
        if (c != null) {
            EditText v = (EditText) findViewById(R.id.declaredField);
            Editable text = v.getText();
            Log.i(TAG, "getField " + text.toString());
            f = Reflector.getField(text.toString(), c);
            f.setAccessible(true);
            TextView output = (TextView) findViewById(R.id.fieldOutput);
            String value = "";
            try {
                value = f.get(null).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                Log.i(TAG, "The field " + f.getName() + " was null");
            } finally {
                output.setText("Field name: " + f.getName() + " Field value: " + value);
                Log.i(TAG, "Value: " + value);
            }
        }
    }

    public void invokeMethod(View view) {
        if (m != null) {
            Log.i(TAG, "method not null");
            Object o = Reflector.execMethod(m);
            Log.i(TAG, "Method executed successfully. Result:");
            try {
                Log.i(TAG, o.toString());
            } catch (NullPointerException e) {
                Log.w(TAG, "Received a null pointer");
            }
        } else {
            Log.e(TAG, "method null");
        }
    }
}
