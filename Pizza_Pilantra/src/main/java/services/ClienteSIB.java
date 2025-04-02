package services;

import daos.ClienteDao;
import jakarta.jws.WebService;
import models.Cliente;

import java.util.Date;

@WebService(endpointInterface = "services.ClienteSEI")
public class ClienteSIB implements ClienteSEI {
    @Override
    public String criarCliente(String nome, String telefone, String cpf, String endereco, Date data_nascimento) {

        ClienteDao dao = new ClienteDao();

        if(dao.buscar_por_nome(nome) == null)
        {

            salvarCliente(nome, telefone, cpf, endereco, data_nascimento);

            return "Cliente criado com sucesso!";

        }
        else
        {

            return "Cliente já existe no sistema";

        }

    }

    @Override
    public String salvarCliente(String nome, String telefone, String cpf, String endereco, Date data_nascimento) {

        ClienteDao dao = new ClienteDao();

        Cliente cliente = new Cliente(nome, telefone, cpf, endereco, data_nascimento);

        dao.salvar(cliente);

        return cliente.getNome() + " salvo com sucesso!";

    }
}
