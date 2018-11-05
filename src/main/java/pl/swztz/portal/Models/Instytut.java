package pl.swztz.portal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.swztz.portal.repositories.InstytutRepository;
import java.io.Serializable;

@Entity
@Table(name="instytut")
public class Instytut implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long idInstytut;
	@Column(name="nazwaInstytut")	
	private String nazwaInstytut;
	@Column(name="idWydzial")
	private Long idWydzial;
	
	public Instytut(String nazwaInstytut, Long idWydzial) {
		this.nazwaInstytut = nazwaInstytut;
		this.idWydzial = idWydzial;
	}

	private Instytut(){}

	public static void delById(JpaRepository repo, Long x) {
		((InstytutRepository) repo).delById(x);
	}
	
	public Long getIdWydzial() {
		return idWydzial;
	}

	public void setIdWydzial(Long idWydzial) {
		this.idWydzial = idWydzial;
	}

	public String getNazwaInstytut() {
		return nazwaInstytut;
	}

	public void setNazwaInstytut(String nazwaInstytut) {
		this.nazwaInstytut = nazwaInstytut;
	}
}