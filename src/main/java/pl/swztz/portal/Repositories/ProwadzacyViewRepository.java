package pl.swztz.portal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.Models.Prowadzacy;
import pl.swztz.portal.Models.ProwadzacyView;

public interface ProwadzacyViewRepository extends JpaRepository<ProwadzacyView, Long> {
	
	Prowadzacy findById(Long id);
}