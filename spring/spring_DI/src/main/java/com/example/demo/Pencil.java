package com.example.demo;

public class Pencil {
	
	private String action;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public void howToUse() {
		System.out.println(action);
	}
}
