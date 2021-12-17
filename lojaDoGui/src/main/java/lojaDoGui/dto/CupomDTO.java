package lojaDoGui.dto;

import lojaDoGui.domain.Cupom;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CupomDTO {
    private Long id;
    @NotNull(message = "Código do cupom é obrigatório")
    private String codigo;
    @NotNull(message = "Descrição do cupom é obrigatório")
    private String descricao;
    @NotNull(message = "Percentual de desconto do cupom é obrigatório")
    private Double percentual;

    public CupomDTO() {

    }

    public CupomDTO(Cupom cupom) {
        this.id = cupom.getId();
        this.codigo = cupom.getCodigo();
        this.descricao = cupom.getDescricao();
        this.percentual = cupom.getPercentual();
    }

    public Cupom toCupom() {
        Cupom cupom = new Cupom();
        cupom.setId(id);
        cupom.setCodigo(codigo);
        cupom.setDescricao(descricao);
        cupom.setPercentual(percentual);
        return cupom;
    }
}
