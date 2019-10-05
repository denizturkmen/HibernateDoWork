package com.denizturkmen.Client;

import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.denizturkmen.Entity.Person;
import com.denizturkmen.Util.HibernateUtil;

public class DoWorkTest {

	public static void main(String[] args) {
		//doWork entitleri kullanarak verileri almak için bir ORM aracıdır
		//Bazı durumlarda JDBC baglandıtısı kullanmak gerekebilir
		
	
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Person person = session.byId(Person.class).load(1L);
			
			session.doWork(connection ->{
				try(Statement statement = connection.createStatement()){
					statement.executeUpdate("UPDATE Person_Table Set name=UPPER(name)");
					// update komut persontable ismi ne ise o
				}
			});
			session.refresh(person);
			System.out.println(person.getName());
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
	}
}
