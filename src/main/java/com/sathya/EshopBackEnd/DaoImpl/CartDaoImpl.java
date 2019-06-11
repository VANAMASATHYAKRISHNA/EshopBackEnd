package com.sathya.EshopBackEnd.DaoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sathya.EshopBackEnd.Dao.CartDao;
import com.sathya.EshopBackEnd.model.Cart;
import com.sathya.EshopBackEnd.model.Product;
@Component
public class CartDaoImpl implements CartDao
{
	@Autowired
	SessionFactory sessionFactory;
	public boolean saveCart(Product product, int quantity,String userName) 
	{
		Session session=null;
			try {
			//	Cart cart=new Cart(); 
			//	 session=sessionFactory.openSession();
			//	if(cart.getCartid()==0)
			//     {
			//  int cartid=(int)(Math.random()*10000);
			//   cart.setCartid(cartid);
			//  }
				Cart cart=new Cart(); 
				session=sessionFactory.openSession();
				cart.setProductId(product.getProductId());
			    cart.setProductName(product.getProductName());
				cart.setProductPrice(product.getProductPrice());
				cart.setProductSupplier(product.getProductSupplier());
				cart.setQuantity(quantity);
				cart.setTotalprice(cart.getProductPrice()*cart.getQuantity());
				cart.setUsername(userName);
				session.save(cart);
			    Transaction transaction	=session.beginTransaction();
			    transaction.commit();
			    return true;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("catch");
				return false;
			}
			finally {
				session.close();
		
			}
	}
	public List<Cart> getCartList(String username) 
	{
		Session session = sessionFactory.openSession();
		 Query query    =   session.createQuery("from Cart where username=:em");
		 query.setParameter("em" ,username);
		List<Cart> cartlist= query.list();
		session.close();
		return cartlist;
		}
	public Cart getCart(int cartId) 
	{
		Session session=sessionFactory.openSession();
		Cart cart=session.get(Cart.class,cartId);
		session.close();
		return cart;
		
	}
public boolean deleteCart(Cart cart) {
	Session session=sessionFactory.openSession();
	session.delete(cart);
	Transaction transaction=session.beginTransaction();
	transaction.commit();
	session.close();
	return false;
	}
@Override
public boolean editCart(Product product, int quantity, String userName) {
	Session session=null;
	try {
		//Cart cart=new Cart(); 
		// session=sessionFactory.openSession();
		//if(cart.getCartid()==0)
	 //    {
	 // int cartid=(int)(Math.random()*10000);
	 //  cart.setCartid(cartid);
	//  }
		Cart cart=new Cart(); 
		 session=sessionFactory.openSession();
		cart.setProductId(product.getProductId());
	    cart.setProductName(product.getProductName());
		cart.setProductPrice(product.getProductPrice());
		cart.setProductSupplier(product.getProductSupplier());
		cart.setQuantity(quantity);
		cart.setTotalprice(cart.getProductPrice()*cart.getQuantity());
		cart.setUsername(userName);
		session.update(cart);
	    Transaction transaction	=session.beginTransaction();
	    transaction.commit();
	    return true;
	}
	catch (Exception e)
	{
		e.printStackTrace();
		System.out.println("catch");
		return false;
	}
	finally {
		session.close();

	}
}

	

}
