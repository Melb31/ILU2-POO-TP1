package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	
	public Village(String nom, int nbVillageoisMaximum,int nbEtals) {
		marche=new Marche(nbEtals);
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}
	
	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	private class Marche{
		private Etal[] etals;
		private Marche(int nbEtals) {
			etals= new Etal[nbEtals];
			for(int i=0; i<nbEtals ; i++) {
				etals[i]=new Etal();
			}
		}
		
	private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit){
		etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
	}
	private int trouverEtalLibre() {
		for(int i=0; i<etals.length ;i++) {
			if ( ! etals[i].isEtalOccupe()) {
				return i;
			}
		}
		return -1;}
	

	
	private Etal[] trouverEtals(String produit) {
		int nbEtalsTrouves=0;
		for(int i=0; i<etals.length ;i++) {
			if (etals[i].isEtalOccupe() &&  etals[i].contientProduit(produit)) {
				nbEtalsTrouves++;
			}
		}
		
		
		Etal[] etalsProduitChercher= new Etal[nbEtalsTrouves];
		int nbEtalsProduit=0;
		for(int i=0; i<etals.length ;i++) {
			if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
				etalsProduitChercher[nbEtalsProduit]=etals[i];
				nbEtalsProduit++;
			}
		}
		return etalsProduitChercher;
	}
	
	private Etal trouverVendeur(Gaulois gaulois) {
		for(int i=0; i<etals.length ;i++) {
			if (etals[i].getVendeur()==gaulois) {
				return etals[i];
			}
		}
		return null;
		}
	public String afficherMarche() {
		int nbEtalVide =0;
		StringBuilder chaine = new StringBuilder();
		for(int i=0; i<etals.length ;i++) {
			if (etals[i].isEtalOccupe()) {
				chaine.append(etals[i].afficherEtal() +"\n");
				
			}
			else {
				nbEtalVide ++;
		   }
			
		}
		chaine.append("Il reste "+nbEtalVide +" étals non utilisés dans le marché.");
		return chaine.toString();
	}
	
	}

	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() +" cherche un endroit pour vendre "+ nbProduit+" produit.\n");
		int numeroEtal=marche.trouverEtalLibre();
		if (numeroEtal ==-1) {
			chaine.append("Il n'y a plus d'étal libre.");
		}
		else {
			marche.utiliserEtal(numeroEtal, vendeur, produit, nbProduit);
			chaine.append(marche.trouverVendeur(vendeur).afficherEtal());
		}
		
		
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
		
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Les vendeurs qui proposent des "+ produit + " sont :");
		Etal[] etal=marche.trouverEtals(produit);
		for (int i=0; i<etal.length ; i++) {
			chaine.append("- "+ etal[i].getVendeur().getNom() +"\n");
		}
		
		return chaine.toString();
	}
	
	public String partirVendeur(Gaulois vendeur) {
		StringBuilder chaine = new StringBuilder();
		return rechercherEtal(vendeur).libererEtal();
		

	}
	public String afficherMarche() {
		return marche.afficherMarche();
	}
	
	
	
	
	
	
}