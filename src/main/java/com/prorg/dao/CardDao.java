package com.prorg.dao;

import com.prorg.helper.result.Response;
import com.prorg.model.Card;

public interface CardDao {
    Response<Integer> save(Card card);
    Response deleteByCardId(int cardId);
    Response update(Card card);
    Response<Card> findById(int cardId);
}
