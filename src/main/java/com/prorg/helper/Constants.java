package com.prorg.helper;

public class Constants {
    public static class SessionKeys {
        public static final String LOGGED_IN_USER = "loggedInUser";
    }

    public static class ModelAttributes {
        public static final String MESSAGE = "message";
        public static final String USERS = "users";
        public static final String STORYBOARDS = "storyboards";
        public static final String STORYBOARD = "storyboard";
    }

    public static class Route {
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/logout";
        public static final String REGISTER = "/register";
        public static final String ROOT = "/";
        public static final String CARDS = "/storyboards/{stId}/swimlanes/{id}/cards";
        public static final String ADD_USER_TO_CARD = "/storyboards/{stId}/cards/{id}/users";
        public static final String UPDATE_USERS_OF_STORYBOARD = "/storyboards/{id}/users";
        public static final String STORYBOARDS = "/storyboards";
        public static final String SWIMLANES = "/storyboards/{id}/swimlanes";
        public static final String SPECIFIC_STORYBOARD = "/storyboards/{id}";
        public static final String REDIRECT = "redirect:";
        public static final String DELETE_CARD = "/storyboards/{stId}/cards/{id}";

        public static String SPECIFIC_STORYBOARD(int storyboardId) {
            return "/storyboards/" + String.valueOf(storyboardId);
        }
    }

    public static class RedirectPage {
        public static final String INDEX = "index";
        public static final String LOGIN_FORM = "login";
        public static final String REGISTRATION_FORM = "registrationForm";
        public static final String STORYBOARDS = "storyboards";
        public static final String STORYBOARD = "storyboard";
    }

    public static class RequestAttributes {
        public static final String REFERER = "referer";
        public static String HttpMethod = "_method";
    }

    public class HttpMethod {
        public static final String DELETE = "_delete";
    }
}
