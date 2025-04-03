package daos;

import jakarta.persistence.EntityManager;
import models.Cliente;
import models.Pedido;
import utils.EntityManagerUtil;

import java.util.List;

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

    public Pedido buscar_por_id(Integer id)
    {

        try
        {

            Pedido pedido = (Pedido) em.createQuery("from Pedido where id = :id", Pedido.class)
                    .setParameter("id", id).getSingleResult();

            return pedido;

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

    public List<Pedido> buscar_todos()
    {

        try
        {

            return em.createQuery("from Pedido ", Pedido.class).getResultList();

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

    public int atualizar_status()
    {

        try {
            em.getTransaction().begin();

                        /*Recebido
                        Em Preparo
                        Pronto para Retirada
                        Saiu para Entrega
                        Entregue*/

            int updatedCount = em.createQuery("UPDATE Pedido p SET p.status = 'Entregue' WHERE status = 'Saiu para Entrega'")
                    .executeUpdate();

            updatedCount += em.createQuery("UPDATE Pedido p SET p.status = 'Saiu para Entrega' WHERE status = 'Pronto para Retirada'")
                    .executeUpdate();

            updatedCount += em.createQuery("UPDATE Pedido p SET p.status = 'Pronto para Retirada' WHERE status = 'Em Preparo'")
                    .executeUpdate();

            updatedCount += em.createQuery("UPDATE Pedido p SET p.status = 'Em Preparo' WHERE status = 'Recebido'")
                    .executeUpdate();

            updatedCount += em.createQuery("UPDATE Pedido p SET p.status = 'Recebido' WHERE status = null")
                    .executeUpdate();

            em.getTransaction().commit();
            System.out.println(updatedCount + " registros atualizados.");

            return updatedCount;

        } catch (Exception e) {

            em.getTransaction().rollback();
            e.printStackTrace();

            return 0;

        } finally {
            em.close();
        }

    }

}
