package pl.swztz.portal.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.swztz.portal.Models.Wiadomosc;

public interface WiadomoscRepository extends JpaRepository<Wiadomosc, Long> {
	Wiadomosc findByIdWiadomosc(Long id);
	
	@Query(value = "SELECT id, imie + ' ' + nazwisko FROM UZYTKOWNIK_VIEW", nativeQuery = true)
	List<String[]> findUzytkownicy();
	
	@Query(value = "SELECT id, imie + ' ' + nazwisko FROM UZYTKOWNIK_VIEW WHERE id=?1", nativeQuery = true)
	List<String[]> findUzytkownik(Long id);
}