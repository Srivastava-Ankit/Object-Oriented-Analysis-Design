package com.prorg.service.proxy.impl;

import com.prorg.helper.result.Response;
import com.prorg.model.Card;
import com.prorg.model.Storyboard;
import com.prorg.model.User;
import com.prorg.service.proxy.CardUserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardUserPermissionServiceProxyImpl implements CardUserPermissionService {
    private CardUserPermissionService cardService;

    @Autowired
    public CardUserPermissionServiceProxyImpl(@Qualifier("cardServiceImpl") CardUserPermissionService cardService) {
        this.cardService = cardService;
    }

    @Override
    public Response addUserToCard(Card card, User userToAdd) throws Exception {
        List<User> usersWhoHaveAccess = getUsersHavingAccessToStoryboardTheCardIsIn(card);
        if (usersWhoHaveAccess.contains(userToAdd)) {
            if (!card.getAssignedUsers().contains(userToAdd))
                return cardService.addUserToCard(card, userToAdd);
            return Response.SuccessEmptyPayload();
        }
        return Response.Failure("This user does not belong to the storyboard the card is in!");
    }

    @Override
    public Response deleteCardIfAccessibleByUser(Card card, User user) {
        List<User> usersWhoHaveAccess = getUsersHavingAccessToStoryboardTheCardIsIn(card);
        if (usersWhoHaveAccess.contains(user))
            return  cardService.deleteCardIfAccessibleByUser(card, user);
        return Response.Failure("You do not have permission to delete this card");
    }

    private List<User> getUsersHavingAccessToStoryboardTheCardIsIn(Card card) {
        Storyboard storyboardTheCardIsIn = card.getSwimlane().getStoryboard();
        User creator = storyboardTheCardIsIn.getCreatedBy();
        List<User> usersWhoHaveAccess = new ArrayList<>(storyboardTheCardIsIn.getUsersWhoHaveAccess());
        usersWhoHaveAccess.add(creator);
        return usersWhoHaveAccess;
    }
}
