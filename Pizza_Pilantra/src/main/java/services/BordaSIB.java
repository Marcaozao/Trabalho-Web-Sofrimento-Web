package services;

import daos.BordaDao;
import jakarta.jws.WebService;
import models.Borda;

@WebService(endpointInterface = "services.BordaSEI")
public class BordaSIB implements BordaSEI {

    @Override
    public String salvar_sabor(String sabor)
    {

        BordaDao dao = new BordaDao();

        if(dao.buscar_por_sabor(sabor) == null)
        {

            Borda borda = new Borda(sabor);

            BordaDao salvarDao = new BordaDao();

            salvarDao.salvar(borda);

            return "Borda salva com sucesso!";

        }
        else
        {

            return "Borda já existe!";

        }

    }

}
