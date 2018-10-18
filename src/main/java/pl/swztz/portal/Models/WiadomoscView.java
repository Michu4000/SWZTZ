package pl.swztz.portal.Models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="wiadomosc_view")
public class WiadomoscView {

	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long id;
	@Column(name="nadawca")
	private String nadawca;
	@Column(name="odbiorca")
	private String odbiorca;
	@Column(name="temat")
	private String temat;
	@Column(name="tresc")
	private String tresc;
	@Column(name="dataWyslania")
	private LocalDateTime dataWyslania;
	
	public WiadomoscView(String nadawca, String odbiorca, String temat, String tresc, LocalDateTime dataWyslania) {
		this.nadawca = nadawca;
		this.odbiorca = odbiorca;
		this.temat = temat;
		this.tresc = tresc;
		this.dataWyslania = dataWyslania;
	}
	
	private WiadomoscView() {
		
	}
	
	public static String[] getFieldNames() {
		String[] s = {"nadawca", "odbiorca", "temat", "tresc", "dataWyslania", };
		return s;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNadawca() {
		return nadawca;
	}

	public void setNadawca(String nadawca) {
		this.nadawca = nadawca;
	}
	
	public String getOdbiorca() {
		return odbiorca;
	}

	public void setOdbiorca(String odbiorca) {
		this.odbiorca = odbiorca;
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
