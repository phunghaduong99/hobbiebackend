package backend.hobbiebackend.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "business_owners")
@Setter
@NoArgsConstructor
public class BusinessOwner extends UserEntity{

    private String address;

    private String businessName;

    private Set<Hobby> hobby_offers;

    public BusinessOwner(String username, String email, List<UserRoleEntity> roles, String password, String address, String businessName) {
        super(username, email, roles, password);
        this.address = address;
        this.businessName = businessName;
    }

    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }
    @Column(name = "business_name", nullable = false)
    public String getBusinessName() {
        return businessName;
    }
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    public Set<Hobby> getHobby_offers() {
        return hobby_offers;
    }
}
