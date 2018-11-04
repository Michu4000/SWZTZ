package pl.swztz.portal.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="wiadomosc")
public class Wiadomosc {

	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long idWiadomosc;
	@Column(name="idNadawca")
	private Long idNadawca;
	@Column(name="idOdbiorca")
	private Long idOdbiorca;
	@Column(name="temat")
	private String temat;
	@Column(name="tresc")
	private String tresc;
	@Column(name="dataWyslania")
	private LocalDateTime dataWyslania;
	
	public Wiadomosc(Long idNadawca,  Long idOdbiorca, String temat, String tresc, LocalDateTime dataWyslania) {
		this.idNadawca = idNadawca;
		this.idOdbiorca = idOdbiorca;
		this.temat = temat;
		this.tresc = tresc;
		this.dataWyslania = dataWyslania;
	}
	
	public Wiadomosc(){}
	
	public Long getIdWiadomosc() {
		return idWiadomosc;
	}
	
	public void setIdWiadomosc(Long idWiadomosc) {
		this.idWiadomosc = idWiadomosc;
	}
	
	public Long getIdNadawca() {
		return idNadawca;
	}
	
	public void setIdNadawca(Long idNadawca) {
		this.idNadawca = idNadawca;
	}
	
	public Long getIdOdbiorca() {
		return idOdbiorca;
	}
	
	public void setIdOdbiorca(Long idOdbiorca) {
		this.idOdbiorca = idOdbiorca;
	}
	
	public String getTemat() {
		return temat;
	}
	
	public void setTemat(String temat) {
		this.temat = temat;
	}
	
	public String getTresc() {
		return tresc;
	}
	
	public void setTresc(String tresc) {
		this.tresc = tresc;
	}
	
	public LocalDateTime getDataWyslania() {
		return dataWyslania;
	}
	
	public void setDataWyslania(LocalDateTime dataWyslania) {
		this.dataWyslania = dataWyslania;
	}
}