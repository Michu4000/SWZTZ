package pl.swztz.portal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.JpaRepository;

import pl.swztz.portal.repositories.SalaRepository;

import java.io.Serializable;

@Entity
@Table(name="sala")
public class Sala implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long idSala;
	@Column(name="nrSala")
	private String nrSala;
	@Column(name="typSala")
	private String typSala;
	@Column(name="budynek")
	private String budynek;
	@Column(name="iloscMiejsc")
	private int iloscMiejsc;

	public Sala(String nrSala, String typSala, String budynek, int iloscMiejsc) {
		this.nrSala = nrSala;
		this.typSala = typSala;
		this.budynek = budynek;
		this.iloscMiejsc = iloscMiejsc;
	}

	private Sala(){}
	
	public static void delById(JpaRepository repo, Long x) {
		((SalaRepository) repo).delById(x);
	}

	public Long getId() {
		return idSala;
	}

	public void setId(Long id) {
		this.idSala = id;
	}

	public String getNrSala() {
		return nrSala;
	}

	public void setNrSala(String nrSala) {
		this.nrSala = nrSala;
	}

	public String getTypSala() {
		return typSala;
	}

	public void setTypSala(String typSala) {
		this.typSala = typSala;
	}

	public String getBudynek() {
		return budynek;
	}

	public void setBudynek(String budynek) {
		this.budynek = budynek;
	}

	public int getIloscMiejsc() {
		return iloscMiejsc;
	}

	public void setIloscMiejsc(int iloscMiejsc) {
		this.iloscMiejsc = iloscMiejsc;
	}
}