package pl.swztz.portal.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.repositories.PrzedmiotRepository;

@Entity
@Table(name="przedmiot")
public class Przedmiot implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long idPrzedmiot;
	@Column(name="nazwa_przedmiot")
	private String nazwaPrzedmiot;
	@Column(name="id_instytut")
	private Long idInstytut;
	
	public Przedmiot(String nazwaPrzedmiot, Long idInstytut) {
		this.nazwaPrzedmiot = nazwaPrzedmiot;
		this.idInstytut = idInstytut;	
	}
	
	private Przedmiot() {}
	
	public static void delById(JpaRepository repo, Long x) {
		((PrzedmiotRepository) repo).delById(x);
	}
	
	public Long getIdPrzedmiot() {
		return idPrzedmiot;
	}

	public void setIdPrzedmiot(Long idPrzedmiot) {
		this.idPrzedmiot = idPrzedmiot;
	}

	public Long getIdInstytut() {
		return idInstytut;
	}

	public void setIdInstytut(Long idInstytut) {
		this.idInstytut = idInstytut;
	}

	public String getNazwaPrzedmiot() {
		return nazwaPrzedmiot;
	}

	public void setNazwaPrzedmiot(String nazwaPrzedmiot) {
		this.nazwaPrzedmiot = nazwaPrzedmiot;
	}
}