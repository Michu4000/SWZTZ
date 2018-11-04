package pl.swztz.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.swztz.portal.models.Prowadzacy;
import pl.swztz.portal.models.ProwadzacyView;

public interface ProwadzacyViewRepository extends JpaRepository<ProwadzacyView, Long> {
	
	Prowadzacy findById(Long id);
}