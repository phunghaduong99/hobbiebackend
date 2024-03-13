package backend.hobbiebackend.service;

import backend.hobbiebackend.model.dto.AppClientSignUpDto;
import backend.hobbiebackend.model.entities.AppClient;
import backend.hobbiebackend.model.entities.UserEntity;

public interface UserService {
    void seedUsersAndUserRoles();

    boolean userExists(String username, String email);

    AppClient register(AppClientSignUpDto user);

    UserEntity findUserByUsername(String username);

    AppClient findAppClientByUsername(String username);
}
