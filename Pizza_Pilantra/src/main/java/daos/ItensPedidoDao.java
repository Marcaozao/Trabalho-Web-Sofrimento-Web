package daos;

import jakarta.persistence.EntityManager;
import models.Cliente;
import models.ItensPedido;
import utils.EntityManagerUtil;

public class ItensPedidoDao {

    EntityManager em = EntityManagerUtil.getEm();

    public void salvar(ItensPedido itensPedido) {

        try
        {

            em.getTransaction().begin();
            em.persist(itensPedido);
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

}
