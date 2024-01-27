package com.educandoweb.course.config;


import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entity.Category;
import com.educandoweb.course.entity.Order;
import com.educandoweb.course.entity.Product;
import com.educandoweb.course.entity.User;
import com.educandoweb.course.entity.enums.OrderStatus;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null,"The Lod of the Rings", "Lorem ipsum dolor sit amet.", 90.5, "");
		Product p2 = new Product(null,"Guardian of the Galact", "Lorem ipsum dolor sit amet.", 80.5, "");
		Product p3 = new Product(null,"Where the weak have no place", "Lorem ipsum dolor sit amet.", 70.5, "");
		Product p4 = new Product(null,"Beekeeper", "Lorem ipsum dolor sit amet.", 60.5, "");
		Product p5 = new Product(null,"Fire against Fire", "Lorem ipsum dolor sit amet.", 50.5, "");
		
		categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "99 88441133", "123");
		User u2 = new User(null, "Pedro", "pedro@gmail.com", "99 66771133", "123");
		
		Order o1 = new Order(null, Instant.parse("2024-01-25T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2024-02-22T18:53:07Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2024-03-23T11:53:07Z"), OrderStatus.WAITING_PAYMENT, u1);
		
		
		
		
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
	}
	
	
}
