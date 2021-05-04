package com.testing.prototype;

import com.testing.model.User;

public class UserData {

    public static User get() {
        return new User("Riyaz J", "j.riyazu@gmail.com", "Male");
    }
}
