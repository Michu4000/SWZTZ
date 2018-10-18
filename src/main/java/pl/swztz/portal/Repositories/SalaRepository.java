package pl.swztz.portal.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.swztz.portal.Models.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long> {

	Sala findByIdSala(Long idSala);
	//List<Sala> findByNazwa(String value);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE SALA WHERE id_sala=?1", nativeQuery = true)
	void delById(Long id);
}