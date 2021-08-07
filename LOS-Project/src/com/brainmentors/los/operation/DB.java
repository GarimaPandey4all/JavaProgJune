package com.brainmentors.los.operation;

import java.util.ArrayList;

import com.brainmentors.los.customer.Customer;
import com.brainmentors.los.customer.PersonalInformation;

public interface DB {
	
	public static ArrayList<Customer> getNegativeCustomers()
	{
		ArrayList<Customer> negativeCustomers = new ArrayList<>();
		
		Customer customer = new Customer();
		customer.setId(1010);
		PersonalInformation pd = new PersonalInformation();
		pd.setFirstName("Tim");
		pd.setLastName("Johnson");
		pd.setPhone("8327469876");
		pd.setPanCard("A111");
		pd.setEmail("tim@gmail.com");
		
		negativeCustomers.add(customer);
		
		customer = new Customer();
		customer.setId(1011);
		pd = new PersonalInformation();
		pd.setFirstName("Tom");
		pd.setLastName("Dahl");
		pd.setPhone("8327469989");
		pd.setPanCard("A222");
		pd.setEmail("tom@gmail.com");
		
		negativeCustomers.add(customer);
		
		return negativeCustomers;
	}
}
