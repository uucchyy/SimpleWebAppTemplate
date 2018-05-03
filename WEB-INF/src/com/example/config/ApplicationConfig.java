package com.example.config;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

public class ApplicationConfig {
    private static final String RESOURCE_PATH = "application";
    private static ApplicationConfig config;
    private ResourceBundle bundle;

    private boolean debug = false;
    private String test = null;

    private ApplicationConfig() throws RuntimeException {
        try {
            this.bundle = ResourceBundle.getBundle(RESOURCE_PATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.debug = getBoolean("debug");
        this.test = getString("test");
    }

    public static ApplicationConfig getApplicationConfig() throws RuntimeException {
        if (config == null) {
            config = new ApplicationConfig();
        }
        return config;
    }

    private String getString(ResourceBundle bundle, String key) {
        return bundle.getString(key).trim();
    }

    private String getString(String key) {
        String value = getString(this.bundle, key);
        return value;
    }

    private boolean getBoolean(String key) {
        String value = getString(this.bundle, key);
        return Boolean.valueOf(value).booleanValue();
    }

    private int getInt(String key) {
        String value = getString(this.bundle, key);
        return Integer.parseInt(value);
    }

    private long getLong(String key) {
        String value = getString(this.bundle, key);
        return Long.parseLong(value);
    }

    private List<String> getList(String key) {
        String value = getString(key);
        List<String> list = new ArrayList<String>();
        StringTokenizer token = new StringTokenizer(value, ",");
        while (token.hasMoreTokens()) {
            String str = token.nextToken();
            list.add(str.trim());
        }
        return list;
    }

    public boolean isDebug() {
        return this.debug;
    }
    
    public String gettest() {
        return this.test;
    }
}



