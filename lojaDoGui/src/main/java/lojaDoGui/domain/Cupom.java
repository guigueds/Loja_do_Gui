package lojaDoGui.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name="CD_CUPOM")
    private String codigo;

    private String descricao;
    private Double percentual;

}