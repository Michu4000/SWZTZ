package pl.swztz.portal.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.swztz.portal.models.Instytut;

public interface InstytutRepository extends JpaRepository<Instytut, Long> {
	
	Instytut findByIdInstytut(Long id);
	@Modifying
	@Transactional
	@Query(value = "DELETE INSTYTUT WHERE id_instytut=?1", nativeQuery = true)
	void delById(Long id);
	
	@Query(value = "SELECT id, nazwa_wydzial AS Wydzial FROM WYDZIAL_VIEW", nativeQuery = true)
	List<String[]> findWydzialy();
	
	@Query(value = "SELECT id, nazwa_wydzial AS Wydzial FROM WYDZIAL_VIEW WHERE id=?1", nativeQuery = true)
	List<String[]> findWydzial(Long id);
}