package br.com.credsystem.test.carteira.service;

import java.util.List;

import br.com.credsystem.test.carteira.model.Client;
import br.com.credsystem.test.carteira.request.AddClientRequest;
import br.com.credsystem.test.carteira.request.UpdateClientRequest;


public interface ClientService {

	List<Client> listAllClient();

	Client listClientById(Integer clientId);

	String deleteClientById(Integer clientId);

	String updateClient(UpdateClientRequest client);

	String addClient(AddClientRequest client);

}
