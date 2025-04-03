package services;

import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import models.Cliente;

import java.util.List;

@WebService
public interface PedidoSEI {

    String criarPedido(@WebParam(name = "cliente_nome") String cliente_nome, @WebParam(name = "observacoes") String observacoes, @WebParam(name = "tamanho") List<String> tamanho, @WebParam(name = "quantidade") List<Integer> quantidade, @WebParam(name = "sabor_borda") List<String> sabor_borda, @WebParam(name = "sabor_pizza") List<String> sabor_pizza);

    String consultar_pedido(@WebParam(name = "id_pedido") Integer id_pedido);

}
//para fazer o que muda os status eu vou pegar uma lista com todos os ids dos pedidos e com os status e vou fazer um switch para substuir os status da lista e colocar no banco de dados usando o id da sua mesma posição na lista