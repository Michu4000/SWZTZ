package pl.swztz.portal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;

@Entity
@Table(name="grupaView")
public class GrupaView implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long id;
	@Column(name="nazwa")
	private String nazwa;
	@Column(name="kierunek")
	private String kierunek;
	@Column(name="rok")
	private Long rok;
	@Column(name="starosta")
	private String starosta;
	
	public GrupaView(String nazwa, String kierunek, Long rok, String starosta) {
		this.nazwa = nazwa;
		this.kierunek = kierunek;
		this.rok = rok;
		this.starosta = starosta;
	}

	private GrupaView(){}
	
	public static String[] getFieldNames() {
		String[] s = {"nazwa", "kierunek", "rok", "starosta"};
		return s;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getStarosta() {
		return starosta;
	}

	public void setStarosta(String starosta) {
		this.starosta = starosta;
	}
}