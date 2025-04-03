package services;

import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface BordaSEI {

    String salvar_sabor(@WebParam(name = "sabor") String sabor);

}
