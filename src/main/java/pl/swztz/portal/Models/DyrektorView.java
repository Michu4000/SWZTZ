package pl.swztz.portal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;

@Entity
@Table(name="dyrektor_view")
public class DyrektorView implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long id;
	@Column(name="nazwaInstytut")
	private String nazwaInstytut;
	@Column(name="PESEL")
	private Long PESEL;
	@Column(name="imie")
	private String imie;
	@Column(name="nazwisko")
	private String nazwisko;
	@Column(name="pokoj")
	private String pokoj;
	
	public DyrektorView(String nazwaInstytut, Long PESEL, String imie, String nazwisko, String pokoj) {
		this.nazwaInstytut = nazwaInstytut;
		this.PESEL = PESEL;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.pokoj = pokoj;
	}

	private DyrektorView(){}

	public static String[] getFieldNames() {
		String[] s = {"nazwaInstytut", "PESEL", "imie", "nazwisko", "pokoj"};
		return s;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazwaInstytut() {
		return nazwaInstytut;
	}

	public void setInstytut(String nazwaInstytut) {
		this.nazwaInstytut = nazwaInstytut;
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

	public String getPokoj() {
		return pokoj;
	}

	public void setPokoj(String pokoj) {
		this.pokoj = pokoj;
	}
}