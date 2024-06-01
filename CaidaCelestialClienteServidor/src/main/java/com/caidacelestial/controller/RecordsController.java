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

import com.caidacelestial.entity.Record;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@RestController
@RequestMapping("/records")
public class RecordsController {
	ConcurrentHashMap<Long, Record> records = new ConcurrentHashMap<>(); 
	long nextId;
	//long nextId = 0;
	
	@GetMapping(value = "/")
	public Collection<Record> records() {
		nextId = records.mappingCount()+1;
		return records.values();
	}
	
	@PostMapping(value = "/")
	@ResponseStatus(HttpStatus.CREATED)
	public Record record(@RequestBody Record record) throws ClassNotFoundException, IOException {
		if(!records.containsKey(record.getId())) {
			long id = nextId++;
			record.setId(id);
			records.put(id, record);
			guardarRecords();
			return record;
		}
		return null;
	}
	
	/*@GetMapping("/{id}")
	public ResponseEntity<User> loggearUser(@PathVariable long id) {

		User savedUser = users.get(id);

		if (savedUser != null) {
			return new ResponseEntity<>(savedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/
	
	
	@PostConstruct
	public void cargarRecords() throws IOException, ClassNotFoundException{
		FileInputStream fileInputStream = new FileInputStream("src/main/resources/records.txt");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		ConcurrentHashMap recordsEnFichero = (ConcurrentHashMap) objectInputStream.readObject();
		records = recordsEnFichero;
		objectInputStream.close();
	}

	@PreDestroy
	public void guardarRecords() throws IOException, ClassNotFoundException{
		FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/records.txt");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(records);
		objectOutputStream.close();
	}
}
