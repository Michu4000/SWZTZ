package pl.swztz.portal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.Models.Student;
import pl.swztz.portal.Models.StudentView;

public interface StudentViewRepository extends JpaRepository<StudentView, Long> {

	Student findById(Long id);
}