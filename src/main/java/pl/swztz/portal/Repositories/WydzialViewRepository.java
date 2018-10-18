package pl.swztz.portal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.Models.WydzialView;

public interface WydzialViewRepository extends JpaRepository<WydzialView, Long> {
	WydzialView findById(Long id);
}