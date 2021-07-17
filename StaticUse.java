class Enemy {
	private String name;
	private boolean weapon;
	private int attack;
	//Class is loaded, static block / things is call
	private static int counter; // counter = 0
	
	Enemy(String name, boolean weapon, int attack){
		this.name = name;
		this.weapon = weapon;
		this.attack = attack;
		counter++;
		System.out.println("Enemy Created "+counter);
	}
}


public class StaticUse {
	
	public static void main(String[] args) {
		main(null);
		for(int i = 1; i <= 10; i++) {
			Enemy obj = new Enemy("L"+i, true, i);
		}
		
	}
}
