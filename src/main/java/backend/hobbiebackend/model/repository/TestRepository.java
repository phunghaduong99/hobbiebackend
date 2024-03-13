package backend.hobbiebackend.model.repository;

import backend.hobbiebackend.model.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {

}
