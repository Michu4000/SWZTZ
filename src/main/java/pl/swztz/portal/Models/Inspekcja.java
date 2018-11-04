package pl.swztz.portal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.JpaRepository;

import pl.swztz.portal.repositories.InspekcjaRepository;

import java.io.Serializable;

@Entity
@Table(name="inspekcja")
public class Inspekcja implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long idInspekcja;
	@Column(name="idZajecia")
	private Long idZajecia;
	@Column(name="idDyrektor")
	private Long idDyrektor;
	@Column(name="komentarzInspekcja")
	private String komentarzInspekcja;
	
	public Inspekcja(Long idZajecia, Long idDyrektor, String komentarzInspekcja) {
		this.idZajecia = idZajecia;
		this.idDyrektor = idDyrektor;
		this.komentarzInspekcja = komentarzInspekcja;
	}

	private Inspekcja(){}

	public static void delById(JpaRepository repo, Long x) {
		((InspekcjaRepository) repo).delById(x);
	}

	public Long getIdInspekcja() {
		return idInspekcja;
	}

	public void setIdInspekcja(Long idInspekcja) {
		this.idInspekcja = idInspekcja;
	}

	public Long getIdZajecia() {
		return idZajecia;
	}

	public void setIdZajecia(Long idZajecia) {
		this.idZajecia = idZajecia;
	}

	public Long getIdDyrektor() {
		return idDyrektor;
	}

	public void setIdDyrektor(Long idDyrektor) {
		this.idDyrektor = idDyrektor;
	}

	public String getKomentarzInspekcja() {
		return komentarzInspekcja;
	}

	public void setKomentarzInspekcja(String komentarzInspekcja) {
		this.komentarzInspekcja = komentarzInspekcja;
	}
}