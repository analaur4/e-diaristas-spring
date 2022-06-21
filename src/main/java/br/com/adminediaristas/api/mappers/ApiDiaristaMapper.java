package br.com.adminediaristas.api.mappers;

import br.com.adminediaristas.api.dtos.responses.DiaristasLocalidadeResponse;
import br.com.adminediaristas.core.models.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ApiDiaristaMapper {

    ApiDiaristaMapper INSTANCE = Mappers.getMapper(ApiDiaristaMapper.class);

    @Mapping(target = "fotoUsuario", source = "fotoUsuario.url")
    DiaristasLocalidadeResponse toDiaristaLocalidadeResponse(Usuario model);
}
