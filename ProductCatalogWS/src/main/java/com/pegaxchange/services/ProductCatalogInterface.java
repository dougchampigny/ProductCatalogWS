package com.pegaxchange.services;

import java.util.List;

import javax.jws.WebService;

@WebService
public interface ProductCatalogInterface 
{
	public Product searchById(int id);
	public Product[] getAllProducts();
	public void insertProduct(Product product);
}
