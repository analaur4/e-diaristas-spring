package br.com.adminediaristas.api.services;

import br.com.adminediaristas.api.dtos.responses.DiaristasLocalidadeResponse;
import br.com.adminediaristas.api.dtos.responses.DiaristasLocalidadesPagedResponse;
import br.com.adminediaristas.api.mappers.ApiDiaristaMapper;
import br.com.adminediaristas.core.models.Usuario;
import br.com.adminediaristas.core.repositories.UsuarioRepository;
import br.com.adminediaristas.core.services.consultaendereco.adapters.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApiDiaristaService {

    private final UsuarioRepository repository;
    private final ApiDiaristaMapper mapper;
    private final EnderecoService enderecoService;

    public DiaristasLocalidadesPagedResponse buscarDiaristasPorCep(String cep) {
        var codigoIbge = enderecoService.buscarEnderecoPorCep(cep).getIbge();

        var usuarioSort = Sort.sort(Usuario.class);
        var sort = usuarioSort.by(Usuario::getReputacao).descending();
        var NUMERO_PAGINA = 0;
        var TAMANHO_PAGINA = 6;
        var pageable = PageRequest.of(NUMERO_PAGINA, TAMANHO_PAGINA, sort);

        var resultado = repository.findByCidadesAtendidasCodigoIbge(codigoIbge, pageable);
        var diaristas = resultado.getContent()
                .stream()
                .map(mapper::toDiaristaLocalidadeResponse)
                .collect(Collectors.toList());

        return new DiaristasLocalidadesPagedResponse(diaristas, TAMANHO_PAGINA, resultado.getTotalElements());
    }

}
