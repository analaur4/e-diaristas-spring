package br.com.adminediaristas.web.mappers;

import br.com.adminediaristas.core.models.Servico;
import br.com.adminediaristas.web.dtos.ServicoFormDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Mapper(componentModel = "spring")
public interface WebServicoMapper {

    WebServicoMapper INSTANCE = Mappers.getMapper(WebServicoMapper.class);

    Servico toModel(ServicoFormDTO dto);

    ServicoFormDTO toForm(Servico model);
}
