package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SampleController implements Controller {
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("addr", request.getRemoteAddr());
		request.setAttribute("host", request.getRemoteHost());

		return "/view/sample.jsp";
	}
}