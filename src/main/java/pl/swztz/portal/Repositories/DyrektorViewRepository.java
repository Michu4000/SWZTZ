package pl.swztz.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.swztz.portal.models.DyrektorView;

public interface DyrektorViewRepository extends JpaRepository<DyrektorView, Long> {

	DyrektorView findById(Long id);
}
