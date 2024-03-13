package backend.hobbiebackend.init;

import backend.hobbiebackend.service.CategoryService;
import backend.hobbiebackend.service.LocationService;
import backend.hobbiebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner{
    private final UserService userService;

    private final CategoryService categoryService;

    private final LocationService locationService;

    @Autowired
    public DBInit(UserService userService, CategoryService categoryService, LocationService locationService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.locationService = locationService;
        this.locationService.initLocations();
    }

    @Override
    public void run(String... args) throws Exception {
        this.userService.seedUsersAndUserRoles();
        this.categoryService.initCategories();
    }
}
