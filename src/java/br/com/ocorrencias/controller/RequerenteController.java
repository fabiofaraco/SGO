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
import java.util.ArrayList;

@Controller
@RequestMapping("requerente")
public class RequerenteController {

    @InitBinder
    public void customizeBinding(WebDataBinder binder) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        binder.registerCustomEditor(Date.class, "dataNascimento", new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Date.class, "dataRequisicao", new CustomDateEditor(dateFormat, true));

        binder.registerCustomEditor(Estado.class, new EstadoPropertyEditor());
        binder.registerCustomEditor(Cidade.class, new CidadePropertyEditor());
    }

//  ----------------------------------------------------------------------------
    @RequestMapping("/lista")
    public String getFormLista(Model model) {
        model.addAttribute("requerentes", new ArrayList<>());
        model.addAttribute("msgConsulta", "Para exibir um requerente realize a consulta através do filtro.");

        return "lista-requerente";
    }

//  ----------------------------------------------------------------------------
    @RequestMapping("/cadastro")
    public String getFormCadastro(Requerente requerente, Model model) {
        Dao<Estado> daoEstados = new GenericDao<>(Estado.class);
        List<Estado> estados = daoEstados.getLista("select e from Estado e");

        model.addAttribute("estados", estados);

        return "cadastro-requerente";
    }

//  ----------------------------------------------------------------------------
    @RequestMapping("/remover")
    public @ResponseBody
    String remover(Requerente requerente) {
        Dao<Requerente> dao = new GenericDao<>(Requerente.class);

        dao.remover(requerente.getId());

        return "Requerente removido com sucesso.";
    }

//  ----------------------------------------------------------------------------
    @RequestMapping("/salvar")
    public @ResponseBody
    String salvar(Requerente requerente) {
        Dao<Requerente> dao = new GenericDao<>(Requerente.class);
        String msg;

        try {
            if (requerente.getId() == 0) {
                dao.incluir(requerente);
                msg = "O requerente " + requerente.getNome() + " foi incluído com sucesso";
            } else {
                dao.alterar(requerente);
                msg = "O requerente " + requerente.getNome() + " foi alterado com sucesso";
            }
        } catch (Exception e) {
            throw e;
        }

        return msg;
    }

//  ----------------------------------------------------------------------------
    @RequestMapping("/carregar")
    public String carregar(Requerente requerente, Model model) {
        Dao<Requerente> dao = new GenericDao<>(Requerente.class);

        Requerente r = dao.buscar(requerente.getId());

        Dao<Estado> daoEstados = new GenericDao<>(Estado.class);
        List<Estado> estados = daoEstados.getLista("select e from Estado e");

        model.addAttribute("estados", estados);
        model.addAttribute("requerente", r);

        InterfaceEnderecoDao daoEndereco = new EnderecoDao();
        List<Cidade> cidades = daoEndereco.carregarCidade(r.getEndereco().getCidade().getEstado().getId());

        model.addAttribute("cidades", cidades);

        return "cadastro-requerente";
    }

//  ----------------------------------------------------------------------------
    
    @RequestMapping("/validaCpf")
    public @ResponseBody
    String validarCpf(String cpf, int id) {
        InterfaceRequerenteDao dao = new RequerenteDao();

        if (!dao.validarCpf(cpf, id)) {
            return "O CPF digitado já está cadastrado no sistema";
        }

        return "";
    }

//  ----------------------------------------------------------------------------
    
    @RequestMapping("/carregaCidade")
    public @ResponseBody
    String carregarCidades(int idEstado) {
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

//  ----------------------------------------------------------------------------    
    
    @RequestMapping("/filtrar")
    public String filtrar(String nomeFiltro, String cpfFiltro, Model model) {
        InterfaceRequerenteDao dao = new RequerenteDao();

        try {
            List<Requerente> requerentes = dao.getListaFiltro(nomeFiltro, cpfFiltro);

            model.addAttribute("requerentes", requerentes);
            model.addAttribute("msgConsulta", "Não há dados para serem exibidos para esta consulta.");
            model.addAttribute("nomeFiltro", nomeFiltro);
            model.addAttribute("cpfFiltro", cpfFiltro);
        } catch (Exception e) {
            throw e;
        }

        return "lista-requerente";
    }
}
