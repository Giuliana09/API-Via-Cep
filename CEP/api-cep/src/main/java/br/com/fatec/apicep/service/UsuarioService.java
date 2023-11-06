package br.com.fatec.apicep.service;

import br.com.fatec.apicep.model.Usuario;
import br.com.fatec.apicep.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private String mensagem;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario Salvar(Usuario usuario) {
        if(verificacao(usuario)){
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    private boolean isCepValido(String cep) {
        return cep != null && cep.matches("\\d{8}");
    }

    @Transactional
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuario não encontrado")
        );
    }

    public boolean verificacao(Usuario usuario){
        if (!isCepValido(usuario.getCep())) {
            setMensagem("erro no CEP");
            return false;
        }  else if (usuario.getNome() == "" || usuario.getSobrenome() == "") {
            setMensagem("preencha os campos nome e sobrenome");
            return false;
        } else if ("undefined".equals(usuario.getLocalidade())){
            setMensagem("CEP não existente");
            return false;
        } else {
            return true;
        }
    }

    public void setMensagem(String mensagem){
        this.mensagem =  mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
