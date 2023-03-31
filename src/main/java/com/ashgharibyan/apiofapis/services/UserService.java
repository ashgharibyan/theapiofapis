package com.ashgharibyan.apiofapis.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.ashgharibyan.apiofapis.models.LoginUser;
import com.ashgharibyan.apiofapis.models.User;
import com.ashgharibyan.apiofapis.models.User;
import com.ashgharibyan.apiofapis.repositories.UserRepository;
import com.ashgharibyan.apiofapis.repositories.UserRepository;
    
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;
    
    // This method will be called from the controller
    // whenever a user submits a registration form.
    
    public User register(User newUser, BindingResult result) {
    
    	// TO-DO - Reject values or register if no errors:
        // Reject if email is taken (present in database)
    	if(userRepo.findByEmail(newUser.getEmail()).isPresent()) {
    		result.rejectValue("email", "Matches", "Email is already registered");
    		return null;
    	}

        // Reject if password doesn't match confirmation
    	if(!newUser.getPassword().equals(newUser.getConfirm())) {
    	    result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
    	}
        // Return null if result has errors
    	if(result.hasErrors()) {
    		return null;
    	}
        // Hash and set password, save user to database
    	String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);
        System.out.println("BEFORE SAVING -- ");
        System.out.println(newUser);
        userRepo.save(newUser);
        return newUser;
    }

    
    // This method will be called from the controller
    // whenever a user submits a login form.
        public User login(LoginUser newLoginObject, BindingResult result) {
        // TO-DO - Reject values:
        
    	// Find user in the DB by email
        Optional<User> user = userRepo.findByEmail(newLoginObject.getEmail());
        // Reject if NOT present
        if(!user.isPresent()) {
        	result.rejectValue("email", "Matches", "Email is not registered!");
        	return null;
        }
        
        // Reject if BCrypt password match fails
        if(!BCrypt.checkpw(newLoginObject.getPassword(), user.get().getPassword())) {
            result.rejectValue("password", "Matches", "Invalid Password!");
            return null;
        }

        // Return null if result has errors
        if(result.hasErrors()) {
        	return null;
        }
        // Otherwise, return the user object
        return user.get();
    }


		public User findById(Long userId) {
			Optional<User> user = userRepo.findById(userId);
			return user.isPresent()? user.get():null;
		}
		
		public UserService(UserRepository userRepo) {
	        this.userRepo = userRepo;
	    }

	    // returns all the entries
	    public List<User> allUsers() {
	        return (List<User>) userRepo.findAll();
	    }

	    // create
	    public User createUser(User b) {
	        return userRepo.save(b);
	    }
	    
	    
	    // updates
	    public User updateUser(User user) {
	        return userRepo.save(user);

	    }
	    
	    // return true if deleted, returns false if there is not entry with that id
	    public Boolean deleteUser(Long id) {
	        if (findById(id) == null) {
	            return false;
	        }
	        userRepo.deleteById(id);
	        return true;
	    }

}
