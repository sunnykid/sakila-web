package controller;

import java.util.ArrayList;
import java.util.HashMap;

import dao.CustomerDao;

public class MainCustomerMapList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CustomerDao customerDao = new CustomerDao();
		ArrayList<HashMap<String,Object>> list = customerDao.selectCustomerMapList(0,5);
		for(HashMap<String,Object> m : list) {
			System.out.print(m.get("firstName")+", ");
			System.out.print(m.get("lastName")+", ");
			System.out.print(m.get("address")+", ");
			System.out.print(m.get("district")+", ");
			System.out.print(m.get("city")+", ");
			System.out.print(m.get("country")+", ");
			
		}
	}

}
