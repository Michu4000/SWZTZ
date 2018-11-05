package pl.swztz.portal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.repositories.DyrektorRepository;
import java.io.Serializable;

@Entity
@Table(name="dyrektor")
public class Dyrektor implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long idDyrektor;
	@Column(name="idInstytut")
	private Long idInstytut;
	@Column(name="PESEL")
	private Long PESEL;
	@Column(name="imie")
	private String imie;
	@Column(name="nazwisko")
	private String nazwisko;
	@Column(name="pokoj")
	private String pokoj;
	
	public Dyrektor(Long idInstytut, Long PESEL, String imie, String nazwisko, String pokoj) {
		this.idInstytut = idInstytut;
		this.PESEL = PESEL;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.pokoj = pokoj;
	}

	private Dyrektor(){}

	public static void delById(JpaRepository repo, Long x) {
		((DyrektorRepository) repo).delById(x);
	}

	public Long getIdDyrektor() {
		return idDyrektor;
	}

	public void setIdDyrektor(Long idDyrektor) {
		this.idDyrektor = idDyrektor;
	}

	public Long getIdInstytut() {
		return idInstytut;
	}

	public void setIdInstytut(Long idInstytut) {
		this.idInstytut = idInstytut;
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

	public String getPokoj() {
		return pokoj;
	}

	public void setPokoj(String pokoj) {
		this.pokoj = pokoj;
	}
}