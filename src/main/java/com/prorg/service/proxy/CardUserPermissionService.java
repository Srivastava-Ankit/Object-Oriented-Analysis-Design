package com.prorg.service.proxy;

import com.prorg.helper.result.Response;
import com.prorg.model.Card;
import com.prorg.model.User;

public interface CardUserPermissionService {
    Response addUserToCard(Card card, User user) throws Exception;
    Response deleteCardIfAccessibleByUser(Card card, User user);
}
