package ru.itis.maletskov.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.itis.maletskov.models.Album;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Repository
public class AlbumRepositoryEntityManagerImpl implements AlbumRepository {
    private EntityManager em;

    @Autowired
    public AlbumRepositoryEntityManagerImpl(@Qualifier(value = "entityManagerFactory") EntityManagerFactory emf) {
        em = emf.createEntityManager();
    }

    @Override
    public List<Album> findAll() {
        return em.createQuery("select a from ru.itis.maletskov.models.Album a", Album.class).getResultList();
    }

    @Override
    public Optional<Album> findOne(Long id) {
        Album album = em.find(Album.class, id);
        return Optional.ofNullable(album);
        /*Session session = this.sessionFactory.openSession();
        User u = session.get(User.class, id);
        return Optional.ofNullable(u);*/
    }

    @Override
    public Album save(Album model) {
        em.getTransaction().begin();
        em.persist(model);
        em.getTransaction().commit();
        return model;
        /*Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(model);
        tx.commit();
        session.close();*/
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Album model) {

    }
}
