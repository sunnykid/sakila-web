package controller;

import vo.Country;

public class MainEx1 {

	public static void main(String[] args) {

		Country c = new Country();
		System.out.println(c.getContryId());
		System.out.println(c.getCountry());
		c.setCountryId(100);
		System.out.println(c.getContryId());
	}

}
