package com.bjsxt.vo;

import java.sql.*;
import java.util.*;

public class Users {

	private String name;
	private Integer id;
	private String info;


	public String getName(){
		return name;
	}
	public Integer getId(){
		return id;
	}
	public String getInfo(){
		return info;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public void setInfo(String info){
		this.info=info;
	}
}
