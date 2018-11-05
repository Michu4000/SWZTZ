package pl.swztz.portal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.repositories.BladRepository;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="blad")
public class Blad implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long idBlad;
	@Column(name="idUzytkownik")
	private Long idUzytkownik;
	@Column(name="temat")
	private String temat;
	@Column(name="opis")
	private String opis;
	@Column(name="dataZgloszenia")
	private LocalDateTime dataZgloszenia;
	@Column(name="rozpatrzono")
	private boolean rozpatrzono;

	public Blad(Long idUzytkownik, String temat, String opis, LocalDateTime dataZgloszenia, boolean rozpatrzono) {
		this.idUzytkownik = idUzytkownik;
		this.temat = temat;
		this.opis = opis;
		this.dataZgloszenia = dataZgloszenia;
		this.rozpatrzono = rozpatrzono;
	}

	private Blad(){}
	
	public static void delById(JpaRepository repo, Long x) {
		((BladRepository) repo).delById(x);
	}
	
	public Long getIdBlad() {
		return idBlad;
	}

	public void setIdBlad(Long idBlad) {
		this.idBlad = idBlad;
	}

	public Long getIdUzytkownik() {
		return idUzytkownik;
	}

	public void setIdUzytkownik(Long idUzytkownik) {
		this.idUzytkownik = idUzytkownik;
	}

	public String getTemat() {
		return temat;
	}

	public void setTemat(String temat) {
		this.temat = temat;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public LocalDateTime getDataZgloszenia() {
		return dataZgloszenia;
	}

	public void setDataZgloszenia(LocalDateTime dataZgloszenia) {
		this.dataZgloszenia = dataZgloszenia;
	}

	public boolean isRozpatrzono() {
		return rozpatrzono;
	}

	public void setRozpatrzono(boolean rozpatrzono) {
		this.rozpatrzono = rozpatrzono;
	}
}