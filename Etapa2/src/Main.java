
public class Main {
	public static void main(String args[])
	{
		//Cria um manager que vai ter um buffer de 5000 posi��es e 2 consumidores:
		Manager manager = new Manager(10, 2, 5);
		manager.run();
	}
}
