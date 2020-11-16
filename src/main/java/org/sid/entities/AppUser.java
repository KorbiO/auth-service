package org.sid.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    
    @Column
    private String mat ;
    @Column
    private String address;
    
    @Column()
    private String phoneNumber;
    
    @Column()
    private String cin;
    
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column
    private String password;
    
    @Column()
    private boolean actived;
    
    private String confirmedPassword; 
    @Column()
    private String nom;
    @Column()
    private String prenom;
    @Column()
    private String gender;
    
    public AppUser(String username , String password,String confirmedPassword ) {
    	this.username = username;
    	this.password = password;
    	this.confirmedPassword = confirmedPassword;
    }
    
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> roles=new ArrayList<>();
    public void setMat(String mat) {
    	this.mat = this.username;
    }
    
   
}
