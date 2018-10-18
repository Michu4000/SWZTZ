package pl.swztz.portal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.Models.WiadomoscView;

public interface WiadomoscViewRepository extends JpaRepository<WiadomoscView, Long> {

	WiadomoscView findById(Long id);			
}