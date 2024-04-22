package com.productcatalogue.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.productcatalogue.dao.ProductRepository;
import com.productcatalogue.model.Product;

@Component
public class ProductService 
{
	@Autowired
	private ProductRepository productRepository;
	
	//get All Products
	public List<Product> getAllProducts()
	{
		List<Product> list = (List<Product>)this.productRepository.findAll();
		return list;
	}
	
	//get single product
	public Product getProductById(int id)
	{
		Product product = null;
		try
		{
			product = this.productRepository.findById(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return product;
	}
	
	// adding the product
	public Product addProduct(Product p)
	{
		Product result = productRepository.save(p);
		return result;
	}
	
	//delete the product
	public void deleteProduct(int id)
	{
		productRepository.deleteById(id);
	}
	
	//update the product
	public void updateProduct(Product product, int productId)
	{
		product.setId(productId);
		productRepository.save(product);
	}
	
	//search product
	
	public List<Product> searchProducts(String name, String category, String attribute) 
	{
        List<Product> products = getAllProducts();

        if (name != null && !name.isEmpty()) {
            products = products.stream()
                    .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (category != null && !category.isEmpty()) {
            products = products.stream()
                    .filter(p -> p.getCategories().stream().anyMatch(cat -> cat.toLowerCase().contains(category.toLowerCase())))
                    .collect(Collectors.toList());
        }

        if (attribute != null && !attribute.isEmpty()) {
            products = products.stream()
                    .filter(p -> p.getAttributes().toLowerCase().contains(attribute.toLowerCase()))
                    .collect(Collectors.toList());
        }

        return products;
    }
}
