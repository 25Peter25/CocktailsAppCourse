package com.peter.peterCocktails.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//ked zadam list http://localhost:8080/PeterCocktails/ tak budem presmerovany na http://localhost:8080/PeterCocktails/ingredients
@Controller
public class MainController {
	
	@RequestMapping("/")
	public void index(HttpServletRequest request, HttpServletResponse response) {
		
		StringBuffer link = request.getRequestURL();
		
		try {
			response.sendRedirect(link + "ingredients");
		} catch(IOException e) {
			e.printStackTrace();
			}
				
	}

}
