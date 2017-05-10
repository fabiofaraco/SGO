package br.com.ocorrencias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuController {

	@RequestMapping("menuPrincipal")
	public String formMenu(Model model) {
		return "menu-principal";
	}
}