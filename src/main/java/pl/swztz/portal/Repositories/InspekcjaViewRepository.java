package pl.swztz.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.models.InspekcjaView;

public interface InspekcjaViewRepository extends JpaRepository<InspekcjaView, Long> {

	InspekcjaView findById(Long id);
}
