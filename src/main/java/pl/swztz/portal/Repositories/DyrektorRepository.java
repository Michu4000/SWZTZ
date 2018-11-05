package pl.swztz.portal.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import pl.swztz.portal.models.Dyrektor;

public interface DyrektorRepository extends JpaRepository<Dyrektor, Long> {

	Dyrektor findByIdDyrektor(Long idDyrektor);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE DYREKTOR WHERE id_dyrektor=?1", nativeQuery = true)
	void delById(Long id);
	
	@Query(value = "SELECT id, nazwa_instytut FROM INSTYTUT_VIEW", nativeQuery = true)
	List<String[]> findInstytuty();
	
	@Query(value = "SELECT id, nazwa_instytut FROM INSTYTUT_VIEW WHERE id=?1", nativeQuery = true)
	List<String[]> findInstytut(Long id);
}