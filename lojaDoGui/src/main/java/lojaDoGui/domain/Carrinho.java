package lojaDoGui.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;
import lombok.Data;

@Data
@Entity
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "carrinho", fetch = FetchType.LAZY)
    private List<ItemCarrinho> itens;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CUPOM", referencedColumnName = "ID")
    private Cupom cupom;

    private Double subTotal;
    private Double desconto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Double total;


}
