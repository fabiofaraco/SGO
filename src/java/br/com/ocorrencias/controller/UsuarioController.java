package br.com.ocorrencias.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.ocorrencias.bean.Perfil;
import br.com.ocorrencias.bean.Usuario;
import br.com.ocorrencias.dao.Dao;
import br.com.ocorrencias.dao.GenericDao;
import br.com.ocorrencias.dao.InterfaceUsuarioDao;
import br.com.ocorrencias.dao.UsuarioDao;
import br.com.ocorrencias.propertyEditor.PerfilPropertyEditor;
import java.util.ArrayList;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @InitBinder
    public void customizeBinding(WebDataBinder binder) {
        binder.registerCustomEditor(Perfil.class, new PerfilPropertyEditor());
    }

//  ----------------------------------------------------------------------------
    
    @RequestMapping("/lista")
    public String getForm(Model model, String msg) {
        model.addAttribute("usuarios", new ArrayList<>());
        model.addAttribute("msgConsulta", "Para exibir um usuário realize a consulta através do filtro.");

        return "lista-usuario";
    }

//  ----------------------------------------------------------------------------
    
    @RequestMapping("/cadastro")
    public String getFormCadastro(Usuario usuario, Model model) {
        Dao<Perfil> dao = new GenericDao<>(Perfil.class);
        List<Perfil> perfis = dao.getLista("select p from Perfil p");

        model.addAttribute("perfis", perfis);

        return "cadastro-usuario";
    }

//  ----------------------------------------------------------------------------
    
    @RequestMapping("/remover")
    public @ResponseBody
    String removerUsuario(Usuario usuario, RedirectAttributes redirectAttributes) {
        Dao<Usuario> dao = new GenericDao<>(Usuario.class);
        dao.remover(usuario.getId());

        return "Usuário removido com sucesso";
    }

//	--------------------------------------------------------------------------------------------------------------	
    @RequestMapping(value = "/salvar")
    public @ResponseBody
    String salvarUsuario(Usuario usuario, Model model) {
        Dao<Usuario> dao = new GenericDao<>(Usuario.class);
        String msg;

        if (usuario.getId() == 0) {
            dao.incluir(usuario);
            msg = "O usuário "
                    + usuario.getNome()
                    + " foi incluído com sucesso";
        } else {
            dao.alterar(usuario);
            msg = "O usuário "
                    + usuario.getNome()
                    + " foi alterado com sucesso";
        }

        return msg;
    }

//	--------------------------------------------------------------------------------------------------------------	
    @RequestMapping("/carregar")
    public String carregarUsuario(Usuario usuario, Model model) {
        Dao<Usuario> dao = new GenericDao<>(Usuario.class);

        Dao<Perfil> daoPerfil = new GenericDao<>(Perfil.class);
        List<Perfil> perfis = daoPerfil.getLista("select p from Perfil p");

        model.addAttribute("usuario", dao.buscar(usuario.getId()));
        model.addAttribute("perfis", perfis);

        model.addAttribute("nomeFiltro", usuario.getNomeFiltro());
        model.addAttribute("cpfFiltro", usuario.getCpfFiltro());

        return "cadastro-usuario";
    }

//	--------------------------------------------------------------------------------------------------------------	
    @RequestMapping("/filtrar")
    public String filtrar(String nomeFiltro, String cpfFiltro, Model model) {
        InterfaceUsuarioDao dao = new UsuarioDao();
        List<Usuario> usuarios = dao.getListaFiltro(nomeFiltro, cpfFiltro);

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("msgConsulta", "Não há dados para serem exibidos para esta consulta.");
        model.addAttribute("nomeFiltro", nomeFiltro);
        model.addAttribute("cpfFiltro", cpfFiltro);

        return "lista-usuario";
    }

//	--------------------------------------------------------------------------------------------------------------
    @RequestMapping("/validaCpf")
    public @ResponseBody
    String validarCpf(String cpf, int id) {
        InterfaceUsuarioDao dao = new UsuarioDao();

        if (!dao.validarCpf(cpf, id)) {
            return "O CPF digitado já está cadastrado no sistema.";
        }
        return "";
    }

//	--------------------------------------------------------------------------------------------------------------	
}
