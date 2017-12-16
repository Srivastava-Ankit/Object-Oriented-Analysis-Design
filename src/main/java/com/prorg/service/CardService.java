package com.prorg.service;

import com.prorg.helper.result.Response;
import com.prorg.model.Card;
import com.prorg.model.Swimlane;
import com.prorg.model.User;
import com.prorg.service.proxy.CardUserPermissionService;

public interface CardService extends CardUserPermissionService {
    Response<Integer> createCard(String title, String description, Swimlane itsSwimlane, User creator);
    Response<Card> getCardById(int cardId) throws Exception;
}
