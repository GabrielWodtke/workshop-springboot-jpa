package com.educandoweb.course.config;

import com.educandoweb.course.entities.*;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

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

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "123456", "988888888", "Maria Brown", "maria@gmail.com");
        User u2 = new User(null, "123456", "977777777", "Alex Green", "alex@gmail.com");

        Order o1 = new Order(null,  u1, OrderStatus.PAID,Instant.parse("2019-06-20T19:53:07Z"));
        Order o2 = new Order(null,  u2, OrderStatus.WAITING_PAYMENT,Instant.parse("2019-07-21T03:42:10Z"));
        Order o3 = new Order(null,  u1, OrderStatus.WAITING_PAYMENT ,Instant.parse("2019-07-22T15:21:22Z"));

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        Product p1 = new Product(" ", 90.5, "Lorem ipsum dolor sit amet, consectetur.",
                "The Lord of the Rings", null);
        Product p2 = new Product(" ", 2190.0, "Nulla eu imperdiet purus. Maecenas ante.",
                "Smart TV" , null);
        Product p3 = new Product(" ", 1250.0, "Nam eleifend maximus tortor, at mollis.",
                "Macbook Pro", null);
        Product p4 = new Product(" ", 1200.0, "Donec aliquet odio ac rhoncus cursus.",
                "PC Gamer", null);
        Product p5 = new Product(" ", 100.99, "Cras fringilla convallis sem vel faucibus.",
                "Rails for Dummies", null);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4 ,p5));
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

        p1.getCategories().add(cat2);
        p2.getCategories().add(cat1);
        p2.getCategories().add(cat3);
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat3);
        p5.getCategories().add(cat2);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        OrdemItem oi1 = new OrdemItem(o1, p1, 2, p1.getPrice());
        OrdemItem oi2 = new OrdemItem(o1, p3, 1, p3.getPrice());
        OrdemItem oi3 = new OrdemItem(o2, p3, 2, p3.getPrice());
        OrdemItem oi4 = new OrdemItem(o3, p5, 2, p5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
    }
}
