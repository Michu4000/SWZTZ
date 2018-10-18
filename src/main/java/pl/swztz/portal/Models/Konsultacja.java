package pl.swztz.portal.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.Repositories.KonsultacjaRepository;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="konsultacja")
public class Konsultacja implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long idKonsultacja;
	@Column(name="data")
	private LocalDate data;
	@Column(name="nrBloku")
	private Long nrBloku;
	@Column(name="idSala")
	private Long idSala;
	@Column(name="idProwadzacy")
	private Long idProwadzacy;
	@Column(name="komentarz")
	private String komentarz;

	public Konsultacja(LocalDate data, Long nrBloku, Long idSala, Long idProwadzacy, String komentarz) {
		this.data = data;
		this.nrBloku = nrBloku;
		this.idSala = idSala;
		this.idProwadzacy = idProwadzacy;
		this.komentarz = komentarz;
	}

	private Konsultacja(){}

	public static void delById(JpaRepository repo, Long x) {
		((KonsultacjaRepository) repo).delById(x);
	}
	
	public Long getIdKonsultacja() {
		return idKonsultacja;
	}

	public void setIdKonsultacja(Long idKonsultacja) {
		this.idKonsultacja = idKonsultacja;
	}

	public Long getIdProwadzacy() {
		return idProwadzacy;
	}

	public void setProwadzacy(Long idProwadzacy) {
		this.idProwadzacy = idProwadzacy;
	}

	public Long getIdSala() {
		return idSala;
	}

	public void setIdSala(Long idSala) {
		this.idSala = idSala;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Long getNrBloku() {
		return nrBloku;
	}

	public void setNrBloku(Long nrBloku) {
		this.nrBloku = nrBloku;
	}
	
	public String getKomentarz() {
		return komentarz;
	}
	
	public void setKomentarz(String komentarz) {
		this.komentarz = komentarz;
	}
}