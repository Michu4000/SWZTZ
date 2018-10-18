package pl.swztz.portal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.Models.DyrektorView;

public interface DyrektorViewRepository extends JpaRepository<DyrektorView, Long> {

	DyrektorView findById(Long id);
}
