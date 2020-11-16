package org.sid.service;



import org.sid.entities.AppRole;
import org.sid.entities.AppUser;

public interface AccountService {//
    public AppUser saveUser(String username,String password,String confirmedPassword,String phoneNumber , String gender, String address , String cin);
    public AppUser saveFournisseur(String username,String password,String confirmedPassword,String phoneNumber , String gender, String address, String cin );
    public AppRole save(AppRole role);
    public AppUser loadUserByUsername(String username);
    public void addRoleToUser(String username,String rolename);
}
