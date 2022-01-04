package br.com.adminediaristas.web.mappers;

import br.com.adminediaristas.core.models.Usuario;
import br.com.adminediaristas.web.dtos.UsuarioCadastroFormDTO;
import br.com.adminediaristas.web.dtos.UsuarioEdicaoFormDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WebUsuarioMapper {

    WebUsuarioMapper INSTANCE = Mappers.getMapper(WebUsuarioMapper.class);

    Usuario toModel(UsuarioCadastroFormDTO form);

    Usuario toModel(UsuarioEdicaoFormDTO form);

    UsuarioEdicaoFormDTO toForm(Usuario model);
}
