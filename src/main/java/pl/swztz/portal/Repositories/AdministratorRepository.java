package pl.swztz.portal.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.swztz.portal.models.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

	Administrator findByIdAdministrator(Long idAdministrator);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE ADMINISTRATOR WHERE id_administrator=?1", nativeQuery = true)
	void delById(Long id);
	
	@Query(value = "SELECT id, imie + ' ' + nazwisko FROM UZYTKOWNIK_VIEW", nativeQuery = true)
	List<String[]> findUsers();
	
	@Query(value = "SELECT id, imie + ' ' + nazwisko FROM UZYTKOWNIK_VIEW WHERE id=?1", nativeQuery = true)
	List<String[]> findUser(Long id);
}