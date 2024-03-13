package backend.hobbiebackend.model.entities;

import backend.hobbiebackend.model.entities.enums.UserRoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleEntity extends BaseEntity {
    private UserRoleEnum role;

    @Enumerated(EnumType.STRING)
    public UserRoleEnum getRole() {
        return role;
    }
}
