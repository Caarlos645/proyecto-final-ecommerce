package com.proyecto.ecommerce.backend.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.proyecto.ecommerce.backend.application.CategoryService;
import com.proyecto.ecommerce.backend.application.OrderService;
import com.proyecto.ecommerce.backend.application.ProductService;
import com.proyecto.ecommerce.backend.application.RegistrationService;
import com.proyecto.ecommerce.backend.application.UserService;
import com.proyecto.ecommerce.backend.domain.port.ICategoryRepository;
import com.proyecto.ecommerce.backend.domain.port.IOrderRepository;
import com.proyecto.ecommerce.backend.domain.port.IProductRepository;
import com.proyecto.ecommerce.backend.domain.port.IUserRepository;

@Configuration
public class BeanConfiguration {
    
    @Bean
    public UserService userService(IUserRepository iUserRepository) {
        return new UserService(iUserRepository);
    }
    
    @Bean 
    public CategoryService categoryService(ICategoryRepository categoryRepository) {
        return new CategoryService(categoryRepository);
    }
    
    @Bean
    public ProductService productService(IProductRepository iProductRepository) {
        return new ProductService(iProductRepository);
    }
    
    @Bean
    public OrderService orderService(IOrderRepository iOrderRepository) {
        return new OrderService(iOrderRepository);
    }
    
    @Bean
    public RegistrationService registrationService (IUserRepository iUserRepository) {
    	return new RegistrationService(iUserRepository);
    }
}