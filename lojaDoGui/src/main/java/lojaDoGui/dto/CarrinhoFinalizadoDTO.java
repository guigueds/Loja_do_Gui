package lojaDoGui.dto;

import lojaDoGui.domain.Carrinho;
import lojaDoGui.domain.ItemCarrinho;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class CarrinhoFinalizadoDTO {

    @NotNull(message = "Itens é obrigatório")
    private List<ItemCarrinhoFinalizadoDTO> itens;

    private String cupomAplicado;
    private Double subTotal;
    private Double desconto;
    private Double total;

    public CarrinhoFinalizadoDTO() {

    }

    public CarrinhoFinalizadoDTO(Carrinho carrinho) {
        List<ItemCarrinhoFinalizadoDTO> itens = new ArrayList<>();
        for (ItemCarrinho item : carrinho.getItens()) {
            itens.add(new ItemCarrinhoFinalizadoDTO(item));
        }
        this.itens = itens;
        if(carrinho.getCupom() != null) {
            this.cupomAplicado = carrinho.getCupom().getCodigo();
        }
        this.subTotal = carrinho.getSubTotal();
        this.desconto = carrinho.getDesconto();
        this.total = carrinho.getTotal();
    }
}

