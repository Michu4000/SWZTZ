package pl.swztz.portal.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.swztz.portal.Models.Przedmiot;

public interface PrzedmiotRepository extends JpaRepository<Przedmiot, Long> {
	
	Przedmiot findByIdPrzedmiot(Long idPrzedmiot);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE PRZEDMIOT WHERE id_przedmiot=?1", nativeQuery = true)
		void delById(Long id);
	
	@Query(value = "SELECT id, nazwa_instytut FROM INSTYTUT_VIEW", nativeQuery = true)
	List<String[]> findInstytuty();
	
	@Query(value = "SELECT id, nazwa_instytut FROM INSTYTUT_VIEW WHERE id=?1", nativeQuery = true)
	List<String[]> findInstytut(Long id);
}
