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
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @InitBinder
    public void customizeBinding(WebDataBinder binder) {
        binder.registerCustomEditor(Perfil.class, new PerfilPropertyEditor());
    }

//	--------------------------------------------------------------------------------------------------------------	
    @RequestMapping("/lista")
    public String getForm(Model model, String msg) {
        Dao<Usuario> dao = new GenericDao<Usuario>(Usuario.class);
        List<Usuario> usuarios = dao.getLista("select u from Usuario u");

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("msgSuccess", msg);

        return "lista-usuario";
    }

//	--------------------------------------------------------------------------------------------------------------	
    @RequestMapping("/cadastro")
    public String getFormCadastro(Usuario usuario, Model model) {
        Dao<Perfil> dao = new GenericDao<Perfil>(Perfil.class);
        List<Perfil> perfis = dao.getLista("select p from Perfil p");

        model.addAttribute("perfis", perfis);

        return "cadastro-usuario";
    }

//	--------------------------------------------------------------------------------------------------------------	
    @RequestMapping("/remover")
    public String removerUsuario(Usuario usuario, RedirectAttributes redirectAttributes) {
        Dao<Usuario> dao = new GenericDao<Usuario>(Usuario.class);
        dao.remover(usuario.getId());

        redirectAttributes.addFlashAttribute("msgSuccess", "Usuário removido com sucesso");
        return "redirect:/usuario/lista";
    }

//	--------------------------------------------------------------------------------------------------------------	
    @RequestMapping(value="/salvar")
    public @ResponseBody String salvarUsuario(Usuario usuario, Model model) {
        Dao<Usuario> dao = new GenericDao<Usuario>(Usuario.class);
        String msg;

        if (usuario.getId() == 0) {
            dao.incluir(usuario);
            msg = "O usuário " + usuario.getNome() + " foi incluído com sucesso";
        } else {
            dao.alterar(usuario);
            msg = "O usuário " + usuario.getNome() + " foi alterado com sucesso";
        }
        
        return msg;
    }

//	--------------------------------------------------------------------------------------------------------------	
    @RequestMapping("/carregar")
    public String carregarUsuario(Usuario usuario, boolean visualiza, Model model) {
        Dao<Usuario> dao = new GenericDao<Usuario>(Usuario.class);

        Dao<Perfil> daoPerfil = new GenericDao<Perfil>(Perfil.class);
        List<Perfil> perfis = daoPerfil.getLista("select p from Perfil p");

        model.addAttribute("usuario", dao.buscar(usuario.getId()));
        model.addAttribute("perfis", perfis);

        if (visualiza) {
            model.addAttribute("disabled", "disabled");
        }

        return "cadastro-usuario";
    }

//	--------------------------------------------------------------------------------------------------------------	
    @RequestMapping("/retornar")
    public String retornar() {
        return "redirect:/usuario/lista";
    }

//	--------------------------------------------------------------------------------------------------------------
    @RequestMapping("/validaCpf")
    public @ResponseBody
    String validarCpf(String cpf, int id) {
        InterfaceUsuarioDao dao = new UsuarioDao();

        if (!dao.validarCpf(cpf, id)) {
            return "O CPF digitado j� est� cadastrado no sistema";
        }
        return "";
    }

//	--------------------------------------------------------------------------------------------------------------	
}
