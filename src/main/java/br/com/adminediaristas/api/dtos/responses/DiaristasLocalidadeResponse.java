package br.com.adminediaristas.api.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaristasLocalidadeResponse {

    private String nomeCompleto;
    private String reputacao;
    private String fotoUsuario;
    private String cidade;

}
