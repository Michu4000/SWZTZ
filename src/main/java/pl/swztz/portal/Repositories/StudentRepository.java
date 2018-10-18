package pl.swztz.portal.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.swztz.portal.Models.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Student findByIdStudent(Long idStudent);
	//List<Student> findByNazwa(String value);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE STUDENT WHERE id_student=?1", nativeQuery = true)
	void delById(Long id);
	
	@Query(value = "SELECT id, nazwa FROM GRUPA_VIEW", nativeQuery = true)
	List<String[]> findGrupy();
	
	@Query(value = "SELECT id, nazwa FROM GRUPA_VIEW WHERE id=?1", nativeQuery = true)
	List<String[]> findGrupa(Long id);
}