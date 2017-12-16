package com.prorg.dao.impl;

import com.prorg.dao.StoryboardDao;
import com.prorg.helper.result.Response;
import com.prorg.model.Storyboard;
import com.prorg.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StoryboardDaoImpl extends BaseDaoImpl implements StoryboardDao {

    @Autowired
    public StoryboardDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Response<Integer> save(Storyboard storyboard) {
        return super.save(storyboard);
    }

    @Override
    public Response<Storyboard> findById(int storyboardId) {
        return Response.Success(getCurrentSession().get(Storyboard.class, storyboardId));
    }

    @Override
    public Response deleteById(int storyboardId) {
        return super.delete(getCurrentSession().get(Storyboard.class, storyboardId));
    }

    @Override
    public Response update(Storyboard storyboard) {
        return super.update(storyboard);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Response<List<Storyboard>> findByCreator(User creator) {
        try {
            List storyboards = getCurrentSession().createQuery("from Storyboard as st where st.createdBy = :user")
                    .setParameter("user", creator)
                    .getResultList();
            return Response.Success(storyboards);
        } catch (Exception exception) {
            return Response.Failure(exception.getMessage());
        }
    }
}
