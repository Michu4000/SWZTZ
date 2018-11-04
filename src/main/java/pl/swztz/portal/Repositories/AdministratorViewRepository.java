package pl.swztz.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.swztz.portal.models.AdministratorView;

public interface AdministratorViewRepository extends JpaRepository<AdministratorView, Long> {
	AdministratorView findById(Long id);
}