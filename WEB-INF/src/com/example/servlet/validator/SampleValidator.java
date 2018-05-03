package com.example.servlet.validator;

import java.util.ArrayList;
import java.util.List;

import com.example.config.ApplicationConfig;
import com.example.config.MessageConfig;
import com.example.form.SampleForm;

public class SampleValidator {
	
	MessageConfig mc = MessageConfig.getMessageConfig();
	
	public List<String> validate(SampleForm form) {
		List<String> errors = new ArrayList<String>();
		String actionType = form.getActionType();
		if (actionType != null) {
//			if (actionType.equals("encode")) {
//				String before = form.getBefore();
//				if (before == null || before.trim().isEmpty()) {
//					errors.add(mc.getMessage("M00001"));
//				}			
//			} 			
		}
		
		ApplicationConfig config = ApplicationConfig.getApplicationConfig();
		config.gettest();
		return errors;
	}

}