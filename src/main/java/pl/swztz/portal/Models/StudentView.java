package pl.swztz.portal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="student_view")
public class StudentView {
	
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long id;
	@Column(name="imie")
	private String imie;
	@Column(name="nazwisko")
	private String nazwisko;
	@Column(name="nazwa_grupy")
	private String nazwaGrupy;
	@Column(name="PESEL")
	private Long PESEL;

	public StudentView(Long PESEL, String imie, String nazwisko, String nazwaGrupy) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.nazwaGrupy = nazwaGrupy;
		this.PESEL = PESEL;
	}

	private StudentView(){}

	public static String[] getFieldNames() {
		String[] s = {"PESEL", "imie", "nazwisko", "nazwaGrupy"};
		return s;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazwaGrupy() {
		return nazwaGrupy;
	}

	public void setNazwaGrupy(String nazwaGrupy) {
		this.nazwaGrupy = nazwaGrupy;
	}
}