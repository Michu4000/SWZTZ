package pl.swztz.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.swztz.portal.models.PlanistaView;

public interface PlanistaViewRepository extends JpaRepository<PlanistaView, Long> {

	PlanistaView findById(Long id);
}