package com.learnit.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;




@Component
//@ConfigurationProperties(prefix="my")
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
		

		public User deleteById(int id) {
			Iterator<User> it = users.iterator();
				
			while(it.hasNext()){
				User user = it.next();
				
				if(user.getId()==id) {
					it.remove();
					return user;
				}
			}
			
		
			return null;
		}
		
}


