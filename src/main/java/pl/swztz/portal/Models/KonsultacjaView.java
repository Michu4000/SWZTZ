package pl.swztz.portal.Models;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="konsultacjaView")
public class KonsultacjaView implements Serializable{
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
	@Column(name="prowadzacy")
	private String prowadzacy;
	@Column(name="komentarz")
	private String komentarz;
	
	public KonsultacjaView(LocalDate data, Long nrBloku, String sala, String prowadzacy, String komentarz) {
		this.data = data;
		this.nrBloku = nrBloku;
		this.sala = sala;
		this.prowadzacy = prowadzacy;
		this.komentarz = komentarz;
	}
	
	private KonsultacjaView() {}
	
	public static String[] getFieldNames() {
		String[] s = {"data", "nrBloku", "sala", "prowadzacy", "komentarz"};
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
	
	public String getSala() {
		return sala;
	}
	
	public void setSala(String sala) {
		this.sala = sala;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate date) {
		this.data = date;
	}
	
	public Long getNrBloku() {
		return nrBloku;
	}
	
	public void setNrBloku(Long nrBloku) {
		this.nrBloku = nrBloku;
	}
	
	public String getKomentarz() {
		return komentarz;
	}
	
	public void setKomentarz(String kom) {
		this.komentarz = kom;
	}
}