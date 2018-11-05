package pl.swztz.portal.repositories;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.swztz.portal.models.Inspekcja;

public interface InspekcjaRepository extends JpaRepository<Inspekcja, Long> {

	Inspekcja findByIdInspekcja(Long idInspekcja);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE INSPEKCJA WHERE id_inspekcja=?1", nativeQuery = true)
	void delById(Long id);
	
	@Query(value = "SELECT DISTINCT nr_bloku FROM ZAJECIA_VIEW WHERE data=?1", nativeQuery = true)
	List<String> findBloki(Date data);
	
	@Query(value = "SELECT id, 'sala: ' + sala + ' | przedmiot: ' + nazwa_przedmiot + ' | typ zajęć: ' + typ_zajec + ' | prowadzi: ' + prowadzacy + ' | grupy: ' + grupy FROM ZAJECIA_VIEW WHERE data=?1 AND nr_bloku=?2", nativeQuery = true)
	List<String[]> findZajecia(Date data, Long nr);
	
	@Query(value = "SELECT id, data, nr_bloku, 'sala: ' + sala + ' | przedmiot: ' + nazwa_przedmiot + ' | typ zajęć: ' + typ_zajec + ' | prowadzi: ' + prowadzacy + ' | grupy: ' + grupy FROM ZAJECIA_VIEW WHERE id=?1", nativeQuery = true)
	List<String[]> findZajecie(Long id);
	
	@Query(value = "SELECT id, imie + ' ' + nazwisko + ' - ' + nazwa_instytut FROM DYREKTOR_VIEW", nativeQuery = true)
	List<String[]> findDyrektorzy();
	
	@Query(value = "SELECT id, imie + ' ' + nazwisko + ' - ' + nazwa_instytut FROM DYREKTOR_VIEW WHERE id=?1", nativeQuery = true)
	List<String[]> findDyrektor(Long id);
}