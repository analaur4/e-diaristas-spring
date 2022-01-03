package br.com.adminediaristas.web.services;

import br.com.adminediaristas.core.enums.TipoUsuarioEnum;
import br.com.adminediaristas.core.models.Usuario;
import br.com.adminediaristas.core.repositories.UsuarioRepository;
import br.com.adminediaristas.web.dtos.UsuarioCadastroFormDTO;
import br.com.adminediaristas.web.mappers.WebUsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebUsuarioService {

    private final UsuarioRepository repository;
    private final WebUsuarioMapper mapper;

    public List<Usuario> buscarTodos() {
        return repository.findAll();
    }

    public Usuario cadastrar(UsuarioCadastroFormDTO form) {
        var model = mapper.toModel(form);

        model.setTipoUsuario(TipoUsuarioEnum.ADMIN);

        return repository.save(model);
    }
}
