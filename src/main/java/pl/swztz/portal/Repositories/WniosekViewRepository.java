package pl.swztz.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.models.WniosekView;

public interface WniosekViewRepository extends JpaRepository<WniosekView, Long> {
	WniosekView findById(Long id);
}