package pl.swztz.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.swztz.portal.models.Prowadzacy;

public interface ProwadzacyRepository extends JpaRepository<Prowadzacy, Long> {

	Prowadzacy findByIdProwadzacy(Long idProwadzacy);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE PROWADZACY WHERE id_prowadzacy=?1", nativeQuery = true)
	void delById(Long id);
}