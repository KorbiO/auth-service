package org.sid.service;

import org.sid.dao.AppRoleRepository;
import org.sid.dao.AppUserRepository;
import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public AppUser saveUser(String username, String password, String confirmedPassword ,String phoneNumber, String gender , String address, String cin) {
        AppUser  user=appUserRepository.findByUsername(username);
        if(user!=null) throw new RuntimeException("User already exists");
        if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
        AppUser appUser=new AppUser();
        appUser.setMat(username);
        appUser.setUsername(username);
        appUser.setActived(true);
        appUser.setGender(gender);
        appUser.setPhoneNumber(phoneNumber);
        appUser.setAddress(address);
        appUser.setCin(cin);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUser.setConfirmedPassword(bCryptPasswordEncoder.encode(confirmedPassword));
       
        appUserRepository.save(appUser);
        addRoleToUser(username,"USER");
        return appUser;
    }
    @Override
	public AppUser saveFournisseur(String username, String password, String confirmedPassword, String phoneNumber,
			String gender, String address , String cin) {
		AppUser  user=appUserRepository.findByUsername(username);
        if(user!=null) throw new RuntimeException("Fournisseur already exists");
        if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
        AppUser appUser=new AppUser();
        appUser.setMat(username);
        appUser.setUsername(username);
        appUser.setActived(true);
        appUser.setGender(gender);
        appUser.setPhoneNumber(phoneNumber);
        appUser.setAddress(address);
        appUser.setCin(cin);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUser.setConfirmedPassword(bCryptPasswordEncoder.encode(confirmedPassword));
       
        appUserRepository.save(appUser);
        addRoleToUser(username,"FOURNISSEUR");
        System.out.println(appUser.getRoles());
        return appUser;
	}
   
    @Override
    public AppRole save(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppUser appUser=appUserRepository.findByUsername(username);
        AppRole appRole=appRoleRepository.findByRoleName(rolename);
        appUser.getRoles().add(appRole);
        
    }

	
}
