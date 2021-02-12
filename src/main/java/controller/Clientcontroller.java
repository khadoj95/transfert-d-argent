package controller;

import Entite.Client;  
import Service.ClientService;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class Clientcontroller {
	@Autowired
private ClientService clientservice;
	
	@RequestMapping("/client")
	public String index(Model model) {
		List<Client> c=clientservice.listAllCustomers();
		model.addAttribute("Clients", c);
		return "Client";}
		 
		    @GetMapping("")
		    public List<Client> list() {
		        return clientservice.listAllCustomers();
		    }

		    @GetMapping("/{id}")
		    public ResponseEntity<Client> get(@PathVariable long id) {
		        try {
		            Client client = clientservice.getCustomer(id);
		            return new ResponseEntity<Client>(client, HttpStatus.OK);
		        } catch (NoSuchElementException e) {
		            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		        }
		    }
		    @PostMapping("/")
		    public void add(@RequestBody Client client) {
		       clientservice.saveCustomer(client);
		    }
		    @PutMapping("/{id}")
		    public ResponseEntity<?> update(@RequestBody Client client, @PathVariable long id) {
		        try {
		            @SuppressWarnings("unused")
					Client existClient = clientservice.getCustomer(id);
		            client.setId(id);            
		            clientservice.saveCustomer(client);
		            return new ResponseEntity<>(HttpStatus.OK);
		        } catch (NoSuchElementException e) {
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
		    }
		    @DeleteMapping("/{id}")
		    public void delete(@PathVariable Integer id) {

		    	clientservice.deleteCustomer(id);
		    }
		    @GetMapping("/login")
			public String showLoginPage() {
				Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
				if(authentication==null || authentication instanceof AnonymousAuthenticationToken) {
					return "/login";
				}
				return "redirect:/";
			}
		
	}
