package fi.haagahelia.Craftstore;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import fi.haagahelia.Craftstore.domain.User;
import fi.haagahelia.Craftstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository repository;

	@Test
	public void findByUserShouldReturnUser() {
		User users = repository.findByUsername("silver");

		assertThat(users.getUsername()).isEqualTo("silver");
	}

	@Test
	public void createNewUser() {
		User user = new User("vera", "$2a$04$kAF0N94aH/LIYU6T4qql3.rvvt6aPGZLksjjHqyf23Q3nGA9xbl/K", "CUSTOMER");
		repository.save(user);
		assertThat(user.getId()).isNotNull();
	}
}
