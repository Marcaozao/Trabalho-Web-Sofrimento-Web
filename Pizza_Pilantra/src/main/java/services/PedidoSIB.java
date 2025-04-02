package services;

import daos.BordaDao;
import daos.ClienteDao;
import daos.PizzaDao;
import jakarta.jws.WebService;
import models.*;

import java.util.Arrays;
import java.util.List;

@WebService(endpointInterface = "services.PedidoSEI")
public class PedidoSIB implements PedidoSEI
{

    @Override
    public String criarPedido(String cliente_nome, String observacoes, List<String> tamanho, List<Integer> quantidade, List<String> sabor_borda, List<String> sabor_pizza) {

        ClienteDao cliente_dao = new ClienteDao();
        PizzaDao pizza_dao = new PizzaDao();
        BordaDao borda_dao = new BordaDao();

        Pizza pizza = null;
        Borda borda = null;
        Cliente cliente = null;

        if (!(tamanho.size() == quantidade.size() && quantidade.size() == sabor_borda.size() && sabor_borda.size() == sabor_pizza.size()))
        {

            return "Precisam ser a mesma quantidade de informações nas listas";

        }

        if(cliente_dao.buscar_por_nome(cliente_nome) == null)
        {

            return "Cliente não existe!";

        }
        else
        {

            cliente = cliente_dao.buscar_por_nome(cliente_nome);

        }

        for(int i = 0; i < sabor_pizza.size(); i++)
        {

            if(pizza_dao.buscar_por_sabor(sabor_pizza.get(i)) == null)
            {

                return "Não foi possivel achar o sabor da pizza!";

            }
            else
            {

                pizza = pizza_dao.buscar_por_sabor(sabor_pizza.get(i));

            }

        }

        for(int i = 0; i < sabor_borda.size(); i++)
        {

            if(borda_dao.buscar_por_sabor(sabor_borda.get(i)) == null)
            {

                return "Não foi possivel achar o sabor da borda!";

            }
            else
            {

                borda = borda_dao.buscar_por_sabor(sabor_borda.get(i));

            }
            
        }

        ItensPedido itens[] = new ItensPedido[tamanho.size()];

        for(int i = 0; i < tamanho.size(); i++)
        {

            itens[i] = new ItensPedido(tamanho.get(i), quantidade.get(i), pizza, borda);

        }

        Pedido pedido = new Pedido(Arrays.asList(itens), cliente, 0.0, "");

        return "Pedido efetuado com sucesso!";

    }

}
