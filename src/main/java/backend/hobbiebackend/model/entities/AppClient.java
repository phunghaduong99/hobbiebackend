package backend.hobbiebackend.model.entities;

import backend.hobbiebackend.model.entities.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "app_clients")
@Setter
public class AppClient extends UserEntity implements Serializable {
    private String fullName;

    private GenderEnum gender;

    private Test testResults;

    private Set<Hobby> hobby_matches;

    private List<Hobby> saved_hobbies;

    public AppClient(String username, String email, List<UserRoleEntity> roles, String password, String fullName, GenderEnum gender) {
        super(username, email, roles, password);
        this.fullName = fullName;
        this.gender = gender;
    }
    public AppClient() {
    }


    @Column(name = "full_name", nullable = false)
    public String getFullName() {
        return fullName;
    }
    @Column(nullable = false, columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    public GenderEnum getGender() {
        return gender;
    }


    @OneToOne(cascade = CascadeType.REMOVE)
    public Test getTestResults() {
        return testResults;
    }
    @ManyToMany( fetch = FetchType.EAGER)
    public Set<Hobby> getHobby_matches() {
        return hobby_matches;
    }
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Hobby> getSaved_hobbies() {
        return saved_hobbies;
    }
}
