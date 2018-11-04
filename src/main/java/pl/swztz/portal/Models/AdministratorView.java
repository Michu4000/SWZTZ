package pl.swztz.portal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;

@Entity
@Table(name="administratorView")
public class AdministratorView implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long id;
	@Column(name="PESEL")
	private Long PESEL;
	@Column(name="imie")
	private String imie;
	@Column(name="nazwisko")
	private String nazwisko;
	@Column(name="uprawnienia")
	private String uprawnienia;
	
	public AdministratorView(Long PESEL, String imie, String nazwisko, String uprawnienia) {
		this.PESEL = PESEL;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.uprawnienia = uprawnienia;
	}
	
	private AdministratorView() {}
	
	public static String[] getFieldNames() {
		String[] s = {"PESEL", "imie", "nazwisko", "uprawnienia"};
		return s;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getPESEL() {
		return PESEL;
	}

	public void setPESEL(Long PESEL) {
		this.PESEL = PESEL;
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

	public String getUprawnienia() {
		return uprawnienia;
	}

	public void setUprawnienia(String uprawnienia) {
		this.uprawnienia = uprawnienia;
	}
}