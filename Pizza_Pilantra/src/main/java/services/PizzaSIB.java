package services;

import daos.PizzaDao;
import jakarta.jws.WebService;
import models.Pizza;

@WebService(endpointInterface = "services.PizzaSEI")
public class PizzaSIB implements PizzaSEI {

    @Override
    public String salvar_sabor(String sabor) {

        PizzaDao dao = new PizzaDao();

        if(dao.buscar_por_sabor(sabor) == null)
        {

            Pizza pizza = new Pizza(sabor);

            PizzaDao salvarDao = new PizzaDao();

            salvarDao.salvar(pizza);

            return "Sabor salvo com sucesso!";

        }
        else
        {

            return "Esse sabor já existe!";

        }

    }

}
