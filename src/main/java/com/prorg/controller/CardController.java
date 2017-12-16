package com.prorg.controller;

import com.prorg.helper.Constants;
import com.prorg.helper.result.Response;
import com.prorg.model.Card;
import com.prorg.model.Swimlane;
import com.prorg.model.User;
import com.prorg.service.CardService;
import com.prorg.service.SwimlaneService;
import com.prorg.service.UserService;
import com.prorg.service.proxy.CardUserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CardController {

    private CardService cardService;
    private CardUserPermissionService cardUserPermissionService;
    private SwimlaneService swimlaneService;
    private UserService userService;

    @Autowired
    public CardController(CardService cardService, CardUserPermissionService cardUserPermissionService, SwimlaneService swimlaneService, UserService userService) {
        this.cardService = cardService;
        this.cardUserPermissionService = cardUserPermissionService;
        this.swimlaneService = swimlaneService;
        this.userService = userService;
    }

    @RequestMapping(value = Constants.Route.CARDS, method = RequestMethod.POST)
    public String addCardToSwimlane(HttpServletRequest request, HttpSession session, @PathVariable("stId") int storyboardId, @PathVariable("id") int swimlaneId, Model model) throws Exception {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int cardCreatorId = (int) session.getAttribute(Constants.SessionKeys.LOGGED_IN_USER);
        Response<User> userById = userService.getUserById(cardCreatorId);
        // TODO: Check if user is null
        User creator = userById.data();
        Response<Swimlane> response = swimlaneService.getSwimlaneById(swimlaneId);
        Swimlane swimlane = response.data();
        Response<Integer> cardCreation = cardService.createCard(name, description, swimlane, creator);

        model.addAttribute(Constants.ModelAttributes.MESSAGE, cardCreation.isSuccessful() ? "Success" : "Failed");
        return Constants.Route.REDIRECT + Constants.Route.SPECIFIC_STORYBOARD(storyboardId);
    }

    @RequestMapping(value = Constants.Route.ADD_USER_TO_CARD, method = RequestMethod.POST)
    public String addUserToCard(HttpServletRequest request, @PathVariable("stId") int storyboardId, @PathVariable("id") int cardId, Model model) throws Exception {
        String emailOfUserToAdd = request.getParameter("email");
        Response<User> getUser = userService.getUserByEmail(emailOfUserToAdd);
        Response<Card> getCardById = cardService.getCardById(cardId);
        if (getUser.isSuccessful() && getCardById.isSuccessful()) {
            Card card = getCardById.data();
            User user = getUser.data();
            Response addUserToCard = cardUserPermissionService.addUserToCard(card, user);
            if (addUserToCard.isSuccessful()) {
                model.addAttribute(Constants.ModelAttributes.MESSAGE, "success");
            } else
                model.addAttribute(Constants.ModelAttributes.MESSAGE, String.join(", ", addUserToCard.errors()));
        }
        return Constants.Route.REDIRECT + Constants.Route.SPECIFIC_STORYBOARD(storyboardId);
    }

    @RequestMapping(value = Constants.Route.DELETE_CARD, method = RequestMethod.POST)
    public String deleteCard(HttpServletRequest request, HttpSession session, @PathVariable("stId") int storyboardId, @PathVariable("id") int cardId, Model model) throws Exception {
        if (Constants.HttpMethod.DELETE.equals(request.getParameter(Constants.RequestAttributes.HttpMethod))) {
            int userId = (int) session.getAttribute(Constants.SessionKeys.LOGGED_IN_USER);
            Response<User> getUser = userService.getUserById(userId);
            Response<Card> getCardById = cardService.getCardById(cardId);
            if (getUser.isSuccessful() && getCardById.isSuccessful()) {
                Card card = getCardById.data();
                User user = getUser.data();
                Response deleteCardIfAccessibleByUser = cardUserPermissionService.deleteCardIfAccessibleByUser(card, user);
                model.addAttribute(Constants.ModelAttributes.MESSAGE, deleteCardIfAccessibleByUser.isSuccessful() ? "success" : "fail");
            }
        }
        return Constants.Route.REDIRECT + Constants.Route.SPECIFIC_STORYBOARD(storyboardId);
    }
}

