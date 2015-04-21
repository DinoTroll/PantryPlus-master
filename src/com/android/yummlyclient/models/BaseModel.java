package com.android.yummlyclient.models;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.widget.Toast;

public class BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	transient JSONObject jsonObject;

    public BaseModel() {
    }
    
    public String getJSONString() {
        return jsonObject.toString(); 
    }

    protected String getString(String name) {
        try {
        	Log.d("debug", name + " : " + jsonObject.getString(name));
            return jsonObject.getString(name);
        } catch (JSONException e) {
        	Log.d("debug", "Got exception : " + name);
            e.printStackTrace();
            return null;
        }
    }
    
    protected ArrayList<String> getArrayList(String name) {
    	ArrayList<String> arrayList;
        try {
        	JSONArray jsonArray = jsonObject.getJSONArray(name);
        	arrayList = new ArrayList<String>(jsonArray.length());
        	for (int i = 0; i < jsonArray.length(); i++) {
    			arrayList.add(jsonArray.get(i).toString());
    			Log.d("DEBUG", "Basemodel : " +  name + " -> " + jsonArray.get(i).toString());
    		}
            return arrayList;
        } catch (JSONException e) {
        	Log.d("debug", "Got exception : " + name);
            e.printStackTrace();
            return null;
        }
    }
    
    protected ArrayList<String> getArrayList1(String name) {
    	ArrayList<String> arrayList;
        try {
        	JSONArray jsonArray = jsonObject.getJSONArray(name);
        	arrayList = new ArrayList<String>(jsonArray.length());
        	for (int i = 0; i < jsonArray.length(); i++) {
    			arrayList.add(jsonArray.get(i).toString());
    			//Log.d("DEBUG", name + " -> " + jsonArray.get(i).toString());
    		}
            return arrayList;
        } catch (JSONException e) {
        	Log.d("debug", "Got exception : " + name);
            e.printStackTrace();
            return null;
        }
    }

    protected long getLong(String name) {
        try {
            return jsonObject.getLong(name);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    protected int getInt(String name) {
        try {
            return jsonObject.getInt(name);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    protected double getDouble(String name) {
        try {
            return jsonObject.getDouble(name);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    protected boolean getBoolean(String name) {
        try {
            return jsonObject.getBoolean(name);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}


