package pl.swztz.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.swztz.portal.models.BladView;

public interface BladViewRepository extends JpaRepository<BladView, Long> {

	BladView findById(Long id);
}