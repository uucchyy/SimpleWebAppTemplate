package com.example.validator;

import java.util.ArrayList;
import java.util.List;

import com.example.config.MessageConfig;

public class InsertItemValidator {
	
	MessageConfig mc = MessageConfig.getMessageConfig();
	
	public List<String> validate(String itemId, String name) {
		List<String> errors = new ArrayList<String>();
		
		if (itemId == null) {
			errors.add(mc.getMessage("M00004"));
		}
		if (itemId != null) {
			if (isNumber(itemId) == false) {
				errors.add(mc.getMessage("M00004"));
			} 			
		}
		
		return errors;
	}
	
	public boolean isNumber(String num) {
	    try {
	        Integer.parseInt(num);
	        return true;
	        } catch (NumberFormatException e) {
	        return false;
	    }
	}

	public boolean isDoubleNumber(String num) {
	    try {
	        Double.parseDouble(num);
	        return true;
	        } catch (NumberFormatException e) {
	        return false;
	    }
	}

}