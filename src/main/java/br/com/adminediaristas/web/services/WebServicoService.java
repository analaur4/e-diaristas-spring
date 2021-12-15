package br.com.adminediaristas.web.services;

import br.com.adminediaristas.core.exceptions.ServicoNotFoundException;
import br.com.adminediaristas.core.models.Servico;
import br.com.adminediaristas.core.repositories.ServicoRepository;
import br.com.adminediaristas.web.dtos.ServicoFormDTO;
import br.com.adminediaristas.web.mappers.WebServicoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebServicoService {

    private final ServicoRepository repository;
    private final WebServicoMapper mapper;

    public List<Servico> buscarTodos() {
        return repository.findAll();
    }

    public Servico cadastrar(ServicoFormDTO form) {
        var model = mapper.toModel(form);

        return repository.save(model);
    }

    public Servico buscarPorId(Long id) {
        var servicoEncontrado = repository.findById(id);

        if(servicoEncontrado.isPresent()) {
            return servicoEncontrado.get();
        }

        var mensagem = String.format("Serviço com ID %d não encontrado", id);
        throw new ServicoNotFoundException(mensagem);
    }

    public Servico editar(ServicoFormDTO form, Long id) {
        var servicoEncontrado = buscarPorId(id);
        var model = mapper.toModel(form);
        model.setId(servicoEncontrado.getId());

        return repository.save(model);
    }

    public void excluirPorId(Long id) {
        var servicoEncontrado = buscarPorId(id);

        repository.delete(servicoEncontrado);
    }
}
