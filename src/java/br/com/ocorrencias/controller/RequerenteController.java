package br.com.ocorrencias.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ocorrencias.bean.Cidade;
import br.com.ocorrencias.bean.Estado;
import br.com.ocorrencias.bean.Requerente;
import br.com.ocorrencias.dao.Dao;
import br.com.ocorrencias.dao.EnderecoDao;
import br.com.ocorrencias.dao.GenericDao;
import br.com.ocorrencias.dao.InterfaceEnderecoDao;
import br.com.ocorrencias.dao.InterfaceRequerenteDao;
import br.com.ocorrencias.dao.RequerenteDao;
import br.com.ocorrencias.propertyEditor.CidadePropertyEditor;
import br.com.ocorrencias.propertyEditor.EstadoPropertyEditor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("requerente")
public class RequerenteController {
	
	@InitBinder
	public void customizeBinding(WebDataBinder binder) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		
		binder.registerCustomEditor(Date.class, "dataNascimento", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class, "dataRequisicao", new CustomDateEditor(dateFormat, true));
		
		binder.registerCustomEditor(Estado.class,  new EstadoPropertyEditor());
		binder.registerCustomEditor(Cidade.class,  new CidadePropertyEditor());
	}
	
//	---------------------------------------------------------------------------------------------------------------------	
	
	@RequestMapping("/lista")
	public String getFormListaRequerente(Model model) {
		Dao<Requerente> dao = new GenericDao<Requerente>(Requerente.class);
		List<Requerente> requerentes = dao.getLista("select r from Requerente r");
		
		model.addAttribute("requerentes", requerentes);
		
		return "lista-requerente";
	}
	
//	---------------------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/cadastro")
	public String getFormCadastroRequerente(Requerente requerente, Model model) {
		Dao<Estado> daoEstados = new GenericDao<Estado>(Estado.class);
		List<Estado> estados = daoEstados.getLista("select e from Estado e");
		
		model.addAttribute("estados", estados);
		
		return "cadastro-requerente";
	}
	
//	---------------------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/remover")
	public String removerRequerente(Requerente requerente, RedirectAttributes redirectAttributes) {
		Dao<Requerente> dao = new GenericDao<Requerente>(Requerente.class);
		dao.remover(requerente.getId());
		
		redirectAttributes.addFlashAttribute("msgSuccess", "Requerente removido com sucesso");
		return "redirect:/menuPrincipal";
	}
	
//	---------------------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/salvar")
	public String salvarRequerente(Requerente requerente, RedirectAttributes redirectAttributes) {
		Dao<Requerente> dao = new GenericDao<Requerente>(Requerente.class);
		String msg = "";
		
		if(requerente.getId() == 0) {
			dao.incluir(requerente);
			msg = "O requerente "+requerente.getNome()+" foi inclu�do com sucesso";
		} else {
			dao.alterar(requerente);
			msg = "O requerente "+requerente.getNome()+" foi alterado com sucesso";
		}

		redirectAttributes.addFlashAttribute("msgSuccess", msg);
		return "redirect:/menuPrincipal";
	}
	
//	---------------------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/carregar")
	public String carregarRequerente(Requerente requerente, boolean visualiza, Model model) {
		Dao<Requerente> dao = new GenericDao<Requerente>(Requerente.class);
		
		Requerente r = dao.buscar(requerente.getId());
		
		Dao<Estado> daoEstados = new GenericDao<Estado>(Estado.class);
		List<Estado> estados = daoEstados.getLista("select e from Estado e");
		
		model.addAttribute("estados", estados);
		model.addAttribute("requerente", r);
		
		InterfaceEnderecoDao daoEndereco = new EnderecoDao();
		List<Cidade> cidades = daoEndereco.carregarCidade(r.getEndereco().getCidade().getEstado().getId());
		
		model.addAttribute("cidades", cidades);
		
		if(visualiza) 
		{
			model.addAttribute("disabled", "disabled");
		}
		
		return "cadastro-requerente";
	}
	
//	---------------------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/retornar")
	public String retornar() {
		return "redirect:/requerente/lista";
	}

//	---------------------------------------------------------------------------------------------------------------------
	
	@RequestMapping("carregaVisualizacaoRequerente")
	public @ResponseBody String visualizarRequerente(int id) {
		Dao<Requerente> dao = new GenericDao<Requerente>(Requerente.class);
		Requerente requerente = dao.buscar(id);
		ObjectMapper mapper = new ObjectMapper();
		
		String requerenteJson = "";
		try {
			requerenteJson = mapper.writeValueAsString(requerente);
		} catch (JsonProcessingException e) {
			requerenteJson = "";
			e.printStackTrace();
		}
		
		return requerenteJson;
	}
	
//	---------------------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/validaCpf")
	public @ResponseBody String validarCpf(String cpf, int id) {
		InterfaceRequerenteDao dao = new RequerenteDao();
		
		if(!dao.validarCpf(cpf, id)) {
			return "O CPF digitado j� est� cadastrado no sistema";
		}
		
		return "";
	}

//	---------------------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/carregaCidade")
	public @ResponseBody String carregarCidades(int idEstado) {
		ObjectMapper mapper = new ObjectMapper();
		InterfaceEnderecoDao dao = new EnderecoDao();
		List<Cidade> cidades = dao.carregarCidade(idEstado);
		
		try {
			String returnJson = mapper.writeValueAsString(cidades);
			
			return returnJson;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "";
	}
}
