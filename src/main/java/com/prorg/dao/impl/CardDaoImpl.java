package com.prorg.dao.impl;

import com.prorg.dao.CardDao;
import com.prorg.helper.result.Response;
import com.prorg.model.Card;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CardDaoImpl extends BaseDaoImpl implements CardDao {

    public CardDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Response<Integer> save(Card card) {
        return super.save(card);
    }

    @Override
    public Response deleteByCardId(int cardId) {
        return super.delete(getCurrentSession().get(Card.class, cardId));
    }

    @Override
    public Response update(Card card) {
        return super.update(card);
    }

    @Override
    public Response<Card> findById(int cardId) {
        return Response.Success(getCurrentSession().get(Card.class, cardId));
    }
}
