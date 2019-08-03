package com.po;

import java.sql.*;
import java.util.*;

public class Emp {


	private String empname;

	private java.sql.Date birthday;

	private Integer bonus;

	private Integer id;

	private Integer salary;

	private Integer age;

	public String getEmpname (){
		return this.empname;
	}

	public void setEmpname (String empname){
		this.empname=empname;
	}

	public java.sql.Date getBirthday (){
		return this.birthday;
	}

	public void setBirthday (java.sql.Date birthday){
		this.birthday=birthday;
	}

	public Integer getBonus (){
		return this.bonus;
	}

	public void setBonus (Integer bonus){
		this.bonus=bonus;
	}

	public Integer getId (){
		return this.id;
	}

	public void setId (Integer id){
		this.id=id;
	}

	public Integer getSalary (){
		return this.salary;
	}

	public void setSalary (Integer salary){
		this.salary=salary;
	}

	public Integer getAge (){
		return this.age;
	}

	public void setAge (Integer age){
		this.age=age;
	}

}