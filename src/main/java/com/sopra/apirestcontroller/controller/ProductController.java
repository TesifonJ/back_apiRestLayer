package com.sopra.apirestcontroller.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sopra.apirestcontroller.common.DTO.ProductDto;
import com.sopra.apirestcontroller.domain.service.Impl.ProductServiceImpl;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/products")
public class ProductController {
    ProductServiceImpl oProductService;

    public ProductController(){
        
    }

    @Autowired
    public ProductController(ProductServiceImpl oProductService) {
        this.oProductService = oProductService;
    };

    @GetMapping("/")
    @PreAuthorize("hasAuthority('READ')")
    public List<ProductDto> getAll() {
        return oProductService.getAllProducts();
    };

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(oProductService.findById(id));
    };

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> postCreate(@Valid @RequestBody ProductDto newProductDto) {
        return ResponseEntity.ok(oProductService.create(newProductDto));
    };

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> putUpdate(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(oProductService.update(productDto));
    };

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> deleteDelete(@PathVariable Long id) {
        return ResponseEntity.ok(oProductService.deleteById(id));
    };

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { MethodArgumentNotValidException.class, HttpMessageNotReadableException.class,
            DataIntegrityViolationException.class })
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    };
}