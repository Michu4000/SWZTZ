package pl.swztz.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.swztz.portal.models.UrlopView;

public interface UrlopViewRepository extends JpaRepository<UrlopView, Long> {
	 UrlopView findById(Long id); 
}