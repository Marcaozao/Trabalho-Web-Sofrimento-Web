package services;

import daos.*;
import jakarta.jws.WebService;
import models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebService(endpointInterface = "services.PedidoSEI")
public class PedidoSIB implements PedidoSEI
{

    @Override
    public String criarPedido(String cliente_nome, String observacoes, List<String> tamanho, List<Integer> quantidade, List<String> sabor_borda, List<String> sabor_pizza) {

        ClienteDao cliente_dao = new ClienteDao();

        List<Pizza> pizza = new ArrayList<>();
        List<Borda> borda = new ArrayList<>();
        Cliente cliente = null;

        if (!(tamanho.size() == quantidade.size() && quantidade.size() == sabor_borda.size() && sabor_borda.size() == sabor_pizza.size()))
        {

            return "Precisam ser a mesma quantidade de informações nas listas";

        }

        if (cliente_dao.buscar_por_nome(cliente_nome) == null) {

            return "Cliente não existe!";

        }
        else
        {

            cliente_dao = new ClienteDao();

            cliente = cliente_dao.buscar_por_nome(cliente_nome);

        }

        for(int i = 0; i < sabor_pizza.size(); i++)
        {

            PizzaDao pizza_dao = new PizzaDao();

            if(pizza_dao.buscar_por_sabor(sabor_pizza.get(i)) == null)
            {

                return "Não foi possivel achar o sabor da pizza!";

            }
            else
            {

                pizza_dao = new PizzaDao();

                pizza.add(pizza_dao.buscar_por_sabor(sabor_pizza.get(i)));

            }

        }

        for(int i = 0; i < sabor_borda.size(); i++)
        {

            BordaDao borda_dao = new BordaDao();

            if(borda_dao.buscar_por_sabor(sabor_borda.get(i)) == null)
            {

                return "Não foi possivel achar o sabor da borda!";

            }
            else
            {

                borda_dao = new BordaDao();

                borda.add(borda_dao.buscar_por_sabor(sabor_borda.get(i)));

                System.out.println(borda.get(i).getSabor());

            }
            
        }

        ItensPedido itens[] = new ItensPedido[tamanho.size()];

        double valor_total = 0;

        for(int i = 0; i < tamanho.size(); i++)
        {
            
            double valor_unitario = 0;
            
            switch(pizza.get(i).getId())
            {
                
                case 1:
                    
                    valor_unitario += 45.00;

                break;
                case 2:

                    valor_unitario += 40.00;

                break;
                case 3:

                    valor_unitario += 50.00;

                break;
                case 4:

                    valor_unitario += 70.00;

                break;

            }

            switch(borda.get(i).getId())
            {

                case 1:

                    valor_unitario += 15.00;

                    break;
                case 2:

                    valor_unitario += 5.00;

                    break;
                case 3:

                    valor_unitario += 10.00;

                    break;
                case 4:

                    valor_unitario += 25.00;

                    break;

            }

            switch(tamanho.get(i))
            {

                case "Pequena":

                    valor_unitario -= 20.00;

                break;
                case "Média":

                    valor_unitario = valor_unitario;

                break;
                case "Grande":

                    valor_unitario += 20.00;

                break;
                case "Família":

                    valor_unitario += 50.00;

                break;
                default:

                    return "A pizza precisa ter um tamanho cadastrado";

            }

            try
            {

                valor_total += valor_unitario * quantidade.get(i);

            }
            catch(Exception e) {

                e.printStackTrace();
                return "Deu um problema no cauculo do valor total";

            }

            ItensPedidoDao salvarDao = new ItensPedidoDao();

            itens[i] = new ItensPedido(tamanho.get(i), quantidade.get(i), pizza.get(i), borda.get(i), valor_unitario);

            salvarDao.salvar(itens[i]);

        }

        Pedido pedido = new Pedido(Arrays.asList(itens), cliente, valor_total, "");

        pedido.setStatus("Recebido");

        PedidoDao dao = new PedidoDao();

        dao.salvar(pedido);

        return "Pedido efetuado com sucesso! Vai custar " + valor_total;

    }

    @Override
    public String consultar_pedido(Integer id_pedido) {

        PedidoDao dao = new PedidoDao();

        if(dao.buscar_por_id(id_pedido) != null)
        {

            dao = new PedidoDao();

            Pedido pedido = dao.buscar_por_id(id_pedido);

            return "";

        }

        return null;

    }

}
