package backend.hobbiebackend.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity extends BaseEntity implements Serializable {

    private String username;

    private String email;

    private List<UserRoleEntity> roles = new ArrayList<>();

    private String password;

    @Column(nullable = false, unique = true, name= "username")
    public String getUsername() {
        return username;
    }
    @Column(nullable = false, unique = true)
    public String getEmail() {
        return email;
    }
    @ManyToMany(fetch = FetchType.EAGER)
    public List<UserRoleEntity> getRoles() {
        return roles;
    }
    @Column(nullable = false)
    public String getPassword() {
        return password;
    }
}
