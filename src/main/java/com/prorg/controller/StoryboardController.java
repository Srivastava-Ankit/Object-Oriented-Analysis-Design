package com.prorg.controller;

import com.prorg.helper.Constants;
import com.prorg.helper.result.Response;
import com.prorg.model.Storyboard;
import com.prorg.model.User;
import com.prorg.service.StoryboardService;
import com.prorg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StoryboardController {

    private StoryboardService storyboardService;
    private UserService userService;

    @Autowired
    public StoryboardController(StoryboardService storyboardService, UserService userService) {
        this.storyboardService = storyboardService;
        this.userService = userService;
    }

    @RequestMapping(value = Constants.Route.STORYBOARDS, method = RequestMethod.POST)
    public String addStoryboard(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        int createdById = (int) session.getAttribute(Constants.SessionKeys.LOGGED_IN_USER);
        Response<User> response = userService.getUserById(createdById);
        // TODO: Add of check for response failure.
        User createdByUser = response.data();
        Response<Integer> save = storyboardService.createStoryboard(title, description, createdByUser);
        model.addAttribute(Constants.ModelAttributes.MESSAGE, save.isSuccessful() ? "Success" : "Failed");
        return Constants.Route.REDIRECT + Constants.Route.STORYBOARDS;
    }

    @RequestMapping(value = Constants.Route.STORYBOARDS, method = RequestMethod.GET)
    public String getAllStoryboards(HttpSession session, Model model) throws Exception {
        int userId = (int) session.getAttribute(Constants.SessionKeys.LOGGED_IN_USER);
        // TODO: Add isSuccessful() check
        User loggedInUser = userService.getUserById(userId).data();
        List<Storyboard> accessibleStoryboards = loggedInUser.getAccessibleStoryboards();
        // TODO: Add isSuccessful() check
        List<Storyboard> storyboardCreatedByLoggedInUser = storyboardService.getStoryboardGivenItsCreator(loggedInUser).data();
        List<Storyboard> allMyStoryboards = new ArrayList<>(accessibleStoryboards);
        allMyStoryboards.addAll(storyboardCreatedByLoggedInUser);
        model.addAttribute(Constants.ModelAttributes.STORYBOARDS, allMyStoryboards);
        return Constants.RedirectPage.STORYBOARDS;
    }

    @RequestMapping(value = Constants.Route.SPECIFIC_STORYBOARD, method = RequestMethod.GET)
    public String getAStoryboard(@PathVariable("id") int storyboardId, Model model) throws Exception {
        Response<Storyboard> getStoryBoardId = storyboardService.getStoryboardById(storyboardId);
        if (getStoryBoardId.isSuccessful()) {
            model.addAttribute(Constants.ModelAttributes.STORYBOARD, getStoryBoardId.data());
        }
        return Constants.RedirectPage.STORYBOARD;
    }

    @RequestMapping(value = Constants.Route.UPDATE_USERS_OF_STORYBOARD, method = RequestMethod.POST)
    public String addUserToStoryboard(HttpServletRequest request, @PathVariable("id") int storyboardId, Model model) throws Exception {
        String emailOfUserToAdd = request.getParameter("email");
        Response<User> getUser = userService.getUserByEmail(emailOfUserToAdd);
        Response<Storyboard> getStoryboard = storyboardService.getStoryboardById(storyboardId);
        if (getUser.isSuccessful() && getStoryboard.isSuccessful()) {
            Response updateAssignedUsersOfCard = storyboardService.addUserToStoryboard(getStoryboard.data(), getUser.data());
            model.addAttribute(Constants.ModelAttributes.MESSAGE, updateAssignedUsersOfCard.isSuccessful() ? "Success" : "Failed");
        }
        else {
            model.addAttribute(Constants.ModelAttributes.MESSAGE, "Failed");
        }
        return Constants.Route.REDIRECT + Constants.Route.SPECIFIC_STORYBOARD(storyboardId);
    }
}
