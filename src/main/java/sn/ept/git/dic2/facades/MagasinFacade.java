package sn.ept.git.dic2.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.entities.Magasin;

@Stateless
public class MagasinFacade extends AbstractFacade<Magasin> {
    @PersistenceContext(name = "velo")
    private EntityManager entityManager;

    public MagasinFacade() {
        super(Magasin.class);
    }


    @Override
    protected EntityManager getEntityManager(){

        return entityManager;
    }

}
