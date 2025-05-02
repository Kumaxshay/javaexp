
package experiment8;

class PlayerDefeatedException extends Exception {
	 private static final long serialVersionUID = 1L;
public PlayerDefeatedException(String message) {
   super(message);
}
}

class Player {
private int health;

public Player(int health) {
   this.health = health;
}

public void takeDamage(int damage) throws PlayerDefeatedException {
   health -= damage;
   System.out.println("Player takes " + damage + " damage. Remaining health: " + health);
   
   if (health < 0) {
       throw new PlayerDefeatedException("Player has been defeated!");
   }
}
}