package com.prorg.dao.impl;

import com.prorg.dao.UserDao;
import com.prorg.helper.result.Response;
import com.prorg.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Response<Integer> save(User user) {
        return super.save(user);
    }

    @Override
    public Response<User> findByEmail(String email) {
        Query query = getCurrentSession().createQuery("from User where email = :email");
        query.setParameter("email", email);
        try {
            return Response.Success((User) query.getSingleResult());
        } catch (Exception exception) {
            return Response.Failure(exception.getMessage());
        }
    }

    @Override
    public Response<User> findById(int userId) {
        return Response.Success(getCurrentSession().get(User.class, userId));
    }

    @Override
    public Response update(User userToAdd) {
        return super.update(userToAdd);
    }
}
