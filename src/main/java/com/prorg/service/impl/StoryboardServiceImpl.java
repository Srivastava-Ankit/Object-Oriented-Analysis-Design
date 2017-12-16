package com.prorg.service.impl;

import com.prorg.dao.UserDao;
import com.prorg.helper.result.Response;
import com.prorg.helper.result.ValidationResponse;
import com.prorg.helper.validator.ModelValidator;
import com.prorg.model.Storyboard;
import com.prorg.dao.StoryboardDao;
import com.prorg.model.User;
import com.prorg.service.StoryboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StoryboardServiceImpl implements StoryboardService {

    private final StoryboardDao storyboardDao;
    private UserDao userDao;
    private final ModelValidator validator;

    @Autowired
    public StoryboardServiceImpl(StoryboardDao storyboardDao, UserDao userDao, ModelValidator validator) {
        this.storyboardDao = storyboardDao;
        this.userDao = userDao;
        this.validator = validator;
    }

    @Override
    public Response<Integer> createStoryboard(String title, String description, User createdBy){
        Storyboard storyBoardToAdd = new Storyboard();
        storyBoardToAdd.setTitle(title)
                       .setDescription(description)
                       .setCreatedBy(createdBy);
        ValidationResponse validationResponse = validator.validate(storyBoardToAdd);
        if (!validationResponse.isValid()) {
            return Response.Failure(validationResponse.errors());
        }
        return storyboardDao.save(storyBoardToAdd);
    }

    @Override
    public Response<Storyboard> getStoryboardById(int storyboardId) throws Exception {
        Response<Storyboard> queryResponse = storyboardDao.findById(storyboardId);
        return Response.Success(queryResponse.data());
    }

    @Override
    public Response addUserToStoryboard(Storyboard storyboard, User userToAdd) {
        List<Storyboard> accessibleStoryboards = userToAdd.getAccessibleStoryboards();
        accessibleStoryboards.add(storyboard);
        userToAdd.setAccessibleStoryboards(accessibleStoryboards);
        Response updateUser = userDao.update(userToAdd);
        if (!updateUser.isSuccessful())
            return updateUser;

        List<User> usersWhoHaveAccess = storyboard.getUsersWhoHaveAccess();
        usersWhoHaveAccess.add(userToAdd);
        storyboard.setUsersWhoHaveAccess(usersWhoHaveAccess);
        return storyboardDao.update(storyboard);
    }

    @Override
    public Response<List<Storyboard>> getStoryboardGivenItsCreator(User creator) throws Exception {
        Response<List<Storyboard>> response = storyboardDao.findByCreator(creator);
        if (!response.isSuccessful())
            return response;
        return Response.Success(response.data());
    }
}
