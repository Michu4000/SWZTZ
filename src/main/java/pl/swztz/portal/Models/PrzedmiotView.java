package pl.swztz.portal.Models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;

@Entity
@Table(name="przedmiotView")
public class PrzedmiotView implements Serializable{
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long id;
	@Column(name="nazwa_przedmiot")
	private String nazwaPrzedmiot;
	@Column(name="nazwa_instytut")
	private String nazwaInstytut;

	public PrzedmiotView(String nazwaPrzedmiot, String nazwaInstytut) {
		this.nazwaPrzedmiot = nazwaPrzedmiot;
		this.nazwaInstytut = nazwaInstytut;
	}
	
	private PrzedmiotView() {}
	
	public static String[] getFieldNames() {
		String [] s = {"nazwaPrzedmiot", "nazwaInstytut"};
		return s;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNazwaPrzedmiot() {
		return nazwaPrzedmiot;
	}
	
	public void setNazwaPrzedmiot(String nazwaPrzedmiot) {
		this.nazwaPrzedmiot = nazwaPrzedmiot;
	}
	
	public String getNazwaInstytut() {
		return nazwaInstytut;
	}
	
	public void setNazwaInstytut(String nazwaInstytut) {
		this.nazwaInstytut = nazwaInstytut;
	}
}