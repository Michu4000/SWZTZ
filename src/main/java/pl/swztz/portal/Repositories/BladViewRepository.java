package pl.swztz.portal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.Models.BladView;

public interface BladViewRepository extends JpaRepository<BladView, Long> {

	BladView findById(Long id);
}