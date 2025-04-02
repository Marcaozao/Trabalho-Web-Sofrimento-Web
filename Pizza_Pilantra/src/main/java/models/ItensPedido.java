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
    private Pedido pedido_id;

    @ManyToOne
    private Borda borda_id;

    @ManyToOne
    private Pizza Pizza_id;

    public ItensPedido(String tamanho, Integer quantidade, Pizza Pizza_id, Borda borda_id)
    {

    }

    public ItensPedido() {

    }
}
