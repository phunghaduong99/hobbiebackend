package backend.hobbiebackend.service.impl;

import backend.hobbiebackend.model.entities.Category;
import backend.hobbiebackend.model.entities.UserEntity;
import backend.hobbiebackend.model.entities.enums.CategoryNameEnum;
import backend.hobbiebackend.model.repository.CategoryRepository;
import backend.hobbiebackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> initCategories() {
        List<Category> init = new ArrayList<>();
        if (categoryRepository.count() == 0) {
            Arrays.stream(CategoryNameEnum.values()).forEach(categoryNameEnum -> {
                Category category = new Category();
                category.setName(categoryNameEnum);
                this.categoryRepository.save(category);
                init.add(category);
            });
        }
        return init;
    }
}
