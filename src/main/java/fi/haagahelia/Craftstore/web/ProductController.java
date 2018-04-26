package fi.haagahelia.Craftstore.web;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import fi.haagahelia.Craftstore.domain.Product;
import fi.haagahelia.Craftstore.domain.ProductRepository;
import fi.haagahelia.Craftstore.domain.User;
import fi.haagahelia.Craftstore.domain.UserRepository;
import fi.haagahelia.Craftstore.domain.Ad;
import fi.haagahelia.Craftstore.domain.AdRepository;
import fi.haagahelia.Craftstore.domain.CategoryRepository;

@Controller
public class ProductController {
	@Autowired
	ProductRepository repository;
	@Autowired
	private CategoryRepository crepository;
	@Autowired
	private AdRepository arepository;
	@Autowired
	private UserRepository urepository;

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/logout")
	public String logout() {
		return "logout";
	}

//Showing a list of products/ads
	@RequestMapping("/products") // address on the web
	public String products(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User userName = urepository.findByUsername(authentication.getName());

         // getting user role
		Collection<? extends GrantedAuthority> userRole = authentication.getAuthorities();
		SimpleGrantedAuthority s = (SimpleGrantedAuthority) userRole.toArray()[0];
		String srole = s.getAuthority();

		if ((srole.equals("SILVER")) || (srole.equals("GOLD"))) {

			model.addAttribute("products", repository.findByUser(userName));

		}

		else {
			model.addAttribute("products", repository.findAll());
		}

		return "product"; // name of html page
	}


	@RequestMapping("/ads") // address on the web
	public String ads(Model model) {
		model.addAttribute("ads", arepository.findAll());
		return "ad"; // name of html page
	}

//REST API
	@RequestMapping(value = "/productsrest", method = RequestMethod.GET)
	public @ResponseBody List<Product> productListRest() {
		return (List<Product>) repository.findAll();
	}

	@RequestMapping(value = "/adsrest", method = RequestMethod.GET)
	public @ResponseBody List<Ad> adListRest() {
		return (List<Ad>) arepository.findAll();
	}

//Finding product/ad by id
	@RequestMapping(value = "/productsrest/{id}", method = RequestMethod.GET)
	public @ResponseBody Product findProductRest(@PathVariable("id") Long productid) {
		return repository.findOne(productid);
	}

	@RequestMapping(value = "/adsrest/{id}", method = RequestMethod.GET)
	public @ResponseBody Ad findAdRest(@PathVariable("id") Long adid) {
		return arepository.findOne(adid);
	}

//Deleting ad/product
	@PreAuthorize("hasAnyAuthority('ADMIN', 'GOLD', 'SILVER')")
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long productid, Model model) {
		repository.delete(productid);
		return "redirect:../products"; // not html
	}

	@PreAuthorize("hasAnyAuthority('ADMIN', 'GOLD')")
	@RequestMapping("/deletead/{id}")
	public String deleteAd(@PathVariable("id") Long adid, Model model) {
		arepository.delete(adid);
		return "redirect:../ads"; // not html
	}

//Adding ad/product + setting limits
	@PreAuthorize("hasAnyAuthority('GOLD', 'SILVER')")
	@RequestMapping("/add")
	public String addProduct(Model model) {

		// getting username
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User userName = urepository.findByUsername(authentication.getName());

		// getting user role
		Collection<? extends GrantedAuthority> userRole = authentication.getAuthorities();
		SimpleGrantedAuthority s = (SimpleGrantedAuthority) userRole.toArray()[0];
		String srole = s.getAuthority();

		// listing products by user
		List<Product> productList = repository.findByUser(userName);

		boolean silverFull = true;
		if (productList.size() > 2) {
			silverFull = false;
		}

		boolean goldFull = true;
		if (productList.size() > 4) {
			goldFull = false;
		}

		if ((silverFull) & (srole.equals("SILVER"))) {
			model.addAttribute("products", repository.findByUser(userName));
			model.addAttribute("product", new Product());
			model.addAttribute("categories", crepository.findAll());			
			model.addAttribute("user", userName.getId());
			System.out.println("User:" + userName.getId());
		}

		else if ((goldFull) & (srole.equals("GOLD"))) {
			model.addAttribute("products", repository.findByUser(userName));
			model.addAttribute("product", new Product());
			model.addAttribute("categories", crepository.findAll()); // categories when looping
			model.addAttribute("user", userName.getId());
			System.out.println("User:" + userName.getId());
		}

		else {
			model.addAttribute("message", "You have exceeded the limit");
			return "limit";
		}
		//
		return "addproduct";

	}

	@PreAuthorize("hasAuthority('GOLD')")
	@RequestMapping("/addad")
	public String add(Model model) {

		model.addAttribute("ad", new Ad());

		return "addads";
	}

//Saving products/ads
	@RequestMapping("/savead")
	public String save(Ad ad) {
		arepository.save(ad);
		return "redirect:ads";
	}

	@RequestMapping("/save")
	public String saveProduct(Product product) {
		repository.save(product);
		return "redirect:products";
	}

//Updating products/ads
	@PreAuthorize("hasAnyAuthority('ADMIN', 'GOLD', 'SILVER')")
	@RequestMapping("/update/{id}") // web address
	public String updateProduct(@PathVariable("id") Long productid, Product product, Model model) {
		model.addAttribute("product", repository.findOne(productid));
		model.addAttribute("categories", crepository.findAll());
		return "updateproduct"; // my html
	}

}