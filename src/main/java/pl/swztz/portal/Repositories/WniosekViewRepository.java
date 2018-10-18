package pl.swztz.portal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.Models.WniosekView;

public interface WniosekViewRepository extends JpaRepository<WniosekView, Long> {
	WniosekView findById(Long id);
}