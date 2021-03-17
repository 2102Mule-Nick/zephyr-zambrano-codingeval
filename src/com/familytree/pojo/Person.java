package com.familytree.pojo;

public class Person {
	
	private String name;
	
	private String parent1;
	
	private String parent2;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent1() {
		return parent1;
	}

	public void setParent1(String parent1) {
		this.parent1 = parent1;
	}

	public String getParent2() {
		return parent2;
	}

	public void setParent2(String parent2) {
		this.parent2 = parent2;
	}
	
	public Person() {
		super();
	}
	
	public Person(String name, String parent1, String parent2) {
		this.name = name;
		this.parent1 = parent1;
		this.parent2 = parent2;
	}
	
}
