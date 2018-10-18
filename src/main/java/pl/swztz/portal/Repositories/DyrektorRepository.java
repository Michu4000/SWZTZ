package pl.swztz.portal.Repositories;

import java.util.List;

//import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.swztz.portal.Models.Dyrektor;

public interface DyrektorRepository extends JpaRepository<Dyrektor, Long> {

	Dyrektor findByIdDyrektor(Long idDyrektor);
	//List<Dyrektor> findByNazwa(String value);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE DYREKTOR WHERE id_dyrektor=?1", nativeQuery = true)
	void delById(Long id);
	
	@Query(value = "SELECT id, nazwa_instytut FROM INSTYTUT_VIEW", nativeQuery = true)
	List<String[]> findInstytuty();
	
	@Query(value = "SELECT id, nazwa_instytut FROM INSTYTUT_VIEW WHERE id=?1", nativeQuery = true)
	List<String[]> findInstytut(Long id);
}