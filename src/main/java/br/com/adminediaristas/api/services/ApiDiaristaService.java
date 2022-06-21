package br.com.adminediaristas.api.services;

import br.com.adminediaristas.api.dtos.responses.DiaristasLocalidadeResponse;
import br.com.adminediaristas.api.mappers.ApiDiaristaMapper;
import br.com.adminediaristas.core.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApiDiaristaService {

    private final UsuarioRepository repository;
    private final ApiDiaristaMapper mapper;

    public List<DiaristasLocalidadeResponse> buscarDiaristasPorCep() {
        return repository.findAll()
                .stream()
                .map(mapper::toDiaristaLocalidadeResponse)
                .collect(Collectors.toList());
    }

}
