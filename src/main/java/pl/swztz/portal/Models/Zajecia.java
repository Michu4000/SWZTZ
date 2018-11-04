package pl.swztz.portal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.JpaRepository;

import pl.swztz.portal.repositories.ZajeciaRepository;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="zajecia")
public class Zajecia implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long idZajecia;
	@Column(name="data")
	private LocalDate data;
	@Column(name="nrBloku")
	private Long nrBloku;
	@Column(name="idSala")
	private Long idSala;
	@Column(name="idPrzedmiot")
	private Long idPrzedmiot;
	@Column(name="typZajec")
	private String typZajec;
	@Column(name="idProwadzacy")
	private Long idProwadzacy;
	
	public Zajecia(LocalDate data, Long nrBloku, Long idSala, Long idPrzedmiot, String typZajec, Long idProwadzacy) {
		this.data = data;
		this.nrBloku = nrBloku;
		this.idSala = idSala;
		this.idPrzedmiot = idPrzedmiot;
		this.typZajec = typZajec;
		this.idProwadzacy = idProwadzacy;
	}

	private Zajecia(){}
	
	public static void delById(JpaRepository repo, Long x) {
		((ZajeciaRepository) repo).deleteZajeciaGrupy(x);
		((ZajeciaRepository) repo).delById(x);
	}

	public Long getIdZajecia() {
		return idZajecia;
	}

	public void setIdZajecia(Long idZajecia) {
		this.idZajecia = idZajecia;
	}

	public Long getIdPrzedmiot() {
		return idPrzedmiot;
	}

	public void setIdPrzedmiot(Long idPrzedmiot) {
		this.idPrzedmiot = idPrzedmiot;
	}

	public Long getIdSala() {
		return idSala;
	}

	public void setIdSala(Long idSala) {
		this.idSala = idSala;
	}

	public Long getIdProwadzacy() {
		return idProwadzacy;
	}

	public void setIdProwadzacy(Long idProwadzacy) {
		this.idProwadzacy = idProwadzacy;
	}

	public Long getNrBloku() {
		return nrBloku;
	}

	public void setNrBloku(Long nrBloku) {
		this.nrBloku = nrBloku;
	}

	public String getTypZajec() {
		return typZajec;
	}

	public void setTypZajec(String typZajec) {
		this.typZajec = typZajec;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
}