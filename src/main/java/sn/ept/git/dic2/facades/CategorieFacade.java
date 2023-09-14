package sn.ept.git.dic2.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import sn.ept.git.dic2.entities.Categorie;

import java.util.List;

@Stateless
public class CategorieFacade extends AbstractFacade<Categorie> {
    @PersistenceContext(name = "velo")
    private EntityManager entityManager;

    public CategorieFacade() {
        super(Categorie.class);
    }


    public List<Categorie> getAllCategories() {
        TypedQuery<Categorie> query = entityManager.createQuery("SELECT p FROM Categorie p", Categorie.class);
        return query.getResultList();
    }


    @Override
    protected EntityManager getEntityManager(){

        return entityManager;
    }
}
