package com.prorg.controller;


import com.prorg.helper.Constants;
import com.prorg.helper.result.Response;
import com.prorg.model.Storyboard;
import com.prorg.service.StoryboardService;
import com.prorg.service.SwimlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SwimlaneController {

    private SwimlaneService swimlaneService;
    private StoryboardService storyboardService;

    @Autowired
    public SwimlaneController(SwimlaneService swimlaneService, StoryboardService storyboardService){
        this.swimlaneService = swimlaneService;
        this.storyboardService = storyboardService;
    }

    @RequestMapping(value = Constants.Route.SWIMLANES, method = RequestMethod.POST)
    public String addSwimlane(HttpServletRequest request, @PathVariable("id") int storyboardId, Model model) throws Exception {
        String name = request.getParameter("name");
        Response<Storyboard> response = storyboardService.getStoryboardById(storyboardId);
        Storyboard storyboard = response.data();
        Response<Integer> swimlaneCreation = swimlaneService.createSwimlane(name, storyboard);
        model.addAttribute(Constants.ModelAttributes.MESSAGE, swimlaneCreation.isSuccessful() ? "Success" : "Failed");
        return Constants.Route.REDIRECT + Constants.Route.SPECIFIC_STORYBOARD(storyboardId);
    }
}
