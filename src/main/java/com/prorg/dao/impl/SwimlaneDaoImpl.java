package com.prorg.dao.impl;

import com.prorg.dao.SwimlaneDao;
import com.prorg.helper.result.Response;
import com.prorg.model.Swimlane;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SwimlaneDaoImpl extends BaseDaoImpl implements SwimlaneDao {

    @Autowired
    public SwimlaneDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Response<Integer> save(Swimlane swimlane) {
        return super.save(swimlane);
    }

    @Override
    public Response<Swimlane> findById(int swimlaneId) {
        return Response.Success(getCurrentSession().get(Swimlane.class, swimlaneId));
    }

    @Override
    public Response deleteById(int swimlaneId) {
        return super.delete(getCurrentSession().get(Swimlane.class, swimlaneId));
    }

    @Override
    public Response update(Swimlane swimlane) {
        return super.update(swimlane);
    }
}
