package br.com.ocorrencias.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ocorrencias.bean.Protocolo;
import br.com.ocorrencias.dao.Dao;
import br.com.ocorrencias.dao.GenericDao;

@Controller
@RequestMapping("relatorio")
public class RelatorioController {
	
	@RequestMapping("certidao")
	public String imprimirCertidao(int idProtocolo, HttpSession session, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		if(idProtocolo == 0) {
			redirectAttributes.addFlashAttribute("showModalCertidao", true);
		} else {		
			Dao<Protocolo> dao = new GenericDao<Protocolo>(Protocolo.class);
			Protocolo protocolo = dao.buscar(idProtocolo);
			List<Protocolo> arrProtocolos = new ArrayList<Protocolo>();
			
			arrProtocolos.add(protocolo);
			
			try {
				System.out.println("Gerando...");
				
				response.setContentType("application/pdf");
							
				JasperReport report = JasperCompileManager.compileReport(session.getServletContext().getRealPath("/certidao.jrxml"));
				JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(arrProtocolos));
				
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				
				JasperExportManager.exportReportToPdfStream(print, stream);
							
				response.setHeader("Pragma","");
				response.setHeader("Cache-Control","");
				response.setHeader("Expires",""); 
				
				OutputStream out = response.getOutputStream();  
				stream.writeTo(out);
				response.setContentLength(stream.size());
				out.flush();	
				out.close();
				
				System.out.println("Gerado...");
			} catch (JRException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:/menuPrincipal";
	}
}
