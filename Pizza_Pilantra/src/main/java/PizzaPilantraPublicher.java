import daos.PedidoDao;
import jakarta.xml.ws.Endpoint;
import models.Pedido;
import services.*;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PizzaPilantraPublicher {

    public static void main(String[] args) {

        Endpoint.publish("http://localhost:8080/cliente", new ClienteSIB());

        Endpoint.publish("http://localhost:8080/pedido", new PedidoSIB());

        Endpoint.publish("http://localhost:8080/pizza", new PizzaSIB());

        Endpoint.publish("http://localhost:8080/borda", new BordaSIB());

        System.out.println("Serviço publicado com sucesso!");

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // Agenda a tarefa:
        executor.scheduleAtFixedRate(
                () -> {

                    PedidoDao dao = new PedidoDao();

                    int quant = dao.atualizar_status();

                    System.out.println( quant + " status atualizados com sucesso!");
                },
                1, // Delay inicial (0 = começa imediatamente)
                1, // Intervalo de repetição
                TimeUnit.MINUTES // Unidade de tempo (minutos)
        );

//Borda, Pizza, Pedido, Cliente, Itens_Pedido
    }

}
