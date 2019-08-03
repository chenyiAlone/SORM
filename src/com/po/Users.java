package com.po;

import java.sql.*;
import java.util.*;

public class Users {


	private String name;

	private Integer id;

	private String info;

	public String getName (){
		return this.name;
	}

	public void setName (String name){
		this.name=name;
	}

	public Integer getId (){
		return this.id;
	}

	public void setId (Integer id){
		this.id=id;
	}

	public String getInfo (){
		return this.info;
	}

	public void setInfo (String info){
		this.info=info;
	}

}