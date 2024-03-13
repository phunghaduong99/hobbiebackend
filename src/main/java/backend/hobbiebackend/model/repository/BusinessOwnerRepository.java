package backend.hobbiebackend.model.repository;

import backend.hobbiebackend.model.entities.BusinessOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessOwnerRepository extends JpaRepository<BusinessOwner, Long> {
    Optional<BusinessOwner> findByBusinessName(String businessName);

    Optional<BusinessOwner> findByUsername(String username);
}
