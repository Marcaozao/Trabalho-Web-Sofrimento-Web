package daos;

import jakarta.persistence.EntityManager;
import models.Cliente;
import models.Pedido;
import utils.EntityManagerUtil;

public class PedidoDao {

    EntityManager em = EntityManagerUtil.getEm();

    public void salvar(Pedido pedido) {

        try
        {

            em.getTransaction().begin();
            em.persist(pedido);
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
