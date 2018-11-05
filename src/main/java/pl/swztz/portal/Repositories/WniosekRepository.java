package pl.swztz.portal.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.swztz.portal.models.Wniosek;

public interface WniosekRepository extends JpaRepository<Wniosek, Long> {

	Wniosek findByIdWniosek(Long idWniosek);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE WNIOSEK WHERE id_wniosek=?1", nativeQuery = true)
	void delById(Long id);
	
	@Query(value = "SELECT id, stopien + ' ' + imie + ' ' + nazwisko FROM PROWADZACY_VIEW", nativeQuery = true)
	List<String[]> findProwadzacych();
	
	@Query(value = "SELECT id, stopien + ' ' + imie + ' ' + nazwisko FROM PROWADZACY_VIEW WHERE id=?1", nativeQuery = true)
	List<String[]> findProwadzacy(Long id);
	
	@Query(value = "SELECT id, imie + ' ' + nazwisko + ' - ' + nazwa_instytut FROM DYREKTOR_VIEW", nativeQuery = true)
	List<String[]> findDyrektorzy();
	
	@Query(value = "SELECT id, imie + ' ' + nazwisko + ' - ' + nazwa_instytut FROM DYREKTOR_VIEW WHERE id=?1", nativeQuery = true)
	List<String[]> findDyrektor(Long id);
}