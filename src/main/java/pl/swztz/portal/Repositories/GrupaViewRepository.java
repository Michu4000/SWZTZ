package pl.swztz.portal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.Models.GrupaView;

public interface GrupaViewRepository extends JpaRepository<GrupaView, Long> {
	GrupaView findById(Long id);
}