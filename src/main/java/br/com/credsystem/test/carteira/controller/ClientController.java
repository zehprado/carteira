package br.com.credsystem.test.carteira.controller;

import java.util.List;

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

import br.com.credsystem.test.carteira.model.Client;
import br.com.credsystem.test.carteira.request.AddClientRequest;
import br.com.credsystem.test.carteira.request.UpdateClientRequest;
import br.com.credsystem.test.carteira.response.DefaultResponse;
import br.com.credsystem.test.carteira.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/client")
@Api(value = "Lista de serviços relacionado ao Cliente")
public class ClientController {
	
	@Autowired
	private ClientService service;

	@GetMapping(value = "/all")
	@ApiOperation(value = "Listar todos os serviços", httpMethod = "GET")
	public ResponseEntity<?> listAllClient(){
		List<Client> listClient = service.listAllClient();
		return new ResponseEntity<List<Client>>(listClient,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{clientId}")
	@ApiOperation(value = "Listar cliente por Id", httpMethod = "GET")
	public ResponseEntity<?> findClient(@PathVariable Integer clientId){
		Client client = service.listClientById(clientId);
		return new ResponseEntity<Client>(client,HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{clientId}")
	@ApiOperation(value = "Deletar cliente", httpMethod = "DELETE")
	public ResponseEntity<?> deleteClient(@PathVariable Integer clientId){
		DefaultResponse response = new DefaultResponse();
		response.setMessage(service.deleteClientById(clientId));
		return new ResponseEntity<DefaultResponse>(response,HttpStatus.OK);
	}
	
	@PostMapping(value = "/update")
	@ApiOperation(value = "Atualizar cliente", httpMethod = "POST")
	public ResponseEntity<?> updateClient(@RequestBody UpdateClientRequest client){
		DefaultResponse response = new DefaultResponse();
		response.setMessage(service.updateClient(client));
		return new ResponseEntity<DefaultResponse>(response,HttpStatus.OK);
	}
	
	@PostMapping(value = "/add")
	@ApiOperation(value = "Adicionar Cliente", httpMethod = "POST")
	public ResponseEntity<?> addClient(@RequestBody AddClientRequest client){
		String message = service.addClient(client);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
}
