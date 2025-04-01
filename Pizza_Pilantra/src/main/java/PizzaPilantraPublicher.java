import jakarta.xml.ws.Endpoint;

public class PizzaPilantraPublicher {

    public static void main(String[] args) {

        Endpoint.publish("http://localhost:8080/pizza", new UsuarioSIB());

        System.out.println("Serviço publicado com sucesso!");
//Borda, Pizza, Pedido, Cliente, Itens_Pedido
    }

}
