package com.jbk.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.jbk.dao.ProductDao;
import com.jbk.entity.Product;
import com.jbk.utility.ProductUtility;

public class ProductController 
{
	public static void main(String[] args) {
		ProductDao dao=new ProductDao();
		Product product=null;

	Scanner sc=new Scanner(System.in);
	int choice ;
	char c;
	
	do {
		
		System.out.println(" press 1 for save product ");
		System.out.println(" press 2 for get product by id");
		System.out.println(" press 3 to delete product by id");
		System.out.println(" press 4 to update product ");
		System.out.println("press 5 to get all products");
		System.out.println("press 6 to sort product");
		System.out.println("press 7 for restriction ex");

	choice=sc.nextInt();
		
		switch (choice) {
		case 1:
			 product=ProductUtility.prepareProductData(sc);
		String msg=dao.sendData(product);
			System.out.println(msg);
			break;
		case 2:
			System.out.println("enter productId to get product");
			Long productId=sc.nextLong();
			Product product1=dao.getProductbyID(productId);
			if(product1!=null) {
		
			System.out.println(product1);}
			else {
				System.out.println("invalid product id");
			}
            break;
		case 3:
			System.out.println("enter productId to delete product");
			Long productId3=sc.nextLong();
			String msg3=dao.deleteProductbyId(productId3);
			if(msg3!=null) {
			System.out.println(msg3);}
			else {System.out.println("enter valid productID");}
			break;
		case 4:
		{
			try
			{
				 product=ProductUtility.prepareProductData(sc);
				if(product!=null) 
				{
				String msg4=dao.updateProduct(product);
				System.out.println(msg4);
				}
				else {System.out.println("product is not valid");}
			}
			catch (Exception e) {
				
				System.out.println("such product is not available");
				
			}
			break;}
		case 5 :
			List<Product> list=dao.getallProducts();
			
			
			if(list.isEmpty() || list==null) {
				System.out.println("invalid product");
			}else {
			for (Product product2 : list) {
				
				System.out.println(product2);
				}
				
			}
			
			break;
		case 6 :
			System.out.println("enter order type asc/desc");
			String ordertype=sc.next();
			System.out.println("enter propertyname");
			String propertyname=sc.next();
		List<Product> list1=dao.sortProducts(ordertype,propertyname);
		try {
			if(list1.isEmpty() || list1==null) {
				System.out.println("invalid product");
			}else {for (Product product2 : list1) {
				System.out.println(product2);
				
				
			}}
		} catch (Exception e) {
			System.out.println("exception");
		}
		
			break;
		case 7:
			List<Product> list2=dao.restrictionEx();
			if(list2.isEmpty() || list2==null) {
				System.out.println("invalid product");
			}
			else {
				for (Product product2 : list2) {
					System.out.println(product2);
				}
			}
			break;
		default:
			
			System.out.println("invalid choice");
			break;
		}
		
		System.out.println("do u want to purchase again "
				+ "press yes else no ");
	 c=sc.next().charAt(0);
		
	}while(c=='y'|| c=='Y');

}}
