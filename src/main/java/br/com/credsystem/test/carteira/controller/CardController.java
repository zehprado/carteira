package br.com.credsystem.test.carteira.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.credsystem.test.carteira.model.Card;
import br.com.credsystem.test.carteira.request.UpdateCardRequest;
import br.com.credsystem.test.carteira.request.UpdateLimitRequest;
import br.com.credsystem.test.carteira.response.DefaultResponse;
import br.com.credsystem.test.carteira.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/card")
@Api(value = "Lista de serviços relacionado a Cartões")
public class CardController {

	@Autowired
	private CardService service;
	
	
	@GetMapping(value = "/all/{clientId}")
	@ApiOperation(value = "Listar todos os cartões de um determinado cliente", httpMethod = "GET")
	public ResponseEntity<?> listAllCardByClient(@PathVariable Integer clientId){
		List<Card> listCard = service.listAllCardsById(clientId);
		return new ResponseEntity<List<Card>>(listCard,HttpStatus.OK);
	}
	
	@GetMapping(value = "/all")
	@ApiOperation(value = "Listar todos os cartões", httpMethod = "GET")
	public ResponseEntity<?> listAllCard(){
		List<Card> listCard = service.listAllCards();
		return new ResponseEntity<List<Card>>(listCard,HttpStatus.OK);
	}
	
	@GetMapping(value = "/listCard/{cardNumber}")
	@ApiOperation(value = "Listar unico cartão", httpMethod = "GET")
	public ResponseEntity<?> listCard(@PathVariable String cardNumber){
		Card card = service.listCard(cardNumber);
		return new ResponseEntity<Card>(card,HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deleteCard/{cardNumber}")
	@ApiOperation(value = "Deletar cartão", httpMethod = "DELETE")
	public ResponseEntity<?> deleteCard(@PathVariable String cardNumber){
		DefaultResponse response = new DefaultResponse();
		response.setMessage(service.deleteCard(cardNumber));
		return new ResponseEntity<DefaultResponse>(response,HttpStatus.OK);
	}
	
	@PostMapping(value = "/updateCard")
	@ApiOperation(value = "Atualizar cartão", httpMethod = "POST")
	public ResponseEntity<?> updateCard(@RequestBody UpdateCardRequest card){
		DefaultResponse response = new DefaultResponse();
		response.setMessage(service.updateCard(card));
		return new ResponseEntity<DefaultResponse>(response,HttpStatus.OK);
	}

	@PostMapping(value = "/addCard/{clientId}")
	@ApiOperation(value = "Adicionar cartão ao cliente", httpMethod = "POST")
	public ResponseEntity<?> addCard(@PathVariable Integer clientId, @RequestBody @Valid Card card){
		String message = service.addCardToClient(clientId, card);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	@PostMapping(value = "/updateLimit")
	@ApiOperation(value = "Usar limite do cartão", httpMethod = "POST")
	public ResponseEntity<?> updateLimit(@RequestBody UpdateLimitRequest updateLimit){
		String message = service.updateLimit(updateLimit);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	@PostMapping(value = "/paymentCard")
	@ApiOperation(value = "Pagar limite do cartão", httpMethod = "POST")
	public ResponseEntity<?> paymentCard(@RequestBody UpdateLimitRequest paymentCard){
		String message = service.paymentCard(paymentCard);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
}
