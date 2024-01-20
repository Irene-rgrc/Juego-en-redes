/*package com.caidacelestial.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class Controller {

	Map<Long, String> users = new ConcurrentHashMap<>();
	AtomicLong nextId = new AtomicLong(0);
	
	public void cargarUsuarios(HashMap<String, String> hash) {
		for(String indice : hash.keySet()) {
			System.out.println(hash.get(indice));
		}
	}
	
	@GetMapping
	public Collection<String> items() {
		return users.values();
	}
	
	@PostMapping
	public String nuevoUser(@RequestBody String user) throws ClassNotFoundException, IOException {
		long id = nextId.incrementAndGet();
		users.put(id, user);
		return user;
	}
	
	
	/*@PutMapping("/{id}")
	public ResponseEntity<String> actulizaUser(@PathVariable String id, @RequestBody User itemActualizado) {

		User savedItem = users.get(itemActualizado.getUser());

		if (savedItem != null) {

			users.put(id, itemActualizado);

			return new ResponseEntity<>(itemActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> getUser(@PathVariable long id) {

		String savedUser = users.get(id);

		if (savedUser != null) {
			return new ResponseEntity<>(savedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> borrarUser(@PathVariable long id) {

		String savedUser = users.get(id);

		if (savedUser != null) {
			users.remove(id);
			return new ResponseEntity<>(savedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}*/