package com.jbk.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jbk.entity.Product;

public class ProductConfig {
	
	
	
	
	

	public static SessionFactory getconfig() {
		Configuration cfg=new Configuration();
		cfg.configure();
		cfg.addAnnotatedClass(Product.class);	
		SessionFactory sf=cfg.buildSessionFactory();
		return sf;
	}

}
