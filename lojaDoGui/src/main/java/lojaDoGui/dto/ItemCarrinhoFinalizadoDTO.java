package lojaDoGui.dto;

import lojaDoGui.domain.ItemCarrinho;
import lombok.Data;

@Data
public class ItemCarrinhoFinalizadoDTO {
    private Long id;
    private ProdutoDTO produto;
    private Long quantidade;
    private Double valor;

    public ItemCarrinhoFinalizadoDTO() {

    }

    public ItemCarrinhoFinalizadoDTO(ItemCarrinho item) {
        this.id = item.getId();
        this.produto = new ProdutoDTO(item.getProduto());
        this.quantidade = item.getQuantidade();
        this.valor = item.getValor();
    }
}
