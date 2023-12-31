package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> getUserByCar(String s, int i) {
        String hql = "from User where car.model =: model and car.series =: seria";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("model", s);
        query.setParameter("seria", i);

        return query.getResultList();
    }

    @Override
    public List<User> getListUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

}
