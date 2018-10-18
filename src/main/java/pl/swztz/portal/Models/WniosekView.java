package pl.swztz.portal.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;

@Entity
@Table(name="wniosekView")
public class WniosekView implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long id;
	@Column(name="prowadzacy")
	private String prowadzacy;
	@Column(name="dyrektor")
	private String dyrektor;
	@Column(name="trescWniosek")
	private String trescWniosek;
	@Column(name="decyzja")
	private boolean decyzja;
	@Column(name="zmienionoPlan")
	private boolean zmienionoPlan;
	
	public WniosekView(String prowadzacy, String dyrektor, String trescWniosek, boolean decyzja,
			boolean zmienionoPlan) {
		
		this.prowadzacy = prowadzacy;
		this.dyrektor = dyrektor;
		this.trescWniosek = trescWniosek;
		this.decyzja = decyzja;
		this.zmienionoPlan = zmienionoPlan;
	}
	
	public WniosekView() {}
	
	public static String[] getFieldNames()
	{
		String[] s = {"prowadzacy", "dyrektor", "trescWniosek", "decyzja", "zmienionoPlan"};
		return s;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProwadzacy() {
		return prowadzacy;
	}

	public void setProwadzacy(String prowadzacy) {
		this.prowadzacy = prowadzacy;
	}

	public String getDyrektor() {
		return dyrektor;
	}

	public void setDyrektor(String dyrektor) {
		this.dyrektor = dyrektor;
	}

	public String getTrescWniosek() {
		return trescWniosek;
	}

	public void setTrescWniosek(String trescWniosek) {
		this.trescWniosek = trescWniosek;
	}

	public boolean isDecyzja() {
		return decyzja;
	}

	public void setDecyzja(boolean decyzja) {
		this.decyzja = decyzja;
	}

	public boolean isZmienionoPlan() {
		return zmienionoPlan;
	}

	public void setZmienionoPlan(boolean zmienionoPlan) {
		this.zmienionoPlan = zmienionoPlan;
	}
}