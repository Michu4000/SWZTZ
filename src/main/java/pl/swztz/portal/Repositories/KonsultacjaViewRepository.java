package pl.swztz.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.models.KonsultacjaView;

public interface KonsultacjaViewRepository extends JpaRepository<KonsultacjaView, Long> {
	KonsultacjaView findById(Long id);
}