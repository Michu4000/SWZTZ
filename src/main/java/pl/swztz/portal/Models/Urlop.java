package pl.swztz.portal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.repositories.UrlopRepository;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="urlop")
public class Urlop implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long idUrlop;
	@Column(name="idProwadzacy")
	private Long idProwadzacy;
	@Column(name="dataRozpoczecia")
	private LocalDate dataRozpoczecia;
	@Column(name="dataZakonczenia")
	private LocalDate dataZakonczenia;
	@Column (name="powod")
	private String powod;
	public Urlop(Long idProwadzacy,  LocalDate dataRozpoczecia, LocalDate dataZakonczenia, String powod) {
		this.idProwadzacy = idProwadzacy;
		this.dataRozpoczecia = dataRozpoczecia;
		this.dataZakonczenia = dataZakonczenia;
		this.powod = powod;
	}

	private Urlop(){}
	
	public static void delById(JpaRepository repo, Long x) {
		((UrlopRepository) repo).delById(x);
	}
	
	public Long getIdUrlop() {
		return idUrlop;
	}

	public void setIdUrlop(Long idUrlop) {
		this.idUrlop = idUrlop;
	}

	public Long getIdProwadzacy() {
		return idProwadzacy;
	}

	public void setIdProwadzacy(Long idProwadzacy) {
		this.idProwadzacy = idProwadzacy;
	}

	public LocalDate getDataRozpoczecia() {
		return dataRozpoczecia;
	}

	public void setDataRozpoczecia(LocalDate dataRozpoczecia) {
		this.dataRozpoczecia = dataRozpoczecia;
	}

	public LocalDate getDataZakonczenia() {
		return dataZakonczenia;
	}

	public void setDataZakonczenia(LocalDate localDateTime) {
		this.dataZakonczenia = localDateTime;
	}

	public void setPowod (String powod) {
		this.powod = powod;
	}
	public String getPowod() {
		return powod;
	}
}