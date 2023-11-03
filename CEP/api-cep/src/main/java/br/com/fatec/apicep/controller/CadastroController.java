package br.com.fatec.apicep.controller;

import br.com.fatec.apicep.model.Usuario;
import br.com.fatec.apicep.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class CadastroController {

    private final UsuarioService usuarioService;

    @Autowired
    public CadastroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("cadastro")
    public String Formulario() {
        return "cadastro";
    }

    @GetMapping("/cadastro/{cep}")
    @ResponseBody
    public Usuario Cadastro(@PathVariable("cep") String cep) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Usuario> resp = restTemplate.getForEntity(String.format("https://viacep.com.br/ws/%s/json/", cep), Usuario.class);
        Usuario cepResultado = resp.getBody();
        System.out.println(cepResultado);
        return cepResultado;
    }

    @PostMapping("cadastro/{cep}")
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Usuario user = usuarioService.Salvar(usuario);
        ResponseEntity<Usuario> responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(user);

        if (responseEntity.getStatusCodeValue() == HttpStatus.CREATED.value()) {
            System.out.println("Cadastro feito");
        } else {
            System.out.println("Erro ao salvar cadastro");
        }

        return responseEntity;
    }


}
