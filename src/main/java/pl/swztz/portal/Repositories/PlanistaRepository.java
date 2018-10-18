package pl.swztz.portal.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.swztz.portal.Models.Planista;

public interface PlanistaRepository extends JpaRepository<Planista, Long> {

	Planista findByIdPlanista(Long id);
	//List<Planista> findByNazwa(String value);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE PLANISTA WHERE id_planista=?1", nativeQuery = true)
	void delById(Long id);
	
	@Query(value = "SELECT id, nazwa_wydzial FROM WYDZIAL_VIEW", nativeQuery = true)
	List<String[]> findWydzialy();
	
	@Query(value = "SELECT id, nazwa_wydzial FROM WYDZIAL_VIEW WHERE id=?1", nativeQuery = true)
	List<String[]> findWydzial(Long id);
}