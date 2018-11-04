package pl.swztz.portal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="inspekcja_view")
public class InspekcjaView implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long id;
	@Column(name="data")
	private LocalDate data;
	@Column(name="nrBloku")
	private Long nrBloku;
	@Column(name="sala")
	private String sala;
	@Column(name="nazwaPrzedmiot")
	private String nazwaPrzedmiot;
	@Column(name="typZajec")
	private String typZajec;
	@Column(name="prowadzacy")
	private String prowadzacy;
	@Column(name="grupy")
	private String grupy;
	@Column(name="dyrektorInstytutu")
	private String dyrektorInstytutu;
	@Column(name="komentarzInspekcja")
	private String komentarzInspekcja;
	
	public InspekcjaView(LocalDate data, Long nrBloku, String sala, String nazwaPrzedmiot, String typZajec,
			String prowadzacy, String grupy, String dyrektorInstytutu, String komentarzInspekcja) {
		this.data = data;
		this.nrBloku = nrBloku;
		this.sala = sala;
		this.nazwaPrzedmiot = nazwaPrzedmiot;
		this.typZajec = typZajec;
		this.prowadzacy = prowadzacy;
		this.grupy = grupy;
		this.dyrektorInstytutu = dyrektorInstytutu;
		this.komentarzInspekcja = komentarzInspekcja;
	}

	private InspekcjaView(){}

	public static String[] getFieldNames() {
		String[] s = {"data", "nrBloku", "sala", "nazwaPrzedmiot", "typZajec", "prowadzacy", "grupy", "dyrektorInstytutu", "komentarzInspekcja"};
		return s;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public String getNazwaPrzedmiot() {
		return nazwaPrzedmiot;
	}

	public void setNazwaPrzedmiot(String nazwaPrzedmiot) {
		this.nazwaPrzedmiot = nazwaPrzedmiot;
	}

	public String getTypZajec() {
		return typZajec;
	}

	public void setTypZajec(String typZajec) {
		this.typZajec = typZajec;
	}

	public String getProwadzacy() {
		return prowadzacy;
	}

	public void setProwadzacy(String prowadzacy) {
		this.prowadzacy = prowadzacy;
	}
	
	public String getGrupy() {
		return grupy;
	}

	public void setGrupy(String grupy) {
		this.grupy = grupy;
	}

	public String getDyrektorInstytutu() {
		return dyrektorInstytutu;
	}

	public void setDyrektorInstytutu(String dyrektorInstytutu) {
		this.dyrektorInstytutu = dyrektorInstytutu;
	}

	public String getKomentarzInspekcja() {
		return komentarzInspekcja;
	}

	public void setKomentarzInspekcja(String komentarzInspekcja) {
		this.komentarzInspekcja = komentarzInspekcja;
	}
}