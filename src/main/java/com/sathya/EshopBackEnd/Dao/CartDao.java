package com.sathya.EshopBackEnd.Dao;

import java.util.List;

import com.sathya.EshopBackEnd.model.Cart;
import com.sathya.EshopBackEnd.model.Product;

public interface CartDao 
{
	 boolean saveCart(Product product, int quantity,String userName);
	 boolean editCart(Product product, int quantity,String userName);
	    
	    boolean deleteCart(Cart cart);
	    
	    Cart getCart(int cartId);
	    
	   List<Cart>    getCartList(String userName);

}
