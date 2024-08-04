package com.luminar;

import org.springframework.stereotype.Component;

@Component
public class Manager implements Employee{

	@Override
	public void doWork() {
		System.out.println("I am the manager of the company");
		
	}

}
