package br.com.ocorrencias.interceptador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptador extends HandlerInterceptorAdapter {
	
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception {

	      String uri = request.getRequestURI();
	      if(uri.endsWith("login") 
				  || uri.endsWith("logar") 
				  || uri.contains("resources")){
	    	  return true;
	      }
	      
	      if(request.getSession().getAttribute("user") != null) {
	    	  return true;
	      }
	      
	      response.sendRedirect("login");
	      return false;
	  }
}
