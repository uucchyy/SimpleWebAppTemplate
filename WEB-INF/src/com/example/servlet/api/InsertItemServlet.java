package com.example.servlet.api;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.validator.InsertItemValidator;
import com.example.db.DaoItem;
import com.example.model.Item;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class InsertItemServlet extends HttpServlet {

	private static final long serialVersionUID = 38749L;
	private static final Gson gson = new Gson();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {
		process(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {
		
		String _itemId = request.getParameter("itemid");		
		String _name = request.getParameter("name");
		
		InsertItemValidator validator = new InsertItemValidator();
		List<String> errors = validator.validate(_itemId, _name);
		
		int result = 0;
		try {
			
			if (errors.size() == 0) {
				Item item = DaoItem.getItem(_itemId);
				result = DaoItem.insertItem(_itemId, _name);				
			}		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String resultJson;
		if (result == 1) {
			resultJson = "{'result':'OK'}";
		} else {
			resultJson = "{'result':'NG'}";
		}
				
		Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().println(gson.toJson(resultJson));
	}

}