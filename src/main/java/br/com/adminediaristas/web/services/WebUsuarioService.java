package br.com.adminediaristas.web.services;

import br.com.adminediaristas.core.models.Usuario;
import br.com.adminediaristas.core.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebUsuarioService {

    private final UsuarioRepository repository;

    public List<Usuario> buscarTodos() {
        return repository.findAll();
    }
}
