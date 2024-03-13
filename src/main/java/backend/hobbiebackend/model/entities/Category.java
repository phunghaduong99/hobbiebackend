package backend.hobbiebackend.model.entities;

import backend.hobbiebackend.model.entities.enums.CategoryNameEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    private CategoryNameEnum name;
    public Category(CategoryNameEnum categoryNameEnum) {
        this.name = categoryNameEnum;
    }

    public Category() {
    }

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    public CategoryNameEnum getName() {
        return name;
    }


}
