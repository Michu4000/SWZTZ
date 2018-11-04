package pl.swztz.portal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.JpaRepository;

import pl.swztz.portal.repositories.StudentRepository;

import java.io.Serializable;

@Entity
@Table(name="student")
public class Student implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long idStudent;
	@Column(name="imie")
	private String imie;
	@Column(name="nazwisko")
	private String nazwisko;
	@Column(name="id_grupa")
	private Long idGrupa;
	@Column(name="PESEL")
	private Long PESEL;

	public Student(Long PESEL, String imie, String nazwisko, Long idGrupa) {
		this.PESEL = PESEL;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.idGrupa = idGrupa;
	}

	private Student(){}

	public static void delById(JpaRepository repo, Long x) {
		((StudentRepository) repo).delById(x);
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public Long getPESEL() {
		return PESEL;
	}

	public void setPESEL(Long PESEL) {
		this.PESEL = PESEL;
	}

	public Long getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(Long idStudent) {
		this.idStudent = idStudent;
	}

	public Long getIdGrupa() {
		return idGrupa;
	}

	public void setIdGrupa(Long idGrupa) {
		this.idGrupa = idGrupa;
	}
}