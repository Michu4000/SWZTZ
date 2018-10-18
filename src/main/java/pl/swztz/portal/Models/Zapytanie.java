package pl.swztz.portal.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.Repositories.ZapytanieRepository;
import java.io.Serializable;

@Entity
@Table(name="zapytanie")
public class Zapytanie implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long idZapytanie;
	@Column(name="idStudent")
	private Long idStudent;
	@Column(name="idProwadzacy")
	private Long idProwadzacy;
	@Column(name="trescZapytanie")
	private String trescZapytanie;
	@Column(name="decyzja")
	private boolean decyzja;

	public Zapytanie(Long idStudent, Long idProwadzacy, String trescZapytania, boolean decyzja) {
		this.idStudent = idStudent;
		this.idProwadzacy = idProwadzacy;
		this.trescZapytanie = trescZapytania;
		this.decyzja = decyzja;
	}

	private Zapytanie(){}

	public static void delById(JpaRepository repo, Long x) {
		((ZapytanieRepository) repo).delById(x);
	}

	public Long getIdZapytanie() {
		return idZapytanie;
	}

	public void setIdZapytanie(Long idZapytanie) {
		this.idZapytanie = idZapytanie;
	}

	public Long getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(Long nadawca) {
		this.idStudent = nadawca;
	}

	public Long getIdProwadzacy() {
		return idProwadzacy;
	}

	public void setIdProwadzacy(Long odbiorca) {
		this.idProwadzacy = odbiorca;
	}

	public String getTrescZapytania() {
		return trescZapytanie;
	}

	public void setTrescZapytania(String trescZapytania) {
		this.trescZapytanie = trescZapytania;
	}

	public boolean getDecyzjaProwadzacego() {
		return decyzja;
	}

	public void setDecyzjaProwadzacego(boolean decyzjaProwadzacego) {
		this.decyzja = decyzjaProwadzacego;
	}
}