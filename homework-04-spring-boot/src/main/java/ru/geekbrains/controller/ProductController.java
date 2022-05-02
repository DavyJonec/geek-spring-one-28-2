package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.service.ProductService;

import javax.validation.Valid;
import java.util.Optional;


@RequestMapping("/product")
@Controller
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productRepository) {
        this.productService = productRepository;
    }

    @GetMapping
    public String listPage(@RequestParam Optional<String> titleFilter,
                           @RequestParam Optional<Integer> page,
                           @RequestParam Optional<Integer> size,
                           @RequestParam Optional<String> sortField,
                           Model model) {

        String titleFilterValue = titleFilter
                .filter(s -> !s.isBlank())
                .orElse(null);
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(4);
        String sortFieldValue = sortField
                .filter(s -> !s.isBlank())
                .orElse("id");
        model.addAttribute("products", productService.findProductsByFilter(
                titleFilterValue,
                pageValue,
                sizeValue,
                sortFieldValue));
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productService.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found")));
        return "product_form";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("product", new ProductDto());
        return "product_form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("product") ProductDto product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productService.save(product);
        return "redirect:/product";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        productService.deleteById(id);
        return "redirect:/product";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public String notFoundExceptionHandler(Model model, NotFoundException ex) {
        model.addAttribute("message", ex.getMessage());
        return "not_found";
    }
}
