package pl.swztz.portal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.JpaRepository;

import pl.swztz.portal.repositories.PlanistaRepository;

import java.io.Serializable;

@Entity
@Table(name="planista")
public class Planista implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long idPlanista;
	@Column(name="idWydzial")
	private Long idWydzial;
	@Column(name="PESEL")
	private Long PESEL;
	@Column(name="imie")
	private String imie;
	@Column(name="nazwisko")
	private String nazwisko;
	
	public Planista(Long idWydzial, Long PESEL, String imie, String nazwisko) {
		this.idWydzial = idWydzial;
		this.PESEL = PESEL;
		this.imie = imie;
		this.nazwisko = nazwisko;
	}

	private Planista(){}

	public static void delById(JpaRepository repo, Long x) {
		((PlanistaRepository) repo).delById(x);
	}

	public Long getIdPlanista() {
		return idPlanista;
	}

	public void setIdPlanista(Long idPlanista) {
		this.idPlanista = idPlanista;
	}

	public Long getIdWydzial() {
		return idWydzial;
	}

	public void setIdWydzial(Long idWydzial) {
		this.idWydzial = idWydzial;
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
}