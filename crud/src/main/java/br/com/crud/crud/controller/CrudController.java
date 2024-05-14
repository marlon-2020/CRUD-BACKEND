package br.com.crud.crud.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crud.crud.model.CrudModel;
import br.com.crud.crud.repository.CrudRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/bd")
public class CrudController {
    @Autowired
    CrudRepository crudRepository;

    @PostMapping("/criar")
    public ResponseEntity<CrudModel> criaUsuario(@RequestBody CrudModel usuario) {
        CrudModel _usuario = crudRepository.save(usuario);
        return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<CrudModel>> getAllUsuarios(@RequestParam(required = false) String param) {
        List<CrudModel> usuarios = crudRepository.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable("id") long id){
        crudRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/pegar/{id}")
    public ResponseEntity getUsuario(@PathVariable("id") long id) {
        Optional<CrudModel> usuario = crudRepository.findById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
    
}
