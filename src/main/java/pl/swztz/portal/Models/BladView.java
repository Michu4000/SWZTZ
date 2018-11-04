package pl.swztz.portal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="bladView")
public class BladView implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long id;
	@Column(name="temat")
	private String temat;
	@Column(name="opis")
	private String opis;
	@Column(name="dataZgloszenia")
	private LocalDateTime dataZgloszenia;
	@Column(name="rozpatrzono")
	private boolean rozpatrzono;
	@Column(name="zglaszajacy")
	private String zglaszajacy;

	public BladView(String temat, String opis, LocalDateTime dataZgloszenia, boolean rozpatrzono, String zglaszajacy) {
		this.temat = temat;
		this.opis = opis;
		this.dataZgloszenia = dataZgloszenia;
		this.rozpatrzono = rozpatrzono;
		this.zglaszajacy = zglaszajacy;
	}

	private BladView(){}
	
	public static String[] getFieldNames() {
		String[] s = {"temat", "opis", "dataZgloszenia", "rozpatrzono", "zglaszajacy"};
		return s;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTemat() {
		return temat;
	}

	public void setTemat(String temat) {
		this.temat = temat;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public LocalDateTime getDataZgloszenia() {
		return dataZgloszenia;
	}

	public void setDataZgloszenia(LocalDateTime dataZgloszenia) {
		this.dataZgloszenia = dataZgloszenia;
	}

	public boolean isRozpatrzono() {
		return rozpatrzono;
	}

	public void setRozpatrzono(boolean rozpatrzono) {
		this.rozpatrzono = rozpatrzono;
	}

	public String getZglaszajacy() {
		return zglaszajacy;
	}

	public void setZglaszajacy(String zglaszajacy) {
		this.zglaszajacy = zglaszajacy;
	}
}