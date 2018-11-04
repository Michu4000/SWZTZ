package pl.swztz.windows;

import org.springframework.data.jpa.repository.JpaRepository;

public class OknoElementuOdpowiedzAnkiety extends OknoElementu {

	public OknoElementuOdpowiedzAnkiety(String nazwa, JpaRepository repo) {
		super(nazwa, repo);

	}

	@Override
	public void setElement(Long id) {
		
	}
}
