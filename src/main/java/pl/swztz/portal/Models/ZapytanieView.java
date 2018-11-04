package pl.swztz.portal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;

@Entity
@Table(name="zapytanieView")
public class ZapytanieView implements Serializable{
	
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long id;
	@Column(name="student")
	private String student;
	@Column(name="prowadzacy")
	private String prowadzacy;
	@Column(name="trescZapytanie")
	private String trescZapytanie;
	@Column(name="decyzja")
	private boolean decyzja;
	
	public ZapytanieView(String student, String prowadzacy, String trescZapytanie, boolean decyzja) {
		this.student = student;
		this.prowadzacy = prowadzacy;
		this.trescZapytanie = trescZapytanie;
		this.decyzja = decyzja;
	}
	
	private ZapytanieView(){}
	
	public static String[] getFieldNames() {
		String[] s = {"student", "prowadzacy","trescZapytania", "decyzja"};
		return s;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStudent() {
		return student;
	}
	
	public void setStudent(String student) {
		this.student = student;
	}
	public String getProwadzacy() {
		return prowadzacy;
	}
	
	public void setProwadzacy(String prowadzacy) {
		this.prowadzacy = prowadzacy;
	}
	public String getTrescZapytania() {
		return trescZapytanie;
	}
	
	public void setTrescZapytania(String trescZapytanie) {
		this.trescZapytanie = trescZapytanie;
	}
	public boolean getDecyzja() {
		return decyzja;
	}
	
	public void setDecyzja(boolean decyzja) {
		this.decyzja = decyzja;
	}
}