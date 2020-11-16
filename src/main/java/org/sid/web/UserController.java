package org.sid.web;

import lombok.Data;

import org.sid.FeignClient.RestAuth;
import org.sid.common.UserForm;
import org.sid.dao.AppUserRepository;
import org.sid.entities.AppUser;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AppUserRepository appUserRepo ;
    
    @Autowired
    private RestAuth restAuthservice ;
    
    @PostMapping("/registerUser")
    public AppUser registerUser(@RequestBody  UserForm userForm){
    	restAuthservice.postClientDetails(userForm);
        return  accountService.saveUser(userForm.getUsername(), userForm.getPassword(),userForm.getConfirmedPassword()
        		    ,userForm.getPhoneNumber() ,userForm.getGender(),userForm.getAddress(), userForm.getCin());
    }
    @PostMapping("/registerFour")
    public AppUser registerFournisseur(@RequestBody  UserForm userForm){
    	restAuthservice.postFournisseurDetails(userForm);
        return  accountService.saveFournisseur(userForm.getUsername(), userForm.getPassword(),userForm.getConfirmedPassword()
        		    ,userForm.getPhoneNumber() ,userForm.getGender(),userForm.getAddress(), userForm.getCin());
    }
    @PutMapping("/putClient/{username}")
    public AppUser putUser(@RequestBody  UserForm userForm , @PathVariable("username") String username) {
    	AppUser appuser = accountService.loadUserByUsername(username);
    	
    	appuser.setAddress(userForm.getAddress());
    	appuser.setCin(userForm.getCin());
    	appuser.setConfirmedPassword(bCryptPasswordEncoder.encode(userForm.getConfirmedPassword()));
    	appuser.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
    	appuser.setPhoneNumber(userForm.getPhoneNumber());
    	appuser.setNom(userForm.getNom());
    	appuser.setPrenom(userForm.getPrenom());
    	appuser =appUserRepo.save(appuser);
    	restAuthservice.putClientDetails(userForm,username);
		return appuser;
    }
    @PutMapping("/putFour/{username}")
    public AppUser putFour(@RequestBody  UserForm userForm , @PathVariable("username") String username) {
    	AppUser appuser = accountService.loadUserByUsername(username);
    	
    	appuser.setAddress(userForm.getAddress());
    	appuser.setCin(userForm.getCin());
    	appuser.setNom(userForm.getNom());
    	appuser.setPrenom(userForm.getPrenom());
    	appuser.setConfirmedPassword(bCryptPasswordEncoder.encode(userForm.getConfirmedPassword()));
    	appuser.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
    	appuser.setPhoneNumber(userForm.getPhoneNumber());
    	appuser =appUserRepo.save(appuser);
    	restAuthservice.putFournisseurDetails(userForm,username);
		return appuser;
    }
    @GetMapping("/appUsers/{username}")
    public AppUser getUserByusername(@PathVariable("username") String username) {
    	AppUser a = appUserRepo.findByUsername(username);
    	System.out.println(a.getPassword());
    	return a;
    }
    
}
/*
@Data
public class UserForm{
    private String username;
    private String password;
    private String confirmedPassword;
    private String phoneNumber ;
    private String gender;
    private String address;
    private String cin ;
    
}
*/

