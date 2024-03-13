package backend.hobbiebackend.service.impl;

import backend.hobbiebackend.handler.NotFoundException;
import backend.hobbiebackend.model.dto.AppClientSignUpDto;
import backend.hobbiebackend.model.entities.AppClient;
import backend.hobbiebackend.model.entities.BusinessOwner;
import backend.hobbiebackend.model.entities.UserEntity;
import backend.hobbiebackend.model.entities.UserRoleEntity;
import backend.hobbiebackend.model.entities.enums.GenderEnum;
import backend.hobbiebackend.model.entities.enums.UserRoleEnum;
import backend.hobbiebackend.model.repository.AppClientRepository;
import backend.hobbiebackend.model.repository.BusinessOwnerRepository;
import backend.hobbiebackend.model.repository.UserRepository;
import backend.hobbiebackend.service.UserRoleService;
import backend.hobbiebackend.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final AppClientRepository appClientRepository;

    private final UserRoleService userRoleService;

    private final BusinessOwnerRepository businessOwnerRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(AppClientRepository appClientRepository, UserRoleService userRoleService, BusinessOwnerRepository businessOwnerRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, ModelMapper modelMapper) {
        this.appClientRepository = appClientRepository;
        this.userRoleService = userRoleService;
        this.businessOwnerRepository = businessOwnerRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedUsersAndUserRoles() {
        List<UserEntity> seededUsers = new ArrayList<>();
        //simple user
        if (appClientRepository.count() == 0) {
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setRole(UserRoleEnum.USER);
            UserRoleEntity userRole = this.userRoleService.saveRole(userRoleEntity);
            UserRoleEntity userRoleEntity2 = new UserRoleEntity();
            userRoleEntity2.setRole(UserRoleEnum.ADMIN);
            UserRoleEntity adminRole = this.userRoleService.saveRole(userRoleEntity2);
            AppClient user = new AppClient();
            user.setUsername("phunghaduong");
            user.setEmail("n13@gmail.com");
            user.setPassword(this.passwordEncoder.encode("topsecret"));
            user.setRoles(List.of(userRole));
            user.setFullName("Nikoleta Doykova");
            user.setGender(GenderEnum.FEMALE);

            appClientRepository.save(user);
            seededUsers.add(user);


        }
        if (businessOwnerRepository.count() == 0) {
            UserRoleEntity userRoleEntity3 = new UserRoleEntity();
            userRoleEntity3.setRole(UserRoleEnum.BUSINESS_USER);
            UserRoleEntity businessRole = this.userRoleService.saveRole(userRoleEntity3);
            //business_user
            BusinessOwner business_user = new BusinessOwner();
            business_user.setUsername("business");
            business_user.setEmail("n10@gamil.com");
            business_user.setPassword(this.passwordEncoder.encode("topsecret"));
            business_user.setRoles(List.of(businessRole));
            business_user.setBusinessName("My Business name");
            business_user.setAddress("My business address");
            businessOwnerRepository.save(business_user);
            seededUsers.add(business_user);
        }

    }

    @Override
    public boolean userExists(String username, String email) {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(username);
        Optional<UserEntity> byEmail = this.userRepository.findByEmail(email);
        return byUsername.isPresent() || byEmail.isPresent();
    }

    @Override
    public AppClient register(AppClientSignUpDto user) {
        UserRoleEntity userRole = this.userRoleService.getUserRoleByEnumName(UserRoleEnum.USER);
        AppClient appClient = this.modelMapper.map(user, AppClient.class);
        appClient.setRoles(List.of(userRole));
        appClient.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return appClientRepository.save(appClient);
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            return byUsername.get();
        } else {
            throw new NotFoundException("Can not find user with this username");
        }
    }

    @Override
    public AppClient findAppClientByUsername(String username) {
        return this.appClientRepository.findByUsername(username).orElseThrow();
    }
}
