package com.jbk.utility;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jbk.entity.Product;

public class ProductUtility {
	public static Product prepareProductData(Scanner sc) {
		System.out.println("enter product id");
		Long productId = sc.nextLong();

		System.out.println("enter product name");
		String productName = sc.next();

		System.out.println("enter Supplier id");
		long supplierId = sc.nextLong();

		System.out.println("enter category id");
		long categoryId = sc.nextLong();

		System.out.println("enter product price");
		Double productPrice = (double) sc.nextLong();

		System.out.println("enter product qty");
		Integer productQty = sc.nextInt();

		Product product = new Product(productId, productName, productQty, productPrice, supplierId, categoryId);
		
		boolean restrict = dataRestricter(product);
		if (restrict) {
			return product;
		} else {
			return null;
		}

	}

	private static boolean dataRestricter(Product product) {
		boolean isvalid = true;

		if (product.getProductId() <= 0) {
			isvalid = false;
			return isvalid;
		}
		if (product.getProductName() == null) {
			isvalid = false;
			return isvalid;
		}
		else 
		{
	
			 String regex = ".*\\d.*";  // regex to check if string contains any numbers
		        Pattern pattern = Pattern.compile(regex);  // compiles the regex

		        // find match between given string and pattern
		        Matcher matcherText = pattern.matcher(product.getProductName());

		        // return true if the string matched the regex
		        Boolean isanydigit = matcherText.matches();

		       
		        if(isanydigit) {
		        	isvalid = false;
		        	System.out.println("do not enter any number in product name");
					return isvalid;
		        }
			}
		
		if (product.getProductQty() <= 0) {
			isvalid = false;
			return isvalid;
		}
		if (product.getProductPrice() <= 0) {
			isvalid = false;
			return isvalid;
		}
		if (product.getSupplierId() <= 0) {
			isvalid = false;
			return isvalid;
		}
		if (product.getCategoryId() <= 0) {
			isvalid = false;
			return isvalid;
		}
		return isvalid;
	}

}
