package pl.swztz.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.models.PrzedmiotView;

public interface PrzedmiotViewRepository extends JpaRepository<PrzedmiotView, Long> {
	PrzedmiotView findById(Long id);
}