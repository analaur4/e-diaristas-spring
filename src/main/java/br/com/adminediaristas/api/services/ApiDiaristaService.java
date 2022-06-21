package br.com.adminediaristas.api.services;

import br.com.adminediaristas.api.dtos.responses.DiaristasLocalidadeResponse;
import br.com.adminediaristas.api.mappers.ApiDiaristaMapper;
import br.com.adminediaristas.core.repositories.UsuarioRepository;
import br.com.adminediaristas.core.services.consultaendereco.adapters.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApiDiaristaService {

    private final UsuarioRepository repository;
    private final ApiDiaristaMapper mapper;
    private final EnderecoService enderecoService;

    public List<DiaristasLocalidadeResponse> buscarDiaristasPorCep(String cep) {
        var codigoIbge = enderecoService.buscarEnderecoPorCep(cep).getIbge();

        return repository.findByCidadesAtendidasCodigoIbge(codigoIbge)
                .stream()
                .map(mapper::toDiaristaLocalidadeResponse)
                .collect(Collectors.toList());
    }

}
