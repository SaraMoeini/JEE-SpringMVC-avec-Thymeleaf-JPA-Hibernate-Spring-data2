package org.sid.web;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.services.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BanqueController {

	@Autowired
	private IBanqueMetier banqueMetier;
	
	@RequestMapping("/operations")
	public String index() {
		return "comptes";
	}

	@RequestMapping("/consulterCompte")
	public String consulter(String codeCompte, Model model,
		
		@RequestParam(name="page", defaultValue ="0" )int page,
		@RequestParam(name="size", defaultValue = "2")int size  
		 ){
		model.addAttribute("codeCompte", codeCompte);
		
		try {
			Compte cp = banqueMetier.consulterCompte(codeCompte);
			Page<Operation> pageOperations = banqueMetier.listOperation(codeCompte, page, size);
			model.addAttribute("listOperations", pageOperations.getContent());
			model.addAttribute("compte", cp);
			//pour les pages
			
			int[] pages = new int[pageOperations.getTotalPages()];
			model.addAttribute("pages", pages);

		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		
		return "comptes";
	}
	
	@RequestMapping(value="/saveOperation", method=RequestMethod.POST)
	public String saveOperation(Model model, String typeOperation,
			String codeCompte, double montant, String codeCompte2) {
		
		try {
			if(typeOperation.equals("VERSEMENT")) {
				banqueMetier.verser(codeCompte, montant);
			} else
				if(typeOperation.equals("RETRAIT")) {
					banqueMetier.retirer(codeCompte, montant);
				} else
					if(banqueMetier.equals("VIREMENT")) {
						banqueMetier.viremente(codeCompte, codeCompte2, montant);
						
					}
			
		} catch (Exception e) {
			model.addAttribute("error",e);
			return "redirect:/consulterCompte?codeCompte=" + codeCompte + "&error" +e.getMessage();

		}
		
		return "redirect:/consulterCompte?codeCompte=" + codeCompte;
	}
}
