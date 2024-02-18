package com.caidacelestial.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.caidacelestial.entity.User;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>(); 
	long nextId;
	//long nextId = 0;
	
	@GetMapping(value = "/")
	public Collection<User> usuarios() {
		nextId = users.mappingCount()+1;
		return users.values();
	}
	
	@PostMapping(value = "/")
	@ResponseStatus(HttpStatus.CREATED)
	public User usuario(@RequestBody User usuario) throws ClassNotFoundException, IOException {
		if(!users.containsKey(usuario.getId())) {
			long id = nextId++;
			usuario.setId(id);
			usuario.setRecord(300);
			users.put(id, usuario);
			guardarUsuarios();
			return usuario;
		}
		return null;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> actulizaUser(@PathVariable long id,@RequestBody User userActualizado) throws ClassNotFoundException, IOException {

		User savedUser = users.get(userActualizado.getId());

		if (savedUser != null) {

			users.put(id, userActualizado);
			guardarUsuarios();

			return new ResponseEntity<>(userActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> eliminarUser(@PathVariable long id) throws ClassNotFoundException, IOException {

		User savedUser = users.get(id);

		if (savedUser != null) {
			users.remove(savedUser.getId());
			users.get(nextId).setId(id);
			users.put(id,users.get(nextId));
			users.remove(nextId);
			nextId--;
			guardarUsuarios();
			return new ResponseEntity<>(savedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> loggearUser(@PathVariable long id) {

		User savedUser = users.get(id);

		if (savedUser != null) {
			return new ResponseEntity<>(savedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostConstruct
	public void cargarUsuarios() throws IOException, ClassNotFoundException{
		FileInputStream fileInputStream = new FileInputStream("src/main/resources/users.txt");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		ConcurrentHashMap usersEnFichero = (ConcurrentHashMap) objectInputStream.readObject();
		users = usersEnFichero;
		objectInputStream.close();
	}

	@PreDestroy
	public void guardarUsuarios() throws IOException, ClassNotFoundException{
		FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/users.txt");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(users);
		objectOutputStream.close();
	}
}
