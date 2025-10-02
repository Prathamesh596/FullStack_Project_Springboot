package com.bullet.product;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Product Service Rest API Documentation",
                description = "Product service rest api",
                version = "v1",
                contact = @Contact(
                        name = "Prathamesh",
                        email = "prathaam19@gmail.com"
                )
        ),
        externalDocs = @ExternalDocumentation(   // ✅ moved here
                description = "SharePoint URL Product Service API",
                url = "https://docs.oracle.com/javase/8/docs/api/"
        )
)
@SpringBootApplication
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
