package pl.swztz.portal.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.swztz.portal.Models.Blad;

public interface BladRepository extends JpaRepository<Blad, Long> {

	Blad findByIdBlad(Long idBlad);
	//List<Blad> findByNazwa(String value);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE BLAD WHERE id_blad=?1", nativeQuery = true)
	void delById(Long id);
	
	@Query(value = "SELECT id, imie + ' ' + nazwisko FROM UZYTKOWNIK_VIEW", nativeQuery = true)
	List<String[]> findUsers();
	
	@Query(value = "SELECT id, imie + ' ' + nazwisko FROM UZYTKOWNIK_VIEW WHERE id=?1", nativeQuery = true)
	List<String[]> findUser(Long id);
}