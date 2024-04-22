package com.productcatalogue.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Products")
public class Product 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private int id;
	
	private String name;
    private String description;
    private double price;
    
    @ElementCollection
    @CollectionTable(name = "product_categories", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "category")
    private List<String> categories;
    
    private String attributes;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "availability_id")
    @JsonManagedReference
    private Availability availability;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Rating> ratings;
    
    
	
	

	public Product(int id, String name, String description, double price, List<String> categories, String attributes,
			Availability availability, List<Rating> ratings) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.categories = categories;
		this.attributes = attributes;
		this.availability = availability;
		this.ratings = ratings;
	}

	public Product() {
		super();
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public Availability getAvailability() {
		return availability;
	}

	public void setAvailability(Availability availability) {
		this.availability = availability;
	}

	

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", description=" + description + ", price=" + price + ", categories="
				+ categories + ", attributes=" + attributes + ", availability=" + availability + ", ratings=" + ratings
				+ "]";
	}
    
    
}
