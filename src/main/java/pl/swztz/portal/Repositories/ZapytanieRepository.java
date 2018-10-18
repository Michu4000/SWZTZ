package pl.swztz.portal.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.swztz.portal.Models.Zapytanie;

public interface ZapytanieRepository extends JpaRepository<Zapytanie, Long> {

	Zapytanie findByIdZapytanie(Long idZapytanie);

	@Modifying
	@Transactional
	@Query(value = "DELETE ZAPYTANIE WHERE id_zapytanie=?1", nativeQuery = true)
	void delById(Long id);
	
	@Query(value = "SELECT id_prowadzacy, stopien + ' ' + imie + ' ' + nazwisko FROM PROWADZACY", nativeQuery = true)
	List<String[]> findProwadzacych();
	
	@Query(value = "SELECT id_prowadzacy, stopien + ' ' + imie + ' ' + nazwisko FROM PROWADZACY WHERE id_prowadzacy=?1", nativeQuery = true)
	List<String[]> findProwadzacy(Long id);
	
	@Query(value = "SELECT imie + ' ' + nazwisko FROM STUDENT WHERE id_student=?1", nativeQuery = true)
	String findStudent(Long id);
	
	@Query(value = "SELECT id_student FROM STUDENT WHERE imie + ' ' + nazwisko=?1", nativeQuery = true)
	Long findStudentID(String s);
	
	@Query(value = "SELECT email FROM UZYTKOWNIK WHERE id_prowadzacy=?1", nativeQuery = true)
	String findEmailProwadzacy(Long id);
}