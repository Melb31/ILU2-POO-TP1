package histoire;
import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {

	public static void main(String[] args) {
		try{Etal etal = new Etal();
		etal.libererEtal();
		System.out.println("Fin du test 1");
		Druide druide = new Druide("Panoramix", 2, 5, 10);
		etal.acheterProduit(4, druide);
		System.out.println("Fin du test 2");
		Village village= new Village("QuoicouVille",7,3);
		village.afficherVillageois();
		
		}
		
		
		catch(VillageSansChefException e) {
			System.out.println("yo");
		}
		catch(IllegalArgumentException e) {
			System.out.println("La quantité doit être supérieur ou égale a 1");
		}
		catch(IllegalStateException e) {
			System.out.println("L'étal n'est pas occupée !");
		}
		System.out.println("miaou");
		
	}
	

}
