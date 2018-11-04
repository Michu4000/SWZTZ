package pl.swztz.portal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.JpaRepository;

import pl.swztz.portal.repositories.WniosekRepository;

import java.io.Serializable;

@Entity
@Table(name="wniosek")
public class Wniosek implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long idWniosek;
	@Column(name="idProwadzacy")
	private Long idProwadzacy;
	@Column(name="idDyrektor")
	private Long idDyrektor;
	@Column(name="trescWniosek")
	private String trescWniosek;
	@Column(name="decyzja")
	private boolean decyzja;
	@Column(name="zmienionoPlan")
	private boolean zmienionoPlan;
	
	public Wniosek(Long idProwadzacy, Long idDyrektor, String trescWniosek, boolean decyzja, boolean zmienionoPlan) {
		
		this.idProwadzacy = idProwadzacy;
		this.idDyrektor = idDyrektor;
		this.trescWniosek = trescWniosek;
		this.decyzja = decyzja;
		this.zmienionoPlan = zmienionoPlan;
	}
	
	private Wniosek() {}
	
	public static void delById(JpaRepository repo, Long x) {
		((WniosekRepository) repo).delById(x);
	}

	public Long getId() {
		return idWniosek;
	}

	public void setId(Long idWniosek) {
		this.idWniosek = idWniosek;
	}

	public Long getIdProwadzacy() {
		return idProwadzacy;
	}

	public void setIdProwadzacy(Long idProwadzacy) {
		this.idProwadzacy = idProwadzacy;
	}

	public Long getIdDyrektor() {
		return idDyrektor;
	}

	public void setIdDyrektor(Long idDyrektor) {
		this.idDyrektor = idDyrektor;
	}

	public String getTrescWniosek() {
		return trescWniosek;
	}

	public void setTrescWniosek(String trescWniosek) {
		this.trescWniosek = trescWniosek;
	}

	public boolean isDecyzja() {
		return decyzja;
	}

	public void setDecyzja(boolean decyzja) {
		this.decyzja = decyzja;
	}

	public boolean isZmienionoPlan() {
		return zmienionoPlan;
	}

	public void setZmienionoPlan(boolean zmienionoPlan) {
		this.zmienionoPlan = zmienionoPlan;
	}
}