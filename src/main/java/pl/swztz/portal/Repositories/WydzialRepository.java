package pl.swztz.portal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.swztz.portal.Models.Wydzial;

public interface WydzialRepository extends JpaRepository<Wydzial, Long> {
	Wydzial findByIdWydzial(Long idWydzial);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE WYDZIAL WHERE id_wydzial=?1", nativeQuery = true)
	void delById(Long id);
}