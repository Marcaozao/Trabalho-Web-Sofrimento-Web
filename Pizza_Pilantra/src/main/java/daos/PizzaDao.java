package daos;

import jakarta.persistence.EntityManager;
import models.Cliente;
import models.Pizza;
import utils.EntityManagerUtil;

public class PizzaDao
{

    EntityManager em = EntityManagerUtil.getEm();

    public void salvar(Pizza pizza) {

        try
        {

            em.getTransaction().begin();
            em.persist(pizza);
            em.getTransaction().commit();

        }
        catch(Exception e)
        {

            em.getTransaction().rollback();
            e.printStackTrace();

        }
        finally
        {

            em.close();

        }

    }

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
