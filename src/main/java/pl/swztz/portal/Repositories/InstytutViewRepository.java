package pl.swztz.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.models.InstytutView;

public interface InstytutViewRepository extends JpaRepository<InstytutView, Long> {
	 InstytutView findById(Long id); 
}