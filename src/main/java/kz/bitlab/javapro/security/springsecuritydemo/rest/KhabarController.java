package kz.bitlab.javapro.security.springsecuritydemo.rest;

import kz.bitlab.javapro.security.springsecuritydemo.dto.KhabarDTO;
import kz.bitlab.javapro.security.springsecuritydemo.services.KhabarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/khabar")
@CrossOrigin
public class KhabarController {

    @Autowired
    private KhabarService khabarService;

    @GetMapping         //(value = "/allnews")
    public ResponseEntity<List<KhabarDTO>> getAllKhabars(){
        return new ResponseEntity<>(khabarService.getAllKhabars(), HttpStatus.OK);
    }

    @GetMapping(value = "{id}")         //(value = "/getnews{id}")
    public ResponseEntity<KhabarDTO> getKhabar(@PathVariable(name="id") Long id){
        return new ResponseEntity<>(khabarService.getKhabar(id), HttpStatus.OK);
    }
}
