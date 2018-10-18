package pl.swztz.portal.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.Repositories.GrupaRepository;

@Entity
@Table(name="grupa")
public class Grupa implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long idGrupa;
	@Column(name="nazwa")
	private String nazwa;
	@Column(name="kierunek")
	private String kierunek;
	@Column(name="rok")
	private Long rok;
	@Column(name="idStarosta")
	private Long idStarosta;
	
	public Grupa(String nazwa, String kierunek, Long rok, Long idStarosta) {
		this.nazwa = nazwa;
		this.kierunek = kierunek;
		this.rok = rok;
		this.idStarosta = idStarosta;
	}

	private Grupa(){}

	public static void delById(JpaRepository repo, Long x) {
		((GrupaRepository) repo).delById(x);
	}
	
	public Long getId() {
		return idGrupa;
	}

	public void setId(Long id) {
		this.idGrupa = id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	public String getKierunek() {
		return kierunek;
	}

	public void setKierunek(String kierunek) {
		this.kierunek = kierunek;
	}

	public Long getRok() {
		return rok;
	}

	public void setRok(Long rok) {
		this.rok = rok;
	}
	
	public Long getIdStarosta()
	{
		return idStarosta;
	}
	
	public void setIdStarosta(Long idStarosta)
	{
		this.idStarosta = idStarosta;
	}
}