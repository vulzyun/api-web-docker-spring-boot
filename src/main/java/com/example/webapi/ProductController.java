package com.example.webapi;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product) {
        Product savedProduct = repository.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{reference}")
    public ResponseEntity<Product> getProductById(@PathVariable String reference) {
        return repository.findById(Integer.valueOf(reference))
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/by-price")
    public List<Product> getProductsByPrice(@RequestParam int maxPrice) {
        return repository.findByPriceLessThan(maxPrice);
    }

    @DeleteMapping("/{reference}")
    public ResponseEntity<Void> deleteProductById(@PathVariable String reference) {
        if (repository.existsById(Integer.valueOf(reference))) {
            repository.deleteById(Integer.valueOf(reference));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
