package com.jlcastaneda.market.web.controller;


import com.jlcastaneda.market.domain.Purchase;
import com.jlcastaneda.market.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private static final Logger logger = Logger.getLogger(PurchaseController.class.getName());

    @Autowired
    private PurchaseService purchaseServiceImpl;

    @RequestMapping("/all")
    public ResponseEntity<List<Purchase>> getAll(){

        try{
            List<Purchase> purchases =  purchaseServiceImpl.getAll();
            return new ResponseEntity<>(purchases,HttpStatus.OK);
        }catch(Exception e){
            logger.info("Ocurrio un error al intentar recuperar las compras.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/client/{clientId}")
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("clientId") String clientId){
        //todo: si el cliente no existe tira el 204... deberia validarse
        try{
            Optional<List<Purchase>> optionalList = purchaseServiceImpl.getByClient(clientId);
            if(optionalList.isPresent() && !optionalList.get().isEmpty()) {
                return new ResponseEntity<>(optionalList.get(), HttpStatus.OK);
            } else {
                logger.info("el cliente no tiene compras");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e){
            logger.info("Ocurrio un error al buscar al cliente "+ clientId + " en la BD.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase){

        ResponseEntity<Purchase> response = null;

        try{
            purchaseServiceImpl.save(purchase);
            response = new ResponseEntity<>(purchase, HttpStatus.CREATED);
        }catch(Exception e){
            response = new ResponseEntity<>(HttpStatus.CONFLICT);
            logger.info("error al intentar guardar la Compra");
        }
        return response;
    }
}
