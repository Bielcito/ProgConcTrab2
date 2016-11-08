import java.util.Date;

public class Consumidor implements Runnable
{
	Consumidor(Buffer buffer, int id)
	{
		this.buffer = buffer;
		this.id = id;
	}
	
	public synchronized void run()
	{
		while(!buffer.isEmpty())
		{
			try 
			{
				inicio = new Date();
				Pedido aux = buffer.remover();
				wait(3);
				fim = new Date();
				System.out.println("Pedido " + aux + " consumido pela thread " + id + ". Início: " + inicio + "Fim: " + fim);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	private Buffer buffer;
	private int id;
	private Date inicio, fim;
}
