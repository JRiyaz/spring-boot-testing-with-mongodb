package com.testing.repository;

import com.testing.exceptions.UserNotFoundException;
import com.testing.model.User;
import com.testing.prototype.UserData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    @DisplayName("It should find User with Id and Email")
    void itShouldFindUserWithIdAndEmail() {
//        given
        final User userData = UserData.get();

//        when
        repository.save(userData);
        final Optional<User> user = repository.findByIdAndEmail(userData.getId(), userData.getEmail());

//        then
        assertThat(userData).isEqualTo(user.get());
    }

    @Test
    @DisplayName("It should throw UserNotFoundException and check exception message")
    void itShouldThrowUserNotFoundExceptionAndCheckExceptionMessage() {
//        given
        final User userData = UserData.get();

//        when
//        then
        assertThatThrownBy(() -> repository.findByIdAndEmail("6072e416461da66dfc6d4d39",
                userData.getEmail()).get())
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User with Id: 6072e416461da66dfc6d4d39 and Email: "
                        + userData.getEmail() + " not found.");

    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }
}