import jakarta.xml.ws.Endpoint;
import services.ClienteSIB;
import services.PedidoSIB;
import services.PizzaSEI;

public class PizzaPilantraPublicher {

    public static void main(String[] args) {

        Endpoint.publish("http://localhost:8080/cliente", new ClienteSIB());

        Endpoint.publish("http://localhost:8080/pedido", new PedidoSIB());

        System.out.println("Serviço publicado com sucesso!");
//Borda, Pizza, Pedido, Cliente, Itens_Pedido
    }

}
