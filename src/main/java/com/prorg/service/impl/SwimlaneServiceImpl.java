package com.prorg.service.impl;

import com.prorg.dao.SwimlaneDao;
import com.prorg.helper.result.Response;
import com.prorg.helper.result.ValidationResponse;
import com.prorg.helper.validator.ModelValidator;
import com.prorg.model.Storyboard;
import com.prorg.model.Swimlane;
import com.prorg.service.SwimlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SwimlaneServiceImpl implements SwimlaneService {
    private final SwimlaneDao swimlaneDao;
    private final ModelValidator validator;

    @Autowired
    public SwimlaneServiceImpl(SwimlaneDao swimlaneDao, ModelValidator validator) {
        this.swimlaneDao = swimlaneDao;
        this.validator = validator;
    }

    @Override
    public Response<Integer> createSwimlane(String name, Storyboard itsStoryBoard) {
        Swimlane swimlaneToAdd = new Swimlane();
        swimlaneToAdd.setName(name)
                     .setStoryboard(itsStoryBoard);
        ValidationResponse validationResponse = validator.validate(swimlaneToAdd);
        if (!validationResponse.isValid()) {
            return Response.Failure(validationResponse.errors());
        }
        return swimlaneDao.save(swimlaneToAdd);
    }

    @Override
    public Response<Swimlane> getSwimlaneById(int swimlaneId) throws Exception {
        Response queryResponse = swimlaneDao.findById(swimlaneId);
        return Response.Success((Swimlane) queryResponse.data());
    }
}
