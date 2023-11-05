package br.com.fatec.apicep.controller;

import br.com.fatec.apicep.model.Usuario;
import br.com.fatec.apicep.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        ResponseEntity<Usuario> responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(user);

        if (responseEntity.getStatusCodeValue() == HttpStatus.CREATED.value()) {
            model.addAttribute("msgStatusCadastro", "Cadastro feito");
        } else {
            model.addAttribute("msgStatusCadastro", "Erro ao salvar cadastro");
        }

        return "statusCadastro";
    }


}
