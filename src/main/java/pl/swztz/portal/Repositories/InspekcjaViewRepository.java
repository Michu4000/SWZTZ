package pl.swztz.portal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.Models.InspekcjaView;

public interface InspekcjaViewRepository extends JpaRepository<InspekcjaView, Long> {

	InspekcjaView findById(Long id);
}
