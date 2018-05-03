package com.example.servlet.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.controller.SampleController;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 38749L;
	
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
		String uri = request.getRequestURI();
		
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex + 1);
		String dispatchUrl = null;

		if (action.equals("") || action.equals("index.html")) {
			dispatchUrl = "/view/index.jsp";
		} else if (action.equals("sample.html")) {
			SampleController controller = new SampleController();
			dispatchUrl = controller.handleRequest(request, response);
		}

		if (dispatchUrl != null) {
			RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
			rd.forward(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}