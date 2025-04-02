package daos;

import jakarta.persistence.EntityManager;
import models.Borda;
import models.Pizza;
import utils.EntityManagerUtil;

public class BordaDao {

    EntityManager em = EntityManagerUtil.getEm();

    public Borda buscar_por_sabor(String sabor)
    {

        try
        {

            Borda borda = (Borda) em.createQuery("from Borda where sabor = :sabor", Borda.class)
                    .setParameter("sabor", sabor).getSingleResult();

            return borda;

        }
        catch (Exception e)
        {

            e.printStackTrace();
            return null;

        }
        finally {
            em.close();
        }

    }

}
