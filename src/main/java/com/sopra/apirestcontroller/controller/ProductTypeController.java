package com.sopra.apirestcontroller.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;
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

import com.sopra.apirestcontroller.common.DTO.ProductTypeDto;
import com.sopra.apirestcontroller.domain.service.Impl.ProductTypeServiceImpl;

import jakarta.validation.Valid;

@RestController
@Validated
@PreAuthorize("denyAll()")
@RequestMapping("/api/types")
public class ProductTypeController {
    private ProductTypeServiceImpl oTypeService;

    public ProductTypeController(){
        
    }
    @Autowired
    public ProductTypeController(ProductTypeServiceImpl oTypeService) {
        this.oTypeService = oTypeService;
    }

    @GetMapping("/")
    public List<ProductTypeDto> getAll() {
        return oTypeService.getAllTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductTypeDto> getType(@PathVariable Long id) {
        return ResponseEntity.ok(oTypeService.findById(id));
    };

    @PostMapping("/add")
    public ResponseEntity<ProductTypeDto> postCreate(@Valid @RequestBody ProductTypeDto newTypeDto) {
        return ResponseEntity.ok(oTypeService.create(newTypeDto));
    }

    @PutMapping("/update")
    public ResponseEntity<ProductTypeDto> putUpdate(@RequestBody ProductTypeDto productDto) {
        return ResponseEntity.ok(oTypeService.update(productDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteDelete(@PathVariable Long id) {
        return ResponseEntity.ok(oTypeService.deleteById(id));
    }

    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { MethodArgumentNotValidException.class, HttpMessageNotReadableException.class })
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
