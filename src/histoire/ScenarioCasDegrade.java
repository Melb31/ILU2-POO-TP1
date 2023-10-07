package histoire;
import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {

	public static void main(String[] args) {
		try{
		Village village= new Village("QuoicouVille",7,3);
		village.afficherVillageois();
		
		}
		
		
		catch(VillageSansChefException e) {
			System.out.println(e);
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
