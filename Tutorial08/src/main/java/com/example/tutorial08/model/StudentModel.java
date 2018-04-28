package com.example.tutorial08.model;

public class StudentModel
{
    private String npm;
    private String name;
    private double gpa;

    public StudentModel(String npm, String name, double gpa) {
    	this.npm = npm;
    	this.name = name;
    	this.gpa = gpa;
    }

    public String getNpm() {
		return this.npm;
	}

	public void setNpm(String npm) {
		this.npm = npm;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGpa() {
		return this.gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	} 
}
