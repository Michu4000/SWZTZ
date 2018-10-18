package pl.swztz.portal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.Models.PlanistaView;

public interface PlanistaViewRepository extends JpaRepository<PlanistaView, Long> {

	PlanistaView findById(Long id);
}