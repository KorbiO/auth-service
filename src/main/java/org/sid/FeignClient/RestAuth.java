package org.sid.FeignClient;


import org.sid.common.UserForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CrudApplication" , url = "http://localhost:8011/CrudApplication/") 
public interface RestAuth {

	
	@PostMapping("/client")
	public UserForm postClientDetails(@RequestBody UserForm userForm);
	
	@PostMapping("/fournisseur")
	public UserForm postFournisseurDetails(@RequestBody UserForm userForm);
	
	@PutMapping("/client/{username}")
	public UserForm putClientDetails(@RequestBody UserForm userForm , @PathVariable("username") String username);
	
	@PutMapping("/fournisseur/{username}")
	public UserForm putFournisseurDetails(@RequestBody UserForm userForm , @PathVariable("username") String username);
	
	
	
	
	
	
}
