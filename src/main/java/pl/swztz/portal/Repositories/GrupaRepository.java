package pl.swztz.portal.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.swztz.portal.Models.Grupa;

public interface GrupaRepository extends JpaRepository<Grupa, Long> {

	Grupa findByIdGrupa(Long idGrupa);
	//List<Grupa> findByNazwa(String value);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE GRUPA WHERE id_grupa=?1", nativeQuery = true)
	void delById(Long id);
	
	@Query(value = "SELECT id_student, imie + ' ' + nazwisko FROM STUDENT WHERE id_grupa=?1", nativeQuery = true)
	List<String[]> findStudenci(Long idGrupa);
	
	@Query(value = "SELECT id, imie + ' ' + nazwisko FROM STUDENT_VIEW WHERE id=?1", nativeQuery = true)
	List<String[]> findStudent(Long id);
}