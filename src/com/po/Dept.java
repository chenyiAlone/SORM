package com.po;

import java.sql.*;
import java.util.*;

public class Dept {


	private Integer id;

	private String daddr;

	private String dname;

	public Integer getId (){
		return this.id;
	}

	public void setId (Integer id){
		this.id=id;
	}

	public String getDaddr (){
		return this.daddr;
	}

	public void setDaddr (String daddr){
		this.daddr=daddr;
	}

	public String getDname (){
		return this.dname;
	}

	public void setDname (String dname){
		this.dname=dname;
	}

}