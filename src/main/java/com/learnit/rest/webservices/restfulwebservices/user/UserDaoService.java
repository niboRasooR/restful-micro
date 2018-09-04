package com.learnit.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;




@Component
public class UserDaoService {

	private static int usersCount =3;
		private static List<User> users = new ArrayList<>();
		
		
		static {
			users.add(new User(1,"Jack", new Date()));
			users.add(new User(2,"Burt", new Date()));
			users.add(new User(3,"Mörö", new Date()));
			
		}
		
		public List<User> findAll(){
			return users;
		}
		
		public User save(User user) {
			if(user.getId()==null) {
				user.setId(++usersCount);
			}
			//lisätään uusi käyttäjä tauluun
			users.add(user);
			return user;
		}
		
		public User findOne(int id) {
			for(User user:users) {
				if(user.getId()==id) {
					return user;
				}
			}
			return null;
		}
}

