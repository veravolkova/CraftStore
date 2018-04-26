package fi.haagahelia.Craftstore;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import fi.haagahelia.Craftstore.domain.Category;
import fi.haagahelia.Craftstore.domain.User;
import fi.haagahelia.Craftstore.domain.Product;
import fi.haagahelia.Craftstore.domain.ProductRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository repository;

	@Test
	public void findByTitleShouldReturnProduct() {
		List<Product> products = repository.findByTitle("Summer beauties");
		assertThat(products).hasSize(1);
		assertThat(products.get(0).getTitle()).isEqualTo("Summer beauties");
	}

	@Test
	public void createNewProduct() {
		Product product = new Product("Lovely pink",
				new User("guest", "$2a$04$rvIkoO9x1SHNqFq3iDgP/emh26l/la2V4JUY7jtIKoez5ztRFYv7O", "USER"),
				"Lovely socks for a little princess", 23.00, new Category("Baby"));
		repository.save(product);
		assertThat(product.getId()).isNotNull();
	}

}