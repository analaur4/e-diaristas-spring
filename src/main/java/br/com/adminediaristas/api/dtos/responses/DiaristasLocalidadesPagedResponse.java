package br.com.adminediaristas.api.dtos.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(SnakeCaseStrategy.class)
public class DiaristasLocalidadesPagedResponse {

    public DiaristasLocalidadesPagedResponse(List<DiaristasLocalidadeResponse> diaristas, Integer tamanhoPagina, Long totalElementos) {
        this.diaristas = diaristas;
        this.quantidadeDiaristas = totalElementos > tamanhoPagina ? totalElementos - tamanhoPagina : 0;
    }

    private List<DiaristasLocalidadeResponse> diaristas;
    private Long quantidadeDiaristas;

}
