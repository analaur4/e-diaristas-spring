package br.com.adminediaristas.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlashMessageDTO {

    private String classeCss;
    private String mensagem;
}
