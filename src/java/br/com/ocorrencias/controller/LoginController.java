package br.com.ocorrencias.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ocorrencias.bean.Usuario;
import br.com.ocorrencias.dao.InterfaceUsuarioDao;
import br.com.ocorrencias.dao.UsuarioDao;

@Controller
public class LoginController {
	
	@RequestMapping("login")
	public String formLogin() {
		return "login";
	}
	
	@RequestMapping("logar")
	public String logar(String email, String senha, HttpSession session, RedirectAttributes redirectAttributes) {
		InterfaceUsuarioDao dao = new UsuarioDao(); 
		Usuario usuario = dao.realizaLogin(email, senha);
		
		if(usuario == null) {
			redirectAttributes.addFlashAttribute("msg", "Usuário/Senha Inválidos");
			redirectAttributes.addFlashAttribute("email", email);
			return "redirect:login";
		}
		
		session.setAttribute("user", usuario);
		return "redirect:menuPrincipal";
	}
}
