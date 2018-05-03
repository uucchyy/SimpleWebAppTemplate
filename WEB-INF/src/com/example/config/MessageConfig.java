package com.example.config;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class MessageConfig {


    private static final String RESOURCE_PATH = "message";
    private static MessageConfig config;
    private ResourceBundle bundle;

    private MessageConfig() throws RuntimeException {
        try {
            this.bundle = ResourceBundle.getBundle(RESOURCE_PATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static MessageConfig getMessageConfig() throws RuntimeException {
        if (config == null) {
            config = new MessageConfig();
        }
        return config;
    }

	public String getMessage(String key, Object... args) {
		return MessageFormat.format(bundle.getString(key), args);
	}

}



