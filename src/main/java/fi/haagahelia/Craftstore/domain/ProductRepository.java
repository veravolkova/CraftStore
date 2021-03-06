package fi.haagahelia.Craftstore.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

	List<Product> findByTitle(String string);		
	
    // method to find products by user
	List<Product> findByUser(User userName);
}