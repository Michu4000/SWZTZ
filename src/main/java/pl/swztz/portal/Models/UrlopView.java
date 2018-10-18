package pl.swztz.portal.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="Urlop_View")
public class UrlopView implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long id;
	@Column (name="prowadzacy")
	private String prowadzacy;
	@Column(name="dataRozpoczecia")
	private LocalDate dataRozpoczecia;
	@Column(name="dataZakonczenia")
	private LocalDate dataZakonczenia;
	@Column(name="powod")
	private String powod;

	public UrlopView(String prowadzacy, LocalDate dataRozpoczecia, LocalDate dataZakonczenia, String powod) {
		this.prowadzacy = prowadzacy;
		this.dataRozpoczecia = dataRozpoczecia;
		this.dataZakonczenia = dataZakonczenia;
		this.powod = powod;
	}

	private UrlopView(){}
	
	public static String[] getFieldNames() {
		String[] s = {"prowadzacy", "dataRozpoczecia", "dataZakonczenia", "powod"};
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
	
	public void setProwdzacy(String prowadzacy) {
		this.prowadzacy =  prowadzacy;
	}
	public LocalDate getDataRozpoczecia() {
		return dataRozpoczecia;
	}

	public void setDataRozpoczecia(LocalDate dataRozpoczecia) {
		this.dataRozpoczecia = dataRozpoczecia;
	}

	public LocalDate getDataZakonczenia() {
		return dataZakonczenia;
	}

	public void setDataZakonczenia(LocalDate localDateTime) {
		this.dataZakonczenia = localDateTime;
	}
	public void setPowod (String powod) {
		this.powod = powod;
	}
	public String getPowod () {
		return powod;
	}
}