package com.jbk.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.jbk.config.ProductConfig;
import com.jbk.entity.Product;

public class ProductDao {
	SessionFactory sf=ProductConfig.getconfig();
	
	public String sendData(Product product) 
	{
		String msg=null;
		
	
		System.out.println("in dao method before data save");
		try {
			Session session=sf.openSession();
		
			Serializable id=session.save(product);
			session.beginTransaction().commit();
			
				 msg="added"
;			
				
			
			
		} catch (Exception e) {
			
			msg=" product id aleready exists of id="+product.getProductId();
		}
		return msg;
	}
	public Product getProductbyID(Long productId) {
		Product product=null;
		Session session=null;
		try {
			session=sf.openSession();
		  
			 product=session.get(Product.class, productId);
			
		} catch (Exception e) {
			if(session!=null) {
				session.close();
				
			}
		} 
		return product ;
	}
	
	public String deleteProductbyId(Long productId) {
		
		String msg=null;
		Session session=null;
		try {
			 session=sf.openSession();
			Product product=getProductbyID(productId);
			if(product!=null) 
			{
			session.delete(product);
			session.beginTransaction().commit();
			msg="data successfully deleted";
			}
			else
			{
				msg="product id is wrong="+productId;
			}
			
		} catch (Exception e) {
			if(session!=null) {
				session.close();
				
			}
		}
		
		return msg;
	}
	public String updateProduct(Product product) {
		Session session=null;
		String msg=null;
		try {
			
			session=sf.openSession();
			if(session!=null) {
			session.update(product);
			session.beginTransaction().commit();
			msg="data successfully updated";}
		} catch (Exception e) {
			msg="product id is wrong"+product.getProductId();
		}
		
		return msg;
	}
	public List<Product> getallProducts() {
		List<Product> list=new  ArrayList<Product>();
		
		Session session=null;
		try { session=sf.openSession();
		
		Criteria criteria=session.createCriteria(Product.class);
		criteria.setFirstResult(0);
		criteria.setMaxResults(2);
		
		list=criteria.list();
		
		
			
		} catch (Exception e) {
			System.out.println("invalid input");
			
		}
		return list;
		
	}
	public List<Product> sortProducts(String ordertype, String propertyname) {
		Session session=null;
		List<Product> list=null;
		try {
			session=sf.openSession();
			Criteria criteria=session.createCriteria(Product.class);
			if(ordertype.equals("asc")) {
			criteria.addOrder(Order.asc(propertyname));}
			else if(ordertype.equals("desc")) {
				criteria.addOrder(Order.desc(propertyname));
			}
			else {
				System.out.println("invalid order type");
				return null;
			}
			list=criteria.list();
		} catch (Exception e) {
			System.out.println("invalid input");
		}
		return list;
	}
	public List<Product> restrictionEx() {
		Session session=null;
		List<Product> list=null;
		double val=1000;
		try {session=sf.openSession();
		Criteria criteria=session.createCriteria(Product.class);
		criteria.addOrder(Order.asc("productPrice"));
		criteria.add(Restrictions.ge("productPrice",val));
		list=criteria.list();
		
			
		} catch (Exception e) {
			System.out.println("invalid input");
		}
		return list;
		
	}

}
