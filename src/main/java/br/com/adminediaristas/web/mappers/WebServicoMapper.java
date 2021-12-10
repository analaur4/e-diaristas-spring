package br.com.adminediaristas.web.mappers;

import br.com.adminediaristas.core.models.Servico;
import br.com.adminediaristas.web.dtos.ServicoFormDTO;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class WebServicoMapper {

    public Servico toModel(ServicoFormDTO dto) {
        if(isNull(dto)) {
            throw new IllegalArgumentException("ServicoFormDTO é nulo");
        }

        return Servico.builder()
                .nome(dto.getNome())
                .valorMinimo(dto.getValorMinimo())
                .qtdHoras(dto.getQtdHoras())
                .porcentagemComissao(dto.getPorcentagemComissao())
                .horasQuarto(dto.getHorasQuarto())
                .valorQuarto(dto.getValorQuarto())
                .horasSala(dto.getHorasSala())
                .valorSala(dto.getValorSala())
                .horasBanheiro(dto.getHorasBanheiro())
                .valorBanheiro(dto.getValorBanheiro())
                .horasCozinha(dto.getHorasCozinha())
                .valorCozinha(dto.getValorCozinha())
                .horasQuintal(dto.getHorasQuintal())
                .valorQuintal(dto.getValorQuintal())
                .horasOutros(dto.getHorasOutros())
                .valorOutros(dto.getValorOutros())
                .icone(dto.getIcone())
                .posicao(dto.getPosicao())
                .build();
    }

    public ServicoFormDTO toForm(Servico model) {
        if(isNull(model)) {
            throw new IllegalArgumentException("Model Servico é nulo");
        }

        return ServicoFormDTO.builder()
                .nome(model.getNome())
                .valorMinimo(model.getValorMinimo())
                .qtdHoras(model.getQtdHoras())
                .porcentagemComissao(model.getPorcentagemComissao())
                .horasQuarto(model.getHorasQuarto())
                .valorQuarto(model.getValorQuarto())
                .horasSala(model.getHorasSala())
                .valorSala(model.getValorSala())
                .horasBanheiro(model.getHorasBanheiro())
                .valorBanheiro(model.getValorBanheiro())
                .horasCozinha(model.getHorasCozinha())
                .valorCozinha(model.getValorCozinha())
                .horasQuintal(model.getHorasQuintal())
                .valorQuintal(model.getValorQuintal())
                .horasOutros(model.getHorasOutros())
                .valorOutros(model.getValorOutros())
                .icone(model.getIcone())
                .posicao(model.getPosicao())
                .build();
    }
}
