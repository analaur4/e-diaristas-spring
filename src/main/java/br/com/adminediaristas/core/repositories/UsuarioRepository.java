package br.com.adminediaristas.core.repositories;

import br.com.adminediaristas.core.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
