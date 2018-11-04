package pl.swztz.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.swztz.portal.models.ZajeciaView;

public interface ZajeciaViewRepository extends JpaRepository<ZajeciaView, Long> {
	ZajeciaView findById(Long id);
}