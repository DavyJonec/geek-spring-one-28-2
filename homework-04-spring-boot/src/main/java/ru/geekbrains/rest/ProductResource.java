package ru.geekbrains.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.controller.NotFoundException;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.service.ProductService;

import java.sql.SQLException;
import java.util.Optional;

@RequestMapping("/rest/v1/product")
@RestController
public class ProductResource {

    private ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    private Page<ProductDto> findAll(@RequestParam Optional<String> titleFilter,
                                     @RequestParam Optional<Integer> page,
                                     @RequestParam Optional<Integer> size,
                                     @RequestParam Optional<String> sortField) {

        String titleFilterValue = titleFilter
                .filter(s -> !s.isBlank())
                .orElse(null);
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(4);
        String sortFieldValue = sortField
                .filter(s -> !s.isBlank())
                .orElse("id");
        return productService.findProductsByFilter(
                titleFilterValue,
                pageValue,
                sizeValue,
                sortFieldValue);
    }

    @GetMapping("/{id}/id")
    public ProductDto findById(@PathVariable long id) {
        return productService.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto product) {
        if(product.getId() != null) {
            throw new IllegalArgumentException("Created product shouldn't have id");
        }
        return  productService.save(product);
    }

    @PutMapping
    public ProductDto update(@RequestBody ProductDto product) {
        if(product.getId() == null) {
            throw new IllegalArgumentException("Created product shouldn't have id");
        }
        return  productService.save(product);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public String notFoundException(NotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public String IllegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public String sqlException(SQLException ex){
        return ex.getMessage();
    }
}
