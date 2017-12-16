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
@RequestMapping(Constants.Route.REGISTER)
public class RegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String showRegistrationForm() {
        return Constants.RedirectPage.REGISTRATION_FORM;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        Response save = userService.createUser(firstName, lastName, email, password, confirmPassword);
        String redirectMessage = "Failed";
        if (save.isSuccessful()) {
            session.setAttribute(Constants.SessionKeys.LOGGED_IN_USER, save.data());
            redirectMessage = "Success";
        }

        model. addAttribute(Constants.ModelAttributes.MESSAGE, redirectMessage);
        return Constants.Route.REDIRECT + Constants.Route.STORYBOARDS;
    }
}
