package pl.swztz.portal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.Models.UrlopView;

public interface UrlopViewRepository extends JpaRepository<UrlopView, Long> {
	 UrlopView findById(Long id); 
}