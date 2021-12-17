package lojaDoGui.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ItemCarrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUTO_ID")
    private Produto produto;

    private Long quantidade;

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    private Double valor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carrinho_id", referencedColumnName = "id", nullable = false)
    private Carrinho carrinho;

    public void setProduto(Produto produto) {
    }

    public void setQuantidade(Long quantidade) {
    }
}

