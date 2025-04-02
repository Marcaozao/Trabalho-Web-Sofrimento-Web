package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "pedido_id")
    private List<ItensPedido> itens;

    @ManyToOne
    private Cliente cliente_id;

    private Double valor_total;

    private String observacoes;

    private String status;

    public Pedido(List<ItensPedido> itens, Cliente cliente_id, Double valor_total, String observacao)
    {

    }

    public Pedido() {

    }

}
