import java.util.concurrent.Semaphore;

public class Agente implements Runnable
{
	public Agente(Buffer buffer, Semaphore semaphore)
	{
		this.buffer = buffer;
		this.semaphore = semaphore;
		stop = false;
		id = ++sharedid;
	}
	
	public void adicionarPedido() throws InterruptedException
	{
		semaphore.acquire();
		
		if(!buffer.isFull())
		{
			Pedido pedido = new Pedido();
			buffer.inserir(pedido);
			System.out.println("Agente " + id + " adicionou o Pedido " + pedido + ".");
		}
		
		semaphore.release();
	}
	
	public synchronized void run()
	{
		while(!stop)
		{
			try 
			{
				wait(3000);
				adicionarPedido();
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void stop()
	{
		stop = true;
	}
	
	private Buffer buffer;
	private Semaphore semaphore;
	private Boolean stop;
	private static int sharedid = 0;
	private int id;
}
