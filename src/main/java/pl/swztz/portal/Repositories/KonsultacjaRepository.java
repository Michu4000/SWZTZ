package pl.swztz.portal.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.swztz.portal.Models.Konsultacja;

public interface KonsultacjaRepository extends JpaRepository<Konsultacja, Long> {

	Konsultacja findByIdKonsultacja(Long idKonsulatacja);
	//List<Konsultacja> findByNazwa(String value);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE KONSULTACJA WHERE id_konsultacja=?1", nativeQuery = true)
	void delById(Long id);
	
	@Query(value = "SELECT id_prowadzacy FROM PROWADZACY WHERE stopien + ' ' + imie + ' ' + nazwisko=?1", nativeQuery = true)
	Long findIdProwadzacy(String prowadzacy);
	
	@Query(value = "SELECT prowadzacy FROM KONSULTACJA_VIEW WHERE id=?1", nativeQuery = true)
	String findProwadzacy(Long id);
	
	@Query(value = "SELECT id_sala, nr_sala + ' ' + budynek FROM SALA", nativeQuery = true)
	List<String[]> findSale();
	
	@Query(value = "SELECT id_sala, nr_sala + ' ' + budynek FROM SALA WHERE id_sala=?1", nativeQuery = true)
	List<String[]> findSala(Long id);
}