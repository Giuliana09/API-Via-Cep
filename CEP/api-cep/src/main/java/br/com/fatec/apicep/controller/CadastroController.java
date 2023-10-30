package br.com.fatec.apicep.controller;

import br.com.fatec.apicep.model.Usuario;
import br.com.fatec.apicep.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("cadastro")
public class CadastroController {

    private final UsuarioService usuarioService;

    @Autowired
    public CadastroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("{cep}")
    public Usuario Cadastro(@PathVariable("cep") String cep) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Usuario> resp = restTemplate.getForEntity(String.format("https://viacep.com.br/ws/%s/json/", cep), Usuario.class);
        Usuario cepResultado = resp.getBody();
        System.out.println(cepResultado);
        return cepResultado;
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Usuario user = usuarioService.Salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
