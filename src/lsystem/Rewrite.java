package lsystem;

import java.util.ArrayList;
import java.util.Random;
/**
 * Cette classe sert à reécrire une phrase en fonction de règles de réécriture
 */
public class Rewrite {

	public ArrayList<Rule> rules;
	public Rule optionnalRule;
	public String phrase;
	private boolean isSolSystem = false;
	private boolean isFirst;
	private int rate;
	/**
	 * Constructeur de la classe Rewrite qui fait une réécriture DOL-SYSTEM
	 */
	public Rewrite(ArrayList<Rule> rules,String phrase) {
		this.rules = rules;
		this.phrase = phrase;
	}

	/**
	 * Constructeur de la classe Rewrite qui fait une réécriture SOL-SYSTEM
	 * optionnalRule est la règle de substitut
	 * rate indique le taux de chance sur 100 pour que la règle de substitut optionnalRule soit choisie
	 * @require rate compris dans [10,20,30,40,50,60,70,80,90,100]
	 * isFirst indique si c'est la première ou la deuxième règle qui est choisie pour être remplacée par la règle de substitut: optinnalRule
	 */
	public Rewrite(ArrayList<Rule> rules,String phrase, Rule optionnalRule, int rate, boolean isFirst) {
		this.rules = rules;
		this.phrase = phrase;
		this.optionnalRule = optionnalRule;
		this.isSolSystem = true;
		this.isFirst = isFirst;
		this.rate = rate;
	}


	/**
	 * Fait la réécriture de la phrase avec toutes les règles nbIterations fois
	 * Si le paramètre isSolSystem est à true, alors on fait une réécriture stochastique avec une probabilité de rate
	 * @param nbIterations
	 * On pourrait améliorer cette fonction en faisant une sous fonction car y a du code redondant 
	 * @return
	 */
	public String replacement(int nbIterations) {
		//Réécriture DOL-System
		if(!isSolSystem) {
		if(nbIterations > 0) {
			for(int i=0;i<rules.size();i++) { 
				phrase = phrase.replace(rules.get(i).getAxiome(), rules.get(i).getReplacement());
			}
			return replacement(nbIterations-1);	
		}
		return phrase;
		}
		//Réécriture SOL-System 
		else {
			if(nbIterations > 0) {
				//Si c'est la premire regle qui est choisie:
				if(isFirst) {  
					//Pour chaque caractere de la string on va avoir un random entre 0 et 100, 
					//si c'est inferieur a rate on applique la regle optionnelle sur le caractere sinon on applique la regle 1 d'origine:
					String[] res = phrase.split("");
					String resFinal = "";
					for (int i = 0; i < res.length; i++) {
						Random random1 = new Random();
						int alea1 = random1.nextInt(101);
						if(alea1 <= rate) {
							res[i] = res[i].replace(optionnalRule.getAxiome(), optionnalRule.getReplacement());
						//Si la valeur alea = 1 alors on choisit la regle d'origine
						} else {
							res[i] = res[i].replace(rules.get(0).getAxiome(), rules.get(0).getReplacement());
						}
					}
					for (int i = 0; i < res.length; i++) {
						resFinal = resFinal + res[i];
					}
					phrase = resFinal;
					//Et on applique la deuxieme règle normalement
					phrase = phrase.replace(rules.get(1).getAxiome(), rules.get(1).getReplacement());
					return replacement(nbIterations-1);	
				} 
				//Si c'est la deuxieme regle qui est choisie: 
				else {
					//Deja on applique la premiere regle normalement
					phrase = phrase.replace(rules.get(0).getAxiome(), rules.get(0).getReplacement());
					//Puis Pour chaque caractere de la string on va avoir un random entre 0 et 100, 
					//si c'est inferieur a rate on applique la regle optionnelle sur le caractere sinon on applique la regle 2 d'origine:
						String[] res = phrase.split("");
						String resFinal = "";

						for (int i = 0; i < res.length; i++) {
							Random random1 = new Random();
							int alea1 = random1.nextInt(101);

							if(alea1 <= rate) {
								res[i] = res[i].replace(optionnalRule.getAxiome(), optionnalRule.getReplacement());
							} else {
								res[i] = res[i].replace(rules.get(1).getAxiome(), rules.get(1).getReplacement());
							}
						}
						//Je reconverti mon tableau en string et dit que la phrase devient cette string
						for (int i = 0; i < res.length; i++) {
							resFinal = resFinal + res[i];
						}
						phrase = resFinal;
						
						return replacement(nbIterations-1);	
				}
			}
			return phrase;
		}
	}

	/**
	 * Donne les règles
	 * @return
	 */
	public ArrayList<Rule> getRules() {
		return this.rules;
	}

	/**
	 * Donne la phrase
	 */
	public String getPhrase() {
		return this.phrase;
	}
}
