package com.prorg.controller.auth;

import com.prorg.helper.Constants;
import com.prorg.helper.result.Response;
import com.prorg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = Constants.Route.LOGIN, method = RequestMethod.GET)
    public String showLoginForm() {
        return Constants.RedirectPage.LOGIN_FORM;
    }

    @RequestMapping(value = Constants.Route.LOGIN, method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Response<Integer> response = userService.loginUser(email, password);
        if (response.isSuccessful()) {
            session.setAttribute(Constants.SessionKeys.LOGGED_IN_USER, response.data());
            Object referer = request.getAttribute(Constants.RequestAttributes.REFERER);
            if (referer != null) {
                return Constants.Route.REDIRECT + referer;
            }
            return Constants.Route.REDIRECT + Constants.Route.STORYBOARDS;
        }
        model.addAttribute(Constants.ModelAttributes.MESSAGE, "Login Failed");
        return Constants.RedirectPage.LOGIN_FORM;
    }

    @RequestMapping(value = Constants.Route.LOGOUT)
    public String logout(HttpSession session) {
        session.removeAttribute(Constants.SessionKeys.LOGGED_IN_USER);
        return Constants.Route.REDIRECT + Constants.Route.STORYBOARDS;
    }
}
