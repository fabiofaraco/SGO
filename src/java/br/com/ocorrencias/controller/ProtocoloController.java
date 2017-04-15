package br.com.ocorrencias.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ocorrencias.bean.Ocorrencia;
import br.com.ocorrencias.bean.Protocolo;
import br.com.ocorrencias.bean.Requerente;
import br.com.ocorrencias.dao.Dao;
import br.com.ocorrencias.dao.EnderecoDao;
import br.com.ocorrencias.dao.GenericDao;
import br.com.ocorrencias.dao.InterfaceEnderecoDao;
import br.com.ocorrencias.dao.InterfaceOcorrenciaDao;
import br.com.ocorrencias.dao.InterfaceRequerenteDao;
import br.com.ocorrencias.dao.OcorrenciaDao;
import br.com.ocorrencias.dao.RequerenteDao;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("protocolo")
public class ProtocoloController {
	
	@RequestMapping("/lista")
	public String getFormListaProtocolo(Model model) {
		return "lista-protocolo";
	}

//	-----------------------------------------------------------------------------
	
	@RequestMapping("/cadastro")
	public String getFormCadastroRequerente(Requerente requerente, Model model) {
		return "cadastro-protocolo";
	}
	
//	-----------------------------------------------------------------------------	
	
	@RequestMapping("/carregaEndereco")
	public @ResponseBody String carregarCidades(String data) {
		ObjectMapper mapper = new ObjectMapper();
		InterfaceEnderecoDao dao = new EnderecoDao();
				
		try {
			SimpleDateFormat frmt = new SimpleDateFormat("dd/MM/yyyy");
			Date dataOcorrencia = frmt.parse(data);
			
			List<Ocorrencia> endOcorrencias = dao.carregarEndereco(dataOcorrencia);
			
			String returnJson = mapper.writeValueAsString(endOcorrencias);
			
			return returnJson;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
//	-----------------------------------------------------------------------------
	
	@RequestMapping("/consultar")
	public String consultar(String cpf, int idOcorrencia, Model model) {
		try {
			InterfaceRequerenteDao daoRequerente = new RequerenteDao();
			Requerente requerente = daoRequerente.buscarCpf(cpf);
			
			Dao<Ocorrencia> daoOcorrencia = new GenericDao<Ocorrencia>(Ocorrencia.class);
			Ocorrencia ocorrencia = daoOcorrencia.buscar(idOcorrencia);
			
			model.addAttribute("requerente", requerente);
			model.addAttribute("ocorrencia", ocorrencia);
			model.addAttribute("openModal", true);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "cadastro-protocolo";
	}
	
//	--------------------------------------------------------------------------------------------------------------	
	
	@RequestMapping("/salvar")
	public String salvarProtocolo(int idRequerente, int idOcorrencia, RedirectAttributes redirectAttributes) {
		Dao<Protocolo> dao = new GenericDao<Protocolo>(Protocolo.class);
		
		Protocolo protocolo = new Protocolo();
		
		protocolo.getRequerente().setId(idRequerente);
		protocolo.getOcorrencia().setId(idOcorrencia);
		
		dao.incluir(protocolo);
		
		String msg = "Protocolo inserido com sucesso";
		
		redirectAttributes.addFlashAttribute("msgSuccess", msg);
		return "redirect:/menuPrincipal";
	}

//	--------------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/retornar")
	public String retornar() 
	{
		return "redirect:/protocolo/lista";
	}
	
//	--------------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/validaCpf")
	public @ResponseBody String validarCpf(String cpf) {
		InterfaceRequerenteDao dao = new RequerenteDao();
		
		if(dao.validarCpf(cpf, 0)) {
			return "O CPF digitado n�o est� cadastrado no sistema.";
		}
		
		return "";
	}

//	--------------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/validaDataOcorrencia")
	public @ResponseBody String validarDataOcorrencia(String data) {
		InterfaceOcorrenciaDao dao = new OcorrenciaDao();
		
		SimpleDateFormat frmt = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Date dtOcorrencia = frmt.parse(data);
			
			if(!dao.validarDataOcorrencia(dtOcorrencia)) {
				return "Nenhum ocorr�ncia foi encontrada para esta data.";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return "";
	}
}
