package pl.swztz.portal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.Models.SalaView;

public interface SalaViewRepository extends JpaRepository<SalaView, Long> {

	SalaView findById(Long id);
}