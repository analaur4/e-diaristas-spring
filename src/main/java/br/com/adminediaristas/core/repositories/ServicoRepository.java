package br.com.adminediaristas.core.repositories;

import br.com.adminediaristas.core.models.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
