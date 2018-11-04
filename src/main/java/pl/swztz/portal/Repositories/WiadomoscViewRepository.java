package pl.swztz.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.swztz.portal.models.WiadomoscView;

public interface WiadomoscViewRepository extends JpaRepository<WiadomoscView, Long> {

	WiadomoscView findById(Long id);			
}