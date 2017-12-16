package com.prorg.controller;

import com.prorg.helper.Constants;
import com.prorg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(Constants.Route.ROOT)
public class IndexController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String showLandingPage() {
        return Constants.RedirectPage.INDEX;
    }
}
