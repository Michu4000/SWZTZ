package pl.swztz.portal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.Models.PrzedmiotView;

public interface PrzedmiotViewRepository extends JpaRepository<PrzedmiotView, Long> {
	PrzedmiotView findById(Long id);
}