package com.luminar;

import org.springframework.stereotype.Component;

@Component
public class Accountant implements Employee{

	@Override
	public void doWork() {
		System.out.println("I am an Accountant");
	}
	
}
