package backend.hobbiebackend.model.entities;

import backend.hobbiebackend.model.entities.enums.LocationEnum;
import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Table(name = "locations")
@Setter
public class Location extends BaseEntity {
    private LocationEnum name;

    public Location(LocationEnum name) {
        this.name = name;
    }

    public Location() {
    }

    @Column(unique = true, columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    public LocationEnum getName() {
        return name;
    }
}
