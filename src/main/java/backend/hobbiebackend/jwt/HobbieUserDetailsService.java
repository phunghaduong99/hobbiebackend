package backend.hobbiebackend.jwt;

import backend.hobbiebackend.model.entities.UserEntity;
import backend.hobbiebackend.model.repository.UserRepository;
import backend.hobbiebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HobbieUserDetailsService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Autowired
    public HobbieUserDetailsService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User with username " + username + " was not found."));
        return mapToUserDetails(userEntity);
    }

    private UserDetails mapToUserDetails(UserEntity userEntity) {
        System.out.println(userEntity);
        System.out.println(userEntity.getUsername());
        System.out.println(userEntity.getPassword());
        List<GrantedAuthority> authorities = userEntity.getRoles().stream()
                .map(ur -> new SimpleGrantedAuthority("ROLE_" + ur.getRole().name())).collect(Collectors.toList());
        System.out.println(authorities);
        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities
        );
    }
}
