package fi.haagahelia.Craftstore;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import fi.haagahelia.Craftstore.domain.Ad;
import fi.haagahelia.Craftstore.domain.AdRepository;
import fi.haagahelia.Craftstore.domain.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AdRepositoryTest {

	@Autowired
	private AdRepository repository;

	@Test
	public void findByTitleShouldReturnAd() {
		List<Ad> ads = repository.findByTitle("Looking for makers");
		assertThat(ads).hasSize(1);
		assertThat(ads.get(0).getTitle()).isEqualTo("Looking for makers");
	}

	@Test
	public void createNewAd() {
		Ad ad = new Ad("Family-look socks",
				new User("gold", "$2a$04$rvIkoO9x1SHNqFq3iDgP/emh26l/la2V4JUY7jtIKoez5ztRFYv7O", "GOLD"),
				"Can anyone knit it?");
		repository.save(ad);
		assertThat(ad.getId()).isNotNull();
	}

}