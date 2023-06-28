package PP_3_1_2_Boot_Security.dao;

import PP_3_1_2_Boot_Security.model.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManagerFactory entityManagerFactory) {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUser(int id) {
        TypedQuery<User> typedQuery = entityManager.createQuery("select user from User user where user.id = :id", User.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    @Transactional
    public void update(int id, User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void delete(int id) {
        entityManager.remove(getUser(id));
    }

    @Override
    public Optional<User> findByName(String name) {
        TypedQuery<User> typedQuery = entityManager
                .createQuery("select user from User user where user.name = :name", User.class);
        typedQuery.setParameter("name", name);
        return typedQuery.getResultList().stream().findFirst();
    }
}
