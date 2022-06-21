package br.com.adminediaristas.core.services.consultaendereco.adapters;

import br.com.adminediaristas.core.services.consultaendereco.dtos.EnderecoResponse;
import br.com.adminediaristas.core.services.consultaendereco.exceptions.EnderecoServiceException;

public interface EnderecoService {

    EnderecoResponse buscarEnderecoPorCep(String cep) throws EnderecoServiceException;

}
