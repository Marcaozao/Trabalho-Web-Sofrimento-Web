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

    @OneToMany
    private List<ItensPedido> itens;

    @ManyToOne
    private Cliente cliente;

    private Double valor_total;

    private String observacoes;

    private String status;

    public Pedido(List<ItensPedido> itens, Cliente cliente, Double valor_total, String observacao)
    {

        this.itens = itens;
        this.cliente = cliente;
        this.valor_total = valor_total;
        this.observacoes = observacao;

    }

    public Pedido() {

    }

}
