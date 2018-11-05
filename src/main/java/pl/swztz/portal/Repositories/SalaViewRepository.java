package pl.swztz.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.models.SalaView;

public interface SalaViewRepository extends JpaRepository<SalaView, Long> {

	SalaView findById(Long id);
}