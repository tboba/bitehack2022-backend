package pl.wilczadruzyna.biteshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wilczadruzyna.biteshare.model.post.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Location findLocationByLatitudeAndLongitude(Double latitude, Double longitude);

}
