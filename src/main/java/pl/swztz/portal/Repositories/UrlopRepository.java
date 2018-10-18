package pl.swztz.portal.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import pl.swztz.portal.Models.Urlop;

public interface UrlopRepository extends JpaRepository<Urlop, Long> {

	Urlop findByIdUrlop(Long idUrlop);
	//List<Urlop> findByNazwa(String value);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE URLOP WHERE id_urlop=?1", nativeQuery = true)
	void delById(Long id);
	
	@Query(value = "SELECT id, stopien + ' ' + imie + ' ' + nazwisko AS Prowadzacy FROM PROWADZACY_VIEW", nativeQuery = true)
	List<String[]> findProwadzacych();
	
	@Query(value = "SELECT id, stopien + ' ' + imie + ' ' + nazwisko AS Prowadzacy FROM PROWADZACY_VIEW WHERE id=?1", nativeQuery = true)
	List<String[]> findProwadzacy(Long id);
}