package com.prorg.service;

import com.prorg.helper.result.Response;
import com.prorg.model.User;

import java.util.List;

public interface UserService {
    Response<Integer> createUser(String firstName, String lastName, String email, String password, String confirmPassword) throws Exception;
    Response<Integer> loginUser(String email, String password) throws Exception;
    Response<User> getUserById(int userId) throws Exception;
    Response<User> getUserByEmail(String emailId) throws Exception;
}
