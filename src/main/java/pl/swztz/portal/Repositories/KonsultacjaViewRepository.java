package pl.swztz.portal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.Models.KonsultacjaView;

public interface KonsultacjaViewRepository extends JpaRepository<KonsultacjaView, Long> {
	KonsultacjaView findById(Long id);
}