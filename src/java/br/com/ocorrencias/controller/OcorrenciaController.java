package br.com.ocorrencias.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ocorrencias.bean.Cidade;
import br.com.ocorrencias.bean.Estado;
import br.com.ocorrencias.bean.Evento;
import br.com.ocorrencias.bean.Ocorrencia;
import br.com.ocorrencias.dao.Dao;
import br.com.ocorrencias.dao.EnderecoDao;
import br.com.ocorrencias.dao.GenericDao;
import br.com.ocorrencias.dao.InterfaceEnderecoDao;
import br.com.ocorrencias.propertyEditor.CidadePropertyEditor;
import br.com.ocorrencias.propertyEditor.EstadoPropertyEditor;
import br.com.ocorrencias.propertyEditor.EventoPropertyEditor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("ocorrencia")
public class OcorrenciaController {

	@InitBinder
	public void customizeBinding(WebDataBinder binder) {
		binder.registerCustomEditor(Evento.class,  new EventoPropertyEditor());
		binder.registerCustomEditor(Estado.class,  new EstadoPropertyEditor());
		binder.registerCustomEditor(Cidade.class,  new CidadePropertyEditor());
	}
	
//	--------------------------------------------------------------------------------------------------------------	
	
	@RequestMapping("/lista")
	public String formLista() {
		return "lista-ocorrencia";
	}

//	--------------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/cadastro")
	public String formCadastro(Ocorrencia ocorrencia, Model model) {
		Dao<Evento> dao = new GenericDao<Evento>(Evento.class);
		List<Evento> eventos = dao.getLista("select e from Evento e");
		
		Dao<Estado> daoEstados = new GenericDao<Estado>(Estado.class);
		List<Estado> estados = daoEstados.getLista("select e from Estado e");
		
		model.addAttribute("eventos", eventos);
		model.addAttribute("estados", estados);
				
		return "cadastro-ocorrencia";
	}

//	--------------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/salvar")
	public String salvarOcorrencia(Ocorrencia ocorrencia, RedirectAttributes redirectAttributes) {
		Dao<Ocorrencia> dao = new GenericDao<Ocorrencia>(Ocorrencia.class);
		String msg = "";
		
		if(ocorrencia.getId() == 0) {
			dao.incluir(ocorrencia);
			msg = "Ocorr�ncia inclu�da com sucesso";
		} else {
			dao.alterar(ocorrencia);
			msg = "Ocorr�ncia alterada com sucesso";
		}
		
		redirectAttributes.addFlashAttribute("msgSuccess", msg);
		return "redirect:/menuPrincipal";
	}
	
//	--------------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/carregar")
	public String carregarUsuario(Ocorrencia ocorrencia, boolean visualiza, Model model) {
		Dao<Ocorrencia> dao = new GenericDao<Ocorrencia>(Ocorrencia.class);
		
		Ocorrencia occurrence = dao.buscar(ocorrencia.getId());
		
		Dao<Estado> daoEstados = new GenericDao<Estado>(Estado.class);
		List<Estado> estados = daoEstados.getLista("select e from Estado e");
		
		InterfaceEnderecoDao daoEndereco = new EnderecoDao();
		List<Cidade> cidades = daoEndereco.carregarCidade(occurrence.getEndereco().getCidade().getEstado().getId());
		
		Dao<Evento> daoEvento = new GenericDao<Evento>(Evento.class);
		List<Evento> eventos = daoEvento.getLista("select ev from Evento ev");
		
		model.addAttribute("estados", estados);
		model.addAttribute("cidades", cidades);
		model.addAttribute("eventos", eventos);
		model.addAttribute("ocorrencia", occurrence);
		
		if(visualiza) 
		{
			model.addAttribute("disabled", "disabled");
		}
		
		return "cadastro-ocorrencia";
	}

//	--------------------------------------------------------------------------------------------------------------
	
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
	
//	--------------------------------------------------------------------------------------------------------------

	@RequestMapping("/retornar")
	public String retornar() 
	{
		return "redirect:/ocorrencia/lista";
	}

//	--------------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/remover")
	public String removerOcorrencia(Ocorrencia ocorrencia, RedirectAttributes redirectAttributes) {
		Dao<Ocorrencia> dao = new GenericDao<Ocorrencia>(Ocorrencia.class);
		dao.remover(ocorrencia.getId());
		
		redirectAttributes.addFlashAttribute("msgSuccess", "Ocorr�ncia removida com sucesso");
		return "redirect:/menuPrincipal";
	}
}
