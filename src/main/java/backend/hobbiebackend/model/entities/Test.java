package backend.hobbiebackend.model.entities;

import backend.hobbiebackend.model.entities.enums.CategoryNameEnum;
import backend.hobbiebackend.model.entities.enums.LocationEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "test_results")
@Setter
public class Test extends BaseEntity {
    private String username;
    private CategoryNameEnum categoryOne;
    private CategoryNameEnum categoryTwo;
    private CategoryNameEnum categoryThree;
    private CategoryNameEnum categoryFour;
    private CategoryNameEnum categoryFive;
    private CategoryNameEnum categorySix;
    private CategoryNameEnum categorySeven;
    private LocationEnum location;

    public Test() {
    }

    @Column
    public String getUsername() {
        return username;
    }

    @Column(name = "category_one", columnDefinition = "varchar(255)")
    public CategoryNameEnum getCategoryOne() {
        return categoryOne;
    }

    @Column(name = "category_two", columnDefinition = "varchar(255)")
    public CategoryNameEnum getCategoryTwo() {
        return categoryTwo;
    }

    @Column(name = "category_three", columnDefinition = "varchar(255)")
    public CategoryNameEnum getCategoryThree() {
        return categoryThree;
    }

    @Column(name = "category_four", columnDefinition = "varchar(255)")
    public CategoryNameEnum getCategoryFour() {
        return categoryFour;
    }

    @Column(name = "category_five", columnDefinition = "varchar(255)")
    public CategoryNameEnum getCategoryFive() {
        return categoryFive;
    }

    @Column(name = "category_six", columnDefinition = "varchar(255)")
    public CategoryNameEnum getCategorySix() {
        return categorySix;
    }

    @Column(name = "category_seven", columnDefinition = "varchar(255)")
    public CategoryNameEnum getCategorySeven() {
        return categorySeven;
    }

    @Column(columnDefinition = "varchar(255)")
    public LocationEnum getLocation() {
        return location;
    }

}