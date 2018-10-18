package pl.swztz.portal.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;

@Entity
@Table(name="wydzialView")
public class WydzialView implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long id;
	@Column(name="nazwa_wydzial")
	private String nazwaWydzial;
	
	public WydzialView(String nazwaWydzial) {
		this.nazwaWydzial = nazwaWydzial;
	}
	
	private WydzialView() {}
	
	public static String[] getFieldNames() {
		String [] s = { "nazwaWydzial" };
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
}