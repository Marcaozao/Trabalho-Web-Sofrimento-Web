package daos;

import jakarta.persistence.EntityManager;
import models.Cliente;
import utils.EntityManagerUtil;

public class ClienteDao {

    EntityManager em = EntityManagerUtil.getEm();

    public void salvar(Cliente cliente) {

        try
        {

            em.getTransaction().begin();
            em.persist(cliente);
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

    public Cliente buscar_por_nome(String nome)
    {

        Cliente cliente = null;

        try
        {

            cliente = (Cliente) em.createQuery("from Cliente where nome = :nome", Cliente.class)
                    .setParameter("nome", nome).getSingleResult();

            return cliente;

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
