package pl.swztz.portal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.Models.InstytutView;

public interface InstytutViewRepository extends JpaRepository<InstytutView, Long> {
	 InstytutView findById(Long id); 
}