package backend.hobbiebackend.model.entities;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {
    protected Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    public Long getId() {
        return id;
    }
}
