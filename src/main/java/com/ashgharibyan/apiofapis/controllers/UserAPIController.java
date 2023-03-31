package com.ashgharibyan.apiofapis.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashgharibyan.apiofapis.models.Burger;
import com.ashgharibyan.apiofapis.models.User;
import com.ashgharibyan.apiofapis.services.BurgerService;
import com.ashgharibyan.apiofapis.services.UserService;

@RestController
public class UserAPIController {
	@Autowired
	private UserService userServ;
	
	@Autowired
	private BurgerService burgerServ;

	@RequestMapping("/api/all")
	public ArrayList<HashMap<String, ArrayList<HashMap<String, String>>>> index() {
		
		
		List<User> allUsers = (List<User>) userServ.allUsers();
		
		ArrayList<HashMap<String, ArrayList<HashMap<String, String>>>> mainAPI =  new ArrayList<>();
		
		for(int i=0; i<allUsers.size();i++) {
			// One User Level
			HashMap<String, ArrayList<HashMap<String, String>>> thisUserInfo = new HashMap<String, ArrayList<HashMap<String, String>>>();

			//All Model Level
			ArrayList<HashMap<String, String>> allModelInfo = new ArrayList<>();
			
			//Current Model Level
			HashMap<String,String> thisModelInfo = new HashMap<String, String>();
			
//			ArrayList<Burger> allBurgers = (ArrayList<Burger>) burgerServ.allBurgers();
			
			
//			thisModelInfo.put("burger", allBurgers );

			
			User currentUser = allUsers.get(i);
			if(currentUser.getAllAPILinks()!=null) {
				
				System.out.println("IN FOR LOOP");
				System.out.println(currentUser.getAllAPILinks());
				for(int j=0; j<currentUser.getAllAPILinks().size();j++) {
					thisModelInfo = currentUser.getAllAPILinks().get(j);
					allModelInfo.add(thisModelInfo);

				}				
			}
			
//			for(int f=0; f<currentUser.getAllModels().size(); f++) {
//				currentUser.getAllModels().get(f);
//			}
			
			
			thisUserInfo.put(allUsers.get(i).getUserName(), allModelInfo);
			
			mainAPI.add(thisUserInfo);
		}
		
		
		return mainAPI;
	}
}
