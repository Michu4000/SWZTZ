package pl.swztz.portal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.Models.ZajeciaView;

public interface ZajeciaViewRepository extends JpaRepository<ZajeciaView, Long> {
	ZajeciaView findById(Long id);
}