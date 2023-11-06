package br.com.fatec.apicep.controller;

import br.com.fatec.apicep.model.Usuario;
import br.com.fatec.apicep.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class CadastroController {

    private final UsuarioService usuarioService;

    @Autowired
    public CadastroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/cadastro")
    public String formulario(Model model) {
        Usuario usuario =  new Usuario();
        model.addAttribute("usuario", usuario );
        return "cadastro";
    }


    @PostMapping("/cadastro")
    public String create(@ModelAttribute Usuario usuario, Model model) {
        Usuario user = usuarioService.Salvar(usuario);

        if (user == null) {
            model.addAttribute("msgStatusCadastro", "Erro ao salvar cadastro");
            model.addAttribute("classe", "alert alert-danger");
            model.addAttribute("erro", usuarioService.getMensagem());
            model.addAttribute("status", false);
            return "statusCadastro";

        } else {
            model.addAttribute("msgStatusCadastro", "Cadastro feito");
            model.addAttribute("classe", "alert alert-info");
            model.addAttribute("status", true);
        }


        return "statusCadastro";
    }



}
