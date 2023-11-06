package br.com.fatec.apicep.repository;

import br.com.fatec.apicep.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

}
