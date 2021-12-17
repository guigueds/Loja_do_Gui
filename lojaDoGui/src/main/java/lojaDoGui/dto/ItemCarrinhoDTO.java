package lojaDoGui.dto;

import lojaDoGui.domain.ItemCarrinho;
import lojaDoGui.domain.Produto;
import lojaDoGui.exception.ApiException;
import lojaDoGui.service.ProdutoService;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ItemCarrinhoDTO {

    @NotNull(message = "Id do produto é obrigatório")
    private Long idProduto;
    private Long quantidade;

    public ItemCarrinho toItemCarrinho(ProdutoService produtoService) throws ApiException {
        Produto produto = new Produto();
        produto = produtoService.buscarPorId(idProduto).toProduto();
        ItemCarrinho item = new ItemCarrinho();
        item.setProduto(produto);
        item.setQuantidade(this.quantidade);

        Double valor = produto.getValor() * this.quantidade;
        if(this.quantidade >= 10) {
            Double valorPerc = valor*0.10;
            item.setValor(valor-valorPerc);
        }else {
            item.setValor(valor);
        }

        return item;

    }
}
