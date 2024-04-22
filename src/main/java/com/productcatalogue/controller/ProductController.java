package com.productcatalogue.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productcatalogue.service.ProductService;

import com.productcatalogue.model.Product;

@RestController
public class ProductController 
{
	@Autowired
	private ProductService productService;
	
	//get All products
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts()
	{
		List<Product> list= productService.getAllProducts();
		if(list.size()<=0)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}
	
	//get single product
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") int id)
	{
		Product product = productService.getProductById(id);
		if(product == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(product));
	}
	
	//add new Product
	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product product)
	{
		Product p = null;
		try
		{
			p = this.productService.addProduct(product);
			return ResponseEntity.of(Optional.of(p));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//delete the Product
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("productId")int productId)
	{
		try
		{
			this.productService.deleteProduct(productId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//update product
	@PutMapping("/products/{productId}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product ,@PathVariable("productId")int productId)
	{
		try
		{
			this.productService.updateProduct(product, productId);
			return ResponseEntity.ok().body(product);
		}catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//search product
	@GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String attribute) 
	{

        List<Product> products = productService.searchProducts(name, category, attribute);

        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(products);
    }
}
