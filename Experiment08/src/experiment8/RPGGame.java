package experiment8;

public class RPGGame {
 public static void main(String[] args) {
     Player player = new Player(100);
     
     try {
         player.takeDamage(10); 
         player.takeDamage(25); 
     } catch (PlayerDefeatedException e) {
         System.out.println(e.getMessage());
     }
 }
}

