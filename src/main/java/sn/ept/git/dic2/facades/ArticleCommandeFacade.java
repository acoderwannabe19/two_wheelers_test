package sn.ept.git.dic2.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import sn.ept.git.dic2.entities.ArticleCommande;
import sn.ept.git.dic2.entities.Commande;
import sn.ept.git.dic2.entities.Produit;

import java.util.List;

@Stateless
public class ArticleCommandeFacade extends AbstractFacade<ArticleCommande> {
    @PersistenceContext(name = "velo")
    private EntityManager entityManager;

    public ArticleCommandeFacade() {
        super(ArticleCommande.class);
    }


    @Override
    protected EntityManager getEntityManager(){

        return entityManager;
    }

    public List<Produit> getAllProduits() {
        Query query = entityManager.createQuery("SELECT m FROM Produit m");
        return query.getResultList();
    }

    public List<Commande> getAllCommandes() {
        Query query = entityManager.createQuery("SELECT m FROM Commande m");
        return query.getResultList();
    }

    public Produit findProduitById(Long id) {
        Query query = entityManager.createQuery("SELECT c FROM Produit c WHERE c.id = :id");
        query.setParameter("id", id);
        return (Produit) query.getSingleResult();
    }
}
