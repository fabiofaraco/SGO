package br.com.ocorrencias.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ocorrencias.bean.Ocorrencia;
import br.com.ocorrencias.bean.Requerente;
import br.com.ocorrencias.bean.Usuario;
import br.com.ocorrencias.dao.Dao;
import br.com.ocorrencias.dao.GenericDao;

@Controller
public class MenuController {

	@RequestMapping("menuPrincipal")
	public String formMenu(Model model) {
		Dao<Usuario> daoUsuario = new GenericDao<Usuario>(Usuario.class);
		List<Usuario> usuarios = daoUsuario.getLista("select u from Usuario u");
		
		Dao<Requerente> daoRequerente = new GenericDao<Requerente>(Requerente.class);
		List<Requerente> requerentes = daoRequerente.getLista("select r from Requerente r");
		
		Dao<Ocorrencia> daoOcorrencia = new GenericDao<Ocorrencia>(Ocorrencia.class);
		List<Ocorrencia> ocorrencias = daoOcorrencia.getLista("select o from Ocorrencia o");
		
		Dao<Ocorrencia> daoProtocolo = new GenericDao<Ocorrencia>(Ocorrencia.class);
		List<Ocorrencia> protocolos = daoProtocolo.getLista("select p from Protocolo p");
		
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("requerentes", requerentes);
		model.addAttribute("ocorrencias", ocorrencias);
		model.addAttribute("protocolos", protocolos);
		
		return "menu-principal";
	}
}