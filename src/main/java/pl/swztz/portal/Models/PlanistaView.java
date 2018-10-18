package pl.swztz.portal.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;

@Entity
@Table(name="planista_view")
public class PlanistaView implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long id;
	@Column(name="nazwaWydzial")
	private String nazwaWydzial;
	@Column(name="PESEL")
	private Long PESEL;
	@Column(name="imie")
	private String imie;
	@Column(name="nazwisko")
	private String nazwisko;
	
	public PlanistaView(String nazwaWydzial, Long PESEL, String imie, String nazwisko) {
		this.nazwaWydzial = nazwaWydzial;
		this.PESEL = PESEL;
		this.imie = imie;
		this.nazwisko = nazwisko;
	}

	private PlanistaView(){}

	public static String[] getFieldNames() {
		String[] s = {"nazwaWydzial", "PESEL", "imie", "nazwisko"};
		return s;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazwaWydzial() {
		return nazwaWydzial;
	}

	public void setWydzial(String nazwaWydzial) {
		this.nazwaWydzial = nazwaWydzial;
	}

	public Long getPESEL() {
		return PESEL;
	}

	public void setPESEL(Long PESEL) {
		this.PESEL = PESEL;
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
}