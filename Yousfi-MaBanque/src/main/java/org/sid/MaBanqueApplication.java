package org.sid;

import java.util.Date;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.sid.services.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MaBanqueApplication implements CommandLineRunner{

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IBanqueMetier banqueMetier;
	public static void main(String[] args) {
		SpringApplication.run(MaBanqueApplication.class, args);
		}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Client c1= clientRepository.save(new Client("Hassan", "hassan@gmail.com"));
		Client c2= clientRepository.save(new Client("Rachid","rachid@gmail.com"));
	
		Compte cp1= compteRepository.save(
				new CompteCourant("c1", new Date(), 9000, c1, 6000));
		Compte cp2= compteRepository.save(
				new CompteCourant("c2", new Date(), 5000, c2, 2000));
	
		operationRepository.save(new Versement(new Date(), 9000, cp1));  
		operationRepository.save(new Versement(new Date(), 6000, cp1));  
		operationRepository.save(new Versement(new Date(), 2300, cp1));  
		operationRepository.save(new Retrait(new Date(), 9000, cp1)); 
		
		operationRepository.save(new Versement(new Date(), 90230, cp2));  
		operationRepository.save(new Versement(new Date(), 430, cp2));  
		operationRepository.save(new Versement(new Date(), 2300, cp2));  
		operationRepository.save(new Retrait(new Date(), 9000, cp2)); 
		
		//test la couche metier/service
		banqueMetier.verser("c1", 111111);
	}

}
