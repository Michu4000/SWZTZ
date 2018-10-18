package pl.swztz.portal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.Models.AdministratorView;

public interface AdministratorViewRepository extends JpaRepository<AdministratorView, Long> {
	AdministratorView findById(Long id);
}