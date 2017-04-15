package br.com.teste;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;


public class RelatorioTeste {

	public static void main(String[] args) throws JRException {
		System.out.println("Gerando relatório...");
		// lista com os nossos clientes
		List<String> lista = new ArrayList<String>();

		lista.add("Fábio");
		lista.add("Laura");
		lista.add("Daniel");

		// compilacao do JRXML
		JasperReport report = JasperCompileManager
		.compileReport("teste.jrxml");

		// preenchimento do relatorio, note que o metodo recebe 3 parametros:
		// 1 - o relatorio
		//
		// 2 - um Map, com parametros que sao passados ao relatorio
		// no momento do preenchimento. No nosso caso eh null, pois nao
		// estamos usando nenhum parametro
		//
		// 3 - o data source. Note que nao devemos passar a lista diretamente,
		// e sim "transformar" em um data source utilizando a classe
		// JRBeanCollectionDataSource
		JasperPrint print = JasperFillManager.fillReport(report, null,
		new JRBeanCollectionDataSource(lista));

		// exportacao do relatorio para outro formato, no caso PDF
		JasperExportManager.exportReportToPdfFile(print,
		"teste.pdf");

		System.out.println("Relatório gerado.");
		
		JasperViewer.viewReport(print, false);
	}

}
