package backend.hobbiebackend.service.impl;

import backend.hobbiebackend.model.entities.Location;
import backend.hobbiebackend.model.entities.enums.LocationEnum;
import backend.hobbiebackend.model.repository.LocationRepository;
import backend.hobbiebackend.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> initLocations() {
        List<Location> init = new ArrayList<>();
        if (locationRepository.count() == 0) {
            Arrays.stream(LocationEnum.values()).forEach(locationEnum -> {
                Location location = new Location();
                location.setName(locationEnum);
                this.locationRepository.save(location);
                init.add(location);
            });
        }
        return init;
    }

}
