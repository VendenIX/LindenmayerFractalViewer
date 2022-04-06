package lsystem;
/**
 * Classe qui sert à définir des règles qui sont utilisées dans le cadre de la réécriture  RULE ( OLD , NEW ) 
 */
public class Rule {
    public String axiome,replacement;

    /**
     * Constructeur de la classe Rule
     * @param axiome
     * @param replacement
     */
    public Rule(String axiome, String replacement) {
        this.axiome = axiome;
        this.replacement = replacement;
    }
    
    /**
     * Donne l'axiome de la règle
     * @return
     */
    public String getAxiome() {
        return axiome;
    }

    /**
     * Donne le remplacement de la règle
     * @return
     */
    public String getReplacement() {
        return replacement;
    }

    /**
     * Modifie l'axiome de la règle
     * @param axiome
     */
    public void setAxiome(String axiome) {
        this.axiome = axiome;
    }

    /**
     * Modifie le remplacement de la règle
     * @param replacement
     */
    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }

    @Override
    public String toString() {
        return "Regle{ axiome=" + axiome + ", replacement=" + replacement + '}';
    }
}
