package pl.swztz.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.swztz.portal.models.WydzialView;

public interface WydzialViewRepository extends JpaRepository<WydzialView, Long> {
	WydzialView findById(Long id);
}