package pl.swztz.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.swztz.portal.models.Student;
import pl.swztz.portal.models.StudentView;

public interface StudentViewRepository extends JpaRepository<StudentView, Long> {

	Student findById(Long id);
}