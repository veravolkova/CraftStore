package fi.haagahelia.Craftstore;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import fi.haagahelia.Craftstore.domain.Category;
import fi.haagahelia.Craftstore.domain.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository repository;

	@Test
	public void findByTitleShouldReturnUser() {
		List<Category> categories = repository.findByName("Men");
		assertThat(categories).hasSize(1);
		assertThat(categories.get(0).getName()).isEqualTo("Men");
	}

	@Test
	public void createNewCategory() {
		Category category = new Category("Baby");
		repository.save(category);
		assertThat(category.getId()).isNotNull();
	}
}
