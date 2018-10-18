package pl.swztz.portal.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.Repositories.WydzialRepository;

@Entity
@Table(name="wydzial")
public class Wydzial implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long idWydzial;
	@Column(name="nazwa_wydzial")
	private String nazwaWydzial;
	
	public Wydzial(String nazwaWydzial) {
		this.nazwaWydzial = nazwaWydzial;
	}
	
	private Wydzial() {}
	
	public static void delById(JpaRepository repo, Long x) {
		((WydzialRepository) repo).delById(x);
	}
	
	public Long getIdWydzial() {
		return idWydzial;
	}
	
	public void setIdWydzial(Long idWydzial) {
		this.idWydzial = idWydzial;
	}
	
	public String getNazwaWydzial() {
		return nazwaWydzial;
	}

	public void setNazwaWydzial(String nazwaWydzial) {
		this.nazwaWydzial = nazwaWydzial;
	}
}