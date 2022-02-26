package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
//      sessionFactory.getCurrentSession().save(user.getCar());
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   public Optional<User> getUserWithCar(String model, int series){
      TypedQuery<User> query=sessionFactory.getCurrentSession()
              .createQuery("FROM User where car.model = :model and car.series = :series",User.class);
      return query.setParameter("model",model).setParameter("series", series)
              .getResultList().stream().findAny();
   }
}
