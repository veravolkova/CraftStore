package fi.haagahelia.Craftstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.Craftstore.domain.Product;
import fi.haagahelia.Craftstore.domain.ProductRepository;
import fi.haagahelia.Craftstore.domain.Ad;
import fi.haagahelia.Craftstore.domain.AdRepository;
import fi.haagahelia.Craftstore.domain.Category;
import fi.haagahelia.Craftstore.domain.CategoryRepository;
import fi.haagahelia.Craftstore.domain.User;
import fi.haagahelia.Craftstore.domain.UserRepository;

@SpringBootApplication
public class CraftstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CraftstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProductRepository repository, CategoryRepository crepository,
			UserRepository urepository, AdRepository arepository) {
		return (args) -> {
   
//creating categories
			Category c1 = new Category("Men");
			Category c2 = new Category("Women");
			Category c3 = new Category("Kids");

			crepository.save(c1);
			crepository.save(c2);
			crepository.save(c3);
            
//creating users
			User user1 = new User("guest", "$2a$04$rvIkoO9x1SHNqFq3iDgP/emh26l/la2V4JUY7jtIKoez5ztRFYv7O", "USER");
			User user2 = new User("admin", "$2a$04$BwdM51o.O2QzS4DZixH8MOrHT6pVCnUwN6PVPYAGZrbg1R1qCwPxK", "ADMIN");
			User user3 = new User("silver", "$2a$04$I33SP2vRrTdK3JDaZznUKuJMx039UyoiPVyftt6NfoUFOcIjvkl0O", "SILVER");
			User user4 = new User("gold", "$2a$04$ItIkR1GIUOkGNrkVKU6rauCHp.6FGzpv2U5ih5Iw57EUkLTjI1glC", "GOLD");

			urepository.save(user1);
			urepository.save(user2);
			urepository.save(user3);
			urepository.save(user4);

//creating products
			Product p1 = new Product("Cool men socks", user4, "warm, nice, and bright!", 20.15, c1);
			Product p2 = new Product("Summer beauties", user3, "light, tender and beautiful", 30.5, c2);

			repository.save(p1);
			repository.save(p2);

//creating ads
			Ad a1 = new Ad("Looking for makers", user4, "I want to order 5 pairs of socks");
			Ad a2 = new Ad("Wanna find a team", user4, "I have an interesting project, need a team of 3");

			arepository.save(a1);
			arepository.save(a2);

		};
	}
}
