package pl.swztz.portal.Models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="prowadzacy_view")
public class ProwadzacyView implements Serializable {
	
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long id;
	@Column(name="PESEL")
	private Long PESEL;
	@Column(name="imie")
	private String imie;
	@Column(name="nazwisko")
	private String nazwisko;
	@Column(name="stopien")
	private String stopien;
	@Column(name="pokoj")
	private String pokoj;

	public ProwadzacyView(Long PESEL, String stopien, String imie, String nazwisko, String pokoj) {
		this.PESEL = PESEL;
		this.stopien = stopien;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.pokoj = pokoj;
	}

	private ProwadzacyView(){}
	
	public static String[] getFieldNames() {
		String[] s = { "PESEL", "stopien", "imie", "nazwisko", "pokoj" };
		return s;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getStopien() {
		return stopien;
	}

	public void setStopien(String stopien) {
		this.stopien = stopien;
	}

	public Long getPESEL() {
		return PESEL;
	}

	public void setPESEL(Long PESEL) {
		this.PESEL = PESEL;
	}

	public String getPokoj() {
		return pokoj;
	}

	public void setPokoj(String pokoj) {
		this.pokoj = pokoj;
	}
}