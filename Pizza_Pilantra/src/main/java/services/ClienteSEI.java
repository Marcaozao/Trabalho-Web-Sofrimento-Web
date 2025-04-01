package services;

import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.Date;

@WebService
public interface ClienteSEI {

    String criarCliente(@WebParam(name = "nome") String nome, @WebParam(name = "telefone") String telefone, @WebParam(name = "cpf") String cpf, @WebParam(name = "endereco") String endereco, @WebParam(name = "data_nascimento") Date data_nascimento);



}
