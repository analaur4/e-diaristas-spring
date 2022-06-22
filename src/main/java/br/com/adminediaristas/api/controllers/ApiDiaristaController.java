package br.com.adminediaristas.api.controllers;

import br.com.adminediaristas.api.dtos.responses.DiaristasLocalidadesPagedResponse;
import br.com.adminediaristas.api.services.ApiDiaristaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diaristas")
@RequiredArgsConstructor
public class ApiDiaristaController {

    private final ApiDiaristaService service;

    @GetMapping("/localidades")
    public DiaristasLocalidadesPagedResponse buscarDiaristasPorCep(@RequestParam String cep) {
        return service.buscarDiaristasPorCep(cep);
    }
}
