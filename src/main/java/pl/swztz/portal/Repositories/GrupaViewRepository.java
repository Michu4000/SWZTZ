package pl.swztz.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.models.GrupaView;

public interface GrupaViewRepository extends JpaRepository<GrupaView, Long> {
	GrupaView findById(Long id);
}