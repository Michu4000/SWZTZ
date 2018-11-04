package pl.swztz.portal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;

@Entity
@Table(name="sala_view")
public class SalaView implements Serializable {
	@Id
	@GenericGenerator(name = "native_generator", strategy = "native")
	@GeneratedValue(generator = "native_generator")
	private Long id;
	@Column(name="nrSala")
	private String nrSala;
	@Column(name="typSala")
	private String typSala;
	@Column(name="budynek")
	private String budynek;
	@Column(name="iloscMiejsc")
	private int iloscMiejsc;

	public SalaView(String nrSala, String typSala, String budynek, int iloscMiejsc) {
		this.nrSala = nrSala;
		this.typSala = typSala;
		this.budynek = budynek;
		this.iloscMiejsc = iloscMiejsc;
	}

	private SalaView(){}
	
	public static String[] getFieldNames() {
		String[] s = {"nrSala", "budynek", "typSala", "iloscMiejsc" };
		return s;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNrSala() {
		return nrSala;
	}

	public void setNrSala(String nrSala) {
		this.nrSala = nrSala;
	}

	public String getTypSala() {
		return typSala;
	}

	public void setTypSala(String typSala) {
		this.typSala = typSala;
	}

	public String getBudynek() {
		return budynek;
	}

	public void setBudynek(String budynek) {
		this.budynek = budynek;
	}

	public int getIloscMiejsc() {
		return iloscMiejsc;
	}

	public void setIloscMiejsc(int iloscMiejsc) {
		this.iloscMiejsc = iloscMiejsc;
	}
}