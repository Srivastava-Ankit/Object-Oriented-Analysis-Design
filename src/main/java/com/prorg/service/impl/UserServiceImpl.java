package com.prorg.service.impl;

import com.prorg.dao.UserDao;
import com.prorg.helper.Password;
import com.prorg.helper.result.Response;
import com.prorg.helper.result.ValidationResponse;
import com.prorg.helper.validator.ModelValidator;
import com.prorg.model.User;
import com.prorg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final Password passwordHash;
    private final ModelValidator modelValidator;

    @Autowired
    public UserServiceImpl(UserDao userDao, Password passwordHash, ModelValidator modelValidator) {
        this.userDao = userDao;
        this.passwordHash = passwordHash;
        this.modelValidator = modelValidator;
    }

    @Override
    public Response<Integer> createUser(String firstName, String lastName,
                               String email, String password,
                               String confirmPassword) {
        String salt = passwordHash.getNextSalt();
        String password_hash = passwordHash.hash(password, salt);
        User user = new User();
        user.setFirstName(firstName)
            .setLastName(lastName)
            .setEmail(email)
            .setPassword(password)
            .setConfirmPassword(confirmPassword)
            .setSalt(salt)
            .setPasswordHash(password_hash);
        ValidationResponse userValidationResponse = modelValidator.validate(user);
        if (!userValidationResponse.isValid())
            return Response.Failure(userValidationResponse.errors());
        return userDao.save(user);
    }

    @Override
    public Response<Integer> loginUser(String email, String password) throws Exception {
        Response response = userDao.findByEmail(email);
        if(!response.isSuccessful() || response.data() == null)
            return Response.Failure("User not found.");
        User user = (User) response.data();
        ValidationResponse validationResponse = modelValidator.validate(user.setPassword(password).setConfirmPassword(password));
        if (!validationResponse.isValid())
            return Response.Failure(validationResponse.errors());
        return Response.Success(user.getId());
    }

    @Override
    public Response<User> getUserById(int userId) throws Exception {
        Response queryResponse = userDao.findById(userId);
        return Response.Success((User) queryResponse.data());
    }

    @Override
    public Response<User> getUserByEmail(String emailId) throws Exception {
        Response queryResponse = userDao.findByEmail(emailId);
        return Response.Success((User) queryResponse.data());
    }
}
