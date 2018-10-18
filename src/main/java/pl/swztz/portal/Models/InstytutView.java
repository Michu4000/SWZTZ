package pl.swztz.portal.Models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Instytut_View")
public class InstytutView  implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long id;
	@Column(name="nazwa_instytut")
	private String nazwaInstytut;
	@Column (name="nazwa_wydzial")
	private String nazwaWydzial;
	
	public InstytutView(String nazwaInstytut, String nazwaWydzial) {
		this.nazwaInstytut = nazwaInstytut;
		this.nazwaWydzial = nazwaWydzial;
	}
	
	private InstytutView(){}
	
	public static String[] getFieldNames() {
		String[] s = {"nazwaInstytut", "nazwaWydzial"};
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
	
	public void setNazwaWydzial(String nazwaWydzial) {
		this.nazwaWydzial = nazwaWydzial;
	}
	
	public String getNazwaInstytut() {
		return nazwaInstytut;
	}
	
	public void setNazwaInstytut(String nazwaInstytut) {
		this.nazwaInstytut = nazwaInstytut;
	}
}