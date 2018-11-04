package pl.swztz.portal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.JpaRepository;

import pl.swztz.portal.repositories.ProwadzacyRepository;

import java.io.Serializable;

@Entity
@Table(name="prowadzacy")
public class Prowadzacy implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long idProwadzacy;
	@Column(name="PESEL")
	private Long PESEL;
	@Column(name="imie")
	private String imie;
	@Column(name="nazwisko")
	private String nazwisko;
	@Column(name="stopien")
	private String stopien;
	@Column(name="pokoj")
	private String pokoj;

	public Prowadzacy(Long PESEL, String stopien, String imie, String nazwisko, String pokoj) {
		this.PESEL = PESEL;
		this.stopien = stopien;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.pokoj = pokoj;
	}

	private Prowadzacy(){}
	
	public static void delById(JpaRepository repo, Long x) {
		((ProwadzacyRepository) repo).delById(x);
	}

	public Long getIdProwadzacy() {
		return idProwadzacy;
	}

	public void setIdProwadzacy(Long idProwadzacy) {
		this.idProwadzacy = idProwadzacy;
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

	public String getStopien() {
		return stopien;
	}

	public void setStopien(String stopien) {
		this.stopien = stopien;
	}

	public Long getPESEL() {
		return PESEL;
	}

	public void setPESEL(Long PESEL) {
		this.PESEL = PESEL;
	}

	public String getPokoj() {
		return pokoj;
	}

	public void setPokoj(String pokoj) {
		this.pokoj = pokoj;
	}
}