package com.prorg.service;

import com.prorg.helper.result.Response;
import com.prorg.model.Storyboard;
import com.prorg.model.Swimlane;

public interface SwimlaneService {
    Response<Integer> createSwimlane(String name, Storyboard itsStoryBoard);
    Response<Swimlane> getSwimlaneById(int swimlaneId) throws Exception;
}
