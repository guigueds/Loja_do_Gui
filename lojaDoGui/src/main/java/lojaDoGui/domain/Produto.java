package lojaDoGui.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descricao;
    private Double valor;

    @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private ItemCarrinho item;
}
