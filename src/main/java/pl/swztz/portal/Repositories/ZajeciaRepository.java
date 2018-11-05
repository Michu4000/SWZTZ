package pl.swztz.portal.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.swztz.portal.models.Zajecia;

public interface ZajeciaRepository extends JpaRepository<Zajecia, Long> {

	Zajecia findByIdZajecia(Long Zajecia);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE ZAJECIA WHERE id_zajecia=?1", nativeQuery = true)
	void delById(Long id);
	
	@Query(value = "SELECT id_prowadzacy, stopien + ' ' + imie + ' ' + nazwisko FROM PROWADZACY", nativeQuery = true)
	List<String[]> findProwadzacych();
	
	@Query(value = "SELECT id_prowadzacy, stopien + ' ' + imie + ' ' + nazwisko FROM PROWADZACY WHERE id_prowadzacy=?1", nativeQuery = true)
	List<String[]> findProwadzacy(Long id);
	
	@Query(value = "SELECT id_sala, nr_sala + ' ' + budynek FROM SALA", nativeQuery = true)
	List<String[]> findSale();
	
	@Query(value = "SELECT id_sala, nr_sala + ' ' + budynek FROM SALA WHERE id_sala=?1", nativeQuery = true)
	List<String[]> findSala(Long id);
	
	@Query(value = "SELECT id_przedmiot, nazwa_przedmiot FROM PRZEDMIOT", nativeQuery = true)
	List<String[]> findPrzedmioty();
	
	@Query(value = "SELECT id_przedmiot, nazwa_przedmiot FROM PRZEDMIOT WHERE id_przedmiot=?1", nativeQuery = true)
	List<String[]> findPrzedmiot(Long id);
	
	@Query(value = "SELECT id_grupa, nazwa FROM GRUPA", nativeQuery = true)
	List<String[]> findGrupy();
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO ZAJECIA_GRUPY (id_grupa, id_zajecia) VALUES (?1, ?2)", nativeQuery = true)
	void insertZajeciaGrupy(Long idGrupy, Long idZajec);
	
	@Query(value = "SELECT zg.id_grupa, g.nazwa FROM ZAJECIA_GRUPY AS zg JOIN GRUPA AS g ON zg.id_grupa = g.id_grupa WHERE zg.id_zajecia=?1", nativeQuery = true)
	List<String[]> findZajeciaGrupy(Long idZajec);	
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM ZAJECIA_GRUPY WHERE id_zajecia=?1", nativeQuery = true)
	void deleteZajeciaGrupy(Long idZajec);	
}