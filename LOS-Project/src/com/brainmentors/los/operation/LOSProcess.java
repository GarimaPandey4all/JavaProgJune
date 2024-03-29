package com.brainmentors.los.operation;
import com.brainmentors.los.customer.Customer;
import com.brainmentors.los.customer.LoanDetails;
import com.brainmentors.los.customer.PersonalInformation;
import com.brainmentors.los.utils.CommonConstants;
import com.brainmentors.los.utils.LoanConstants;
import com.brainmentors.los.utils.StageConstants;
import com.brainmentors.los.utils.Utility;

import static com.brainmentors.los.utils.Utility.scanner;
import static com.brainmentors.los.utils.Utility.serialCounter;

import java.util.ArrayList;

public class LOSProcess implements StageConstants, CommonConstants{
	
	//private Customer customers[] = new Customer[100];
	private ArrayList<Customer> customers = new ArrayList<>();
	
	public void qde(Customer customer)
	{
		customer.setStage(QDE);
		
		System.out.println("Application Number "+customer.getId());
		System.out.println("Name "+customer.getPersonal().getFirstName()
				+" "+customer.getPersonal().getLastName());
		System.out.println("You Applied for a "+customer.getLoanDetails().getType()
				+" Duration "+customer.getLoanDetails().getDuration()
				+" Amount "+customer.getLoanDetails().getAmount());
		System.out.println("Enter the Pan Card Number");
		String panCard = scanner.next();
		System.out.println("Enter Voter Id");
		String voterId = scanner.next();
		System.out.println("Enter the Income");
		double income = scanner.nextDouble();
		System.out.println("Enter the Liability");
		double liability = scanner.nextDouble();
		System.out.println("Enter the Phone");
		String phone = scanner.next();
		System.out.println("Enter the Email");
		String email = scanner.next();
		
		customer.getPersonal().setPanCard(panCard);
		customer.getPersonal().setVoterId(voterId);
		customer.getPersonal().setEmail(email);
		customer.getPersonal().setPhone(phone);
		customer.setIncome(income);
		customer.setLiability(liability);
		
	}
	
	public void moveToNextStage(Customer customer)
	{
		while(true)
		{
			if(customer.getStage() == SOURCING)
			{
				System.out.println("Sourcing, Want to Move to the Next Stage Y/N");
				char choice = scanner.next().toUpperCase().charAt(0);
				
				if(choice == YES)
				{
					qde(customer);
				}
				else {
					return;
				}
			}
			
			if(customer.getStage() == QDE)
			{
				System.out.println("QDE, Want to Move to the Next Stage Y/N");
				char choice = scanner.next().toUpperCase().charAt(0);
				
				if(choice == YES)
				{
					dedupe(customer);
				}
				else {
					return;
				}
			}
			
			if(customer.getStage() == DEDUPE)
			{
				System.out.println("Dedupe, Want to Move to the Next Stage Y/N");
				char choice = scanner.next().toUpperCase().charAt(0);
				
				if(choice == YES)
				{
					scoring(customer);
				}
				else {
					return;
				}
			}
			
			if(customer.getStage() == SCORING)
			{
				System.out.println("Scoring, Want to Move to the Next Stage Y/N");
				char choice = scanner.next().toUpperCase().charAt(0);
				
				if(choice == YES)
				{
					approval(customer);
				}
				else {
					return;
				}
			}
		}
	}
	
	public void dedupe(Customer customer)
	{
		customer.setStage(DEDUPE);
		//System.out.println("Inside dedupe");
		
		boolean isNegativeFound = false;
		
		for(Customer negativeCustomer : DB.getNegativeCustomers())
		{
			int negativeScore = isNegative(customer, negativeCustomer);
			
			if(negativeScore > 0)
			{
				System.out.println("Customer Record Fraud in Dedupe and Score is "+negativeScore);
				isNegativeFound = true;
				break;
			}
		}
		
		if(isNegativeFound)
		{
			System.out.println("Do you want to proceed this loan "+customer.getId());
			char choice = scanner.next().toUpperCase().charAt(0);
			if(choice == NO)
			{
				customer.setRemarks("Loan is Rejected, Due to High score in Dedupe Check");
				customer.setStage(REJECT);
				return;
			}
		}
		
	}
	
	private int isNegative(Customer customer, Customer negative)
	{
		int percentageMatch = 0;
		
		if(customer.getPersonal().getPhone().equals(negative.getPersonal().getPhone()))
		{
			percentageMatch += 20;
		}
		if(customer.getPersonal().getEmail().equals(negative.getPersonal().getEmail()))
		{
			percentageMatch += 20;
		}
		if(customer.getPersonal().getVoterId().equals(negative.getPersonal().getVoterId()))
		{
			percentageMatch += 20;
		}
		if(customer.getPersonal().getPanCard().equals(negative.getPersonal().getPanCard()))
		{
			percentageMatch += 20;
		}
		if(customer.getPersonal().getAge() == negative.getPersonal().getAge() && 
				customer.getPersonal().getFirstName().equalsIgnoreCase(negative.getPersonal().getFirstName()))
		{
			percentageMatch += 20;
		}
		
		return percentageMatch;
	}
	
	public void scoring(Customer customer)
	{
		customer.setStage(SCORING);
		
		//System.out.println("Inside Scoring");
		
		int score = 0;
		
		double totalIncome = customer.getIncome() - customer.getLiability();
		
		if(customer.getPersonal().getAge() >= 21 && customer.getPersonal().getAge() <= 35)
		{
			score += 50;
		}
		
		if(totalIncome >= 200000)
		{
			score += 50;
		}
		
		customer.getLoanDetails().setScore(score);
	}
	
	public void approval(Customer customer)
	{
		customer.setStage(APPROVAL);
		
		int score = customer.getLoanDetails().getScore();
		
		System.out.println("Id "+customer.getId());
		System.out.println("Name is "+customer.getPersonal().getFirstName()+" "+customer.getPersonal().getLastName());
		System.out.println("Loan "+customer.getLoanDetails().getType() 
				+" Amount "+customer.getLoanDetails().getAmount()
				+" Duration "+customer.getLoanDetails().getDuration());
		
		double approveAmount = customer.getLoanDetails().getAmount() * (score/100);
		System.out.println("Loan Approve Amount is "+approveAmount);
		System.out.println("Do you want to bring this Loan or Not");
		char choice = scanner.next().toUpperCase().charAt(0);
		
		if(choice == NO)
		{
			customer.setStage(REJECT);
			customer.setRemarks("Customer Deny the Approved Amount "+approveAmount);
			return;
		}
		else {
			showEMI(customer);
		}
	}
	
	private void showEMI(Customer customer)
	{
		System.out.println("EMI is ");
		
		if(customer.getLoanDetails().getType() == LoanConstants.HOME_LOAN)
		{
			customer.getLoanDetails().setRoi(LoanConstants.HOME_LOAN_ROI);
		}
		
		if(customer.getLoanDetails().getType() == LoanConstants.AUTO_LOAN)
		{
			customer.getLoanDetails().setRoi(LoanConstants.AUTO_LOAN_ROI);
		}
		
		if(customer.getLoanDetails().getType() == LoanConstants.PERSONAL_LOAN)
		{
			customer.getLoanDetails().setRoi(LoanConstants.PERSONAL_LOAN_ROI);
		}
		
		double perMonthPrinciple = 
		customer.getLoanDetails().getAmount() / customer.getLoanDetails().getDuration();
		
		double interest = (perMonthPrinciple * customer.getLoanDetails().getRoi() 
				* customer.getLoanDetails().getDuration()) / 100;
		
		double totalEmi = perMonthPrinciple + interest;
		System.out.println("Your EMI is "+totalEmi);
		
	}
	
	public void sourcing()
	{
		Customer customer = new Customer();
		customer.setId(serialCounter);
		customer.setStage(SOURCING);
		
		System.out.println("Enter the First Name");
		String firstName = scanner.next();
		System.out.println("Enter the Last Name");
		String lastName = scanner.next();
		System.out.println("Enter the Age");
		int age = scanner.nextInt();
		System.out.println("Enter the Loan Type HL, AL, PL");
		String type = scanner.next();
		System.out.println("Enter the Amount");
		double amount = scanner.nextDouble();
		System.out.println("Duration of Loan");
		int duration = scanner.nextInt();
		
		PersonalInformation pd = new PersonalInformation();
		pd.setFirstName(firstName);
		pd.setLastName(lastName);
		pd.setAge(age);
		customer.setPersonal(pd);
		
		LoanDetails loanDetails = new LoanDetails();
		loanDetails.setType(type);
		loanDetails.setDuration(duration);
		loanDetails.setAmount(amount);
		customer.setLoanDetails(loanDetails);
		
		customers.add(customer);	
		serialCounter++;
		
		System.out.println("Sourcing Done...");
	}
	
	public void checkStage(int applicationNumber)
	{
		boolean isStageFound = false;
		
		if(customers != null && customers.size() > 0)
		{
			for(Customer customer : customers)
			{
				if(customer.getId() == applicationNumber)
				{
					System.out.println("You are on "+ Utility.getStageName(customer.getStage()));
					isStageFound = true;
					moveToNextStage(customer);
					break;
				}
			}
		}	
		if(!isStageFound) {
			System.out.println("Invalid Application Number");
		}
	}
}
