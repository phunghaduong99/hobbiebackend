package backend.hobbiebackend.service;

import backend.hobbiebackend.model.entities.UserRoleEntity;
import backend.hobbiebackend.model.entities.enums.UserRoleEnum;

public interface UserRoleService {
    UserRoleEntity saveRole(UserRoleEntity userRoleEntity);

    UserRoleEntity getUserRoleByEnumName(UserRoleEnum userRoleEnum);
}
