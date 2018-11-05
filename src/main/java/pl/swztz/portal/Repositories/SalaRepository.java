package pl.swztz.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.swztz.portal.models.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long> {

	Sala findByIdSala(Long idSala);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE SALA WHERE id_sala=?1", nativeQuery = true)
	void delById(Long id);
}