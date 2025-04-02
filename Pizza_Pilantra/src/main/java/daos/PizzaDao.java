package daos;

import jakarta.persistence.EntityManager;
import models.Cliente;
import models.Pizza;
import utils.EntityManagerUtil;

public class PizzaDao
{

    EntityManager em = EntityManagerUtil.getEm();

    public Pizza buscar_por_sabor(String sabor)
    {

        try
        {

            Pizza pizza = (Pizza) em.createQuery("from Pizza where sabor = :sabor", Pizza.class)
                    .setParameter("sabor", sabor).getSingleResult();

            return pizza;

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
