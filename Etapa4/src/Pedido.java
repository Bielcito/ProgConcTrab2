public class Pedido {
	public Pedido()
	{
		id = ++sharedid;
	}
	
	public String toString()
	{
		return String.valueOf(id);
	}
	
	public int getId()
	{
		return id;
	}
	
	private static int sharedid = 0;
	private int id;
}
