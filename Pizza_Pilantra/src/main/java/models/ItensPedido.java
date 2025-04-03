package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class ItensPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tamanho;
    private Integer quantidade;
    private double valor_unitario;

    @ManyToOne
    @JoinColumn
    private Pedido pedido;

    @ManyToOne
    private Borda borda;

    @ManyToOne
    private Pizza pizza;

    public ItensPedido(String tamanho, Integer quantidade, Pizza pizza, Borda borda, double valor_unitario)
    {

        this.tamanho = tamanho;
        this.quantidade = quantidade;
        this.pizza = pizza;
        this.borda = borda;
        this.valor_unitario = valor_unitario;

    }

    public ItensPedido() {

    }
}
