import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

public class Consumidor implements Runnable
{
	Consumidor(Buffer buffer, Semaphore semaphore)
	{
		this.buffer = buffer;
		this.semaphore = semaphore;
		stop = false;
		id = ++sharedid;
	}
	
	public synchronized void run()
	{
		while(!stop)
		{
			try 
			{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM HH:mm:ss.ms");
				
				semaphore.acquire();
				
				if(!buffer.isEmpty())
				{
					inicio = new Date();
					Pedido aux = buffer.remover();
					semaphore.release();
					wait(3000);
					fim = new Date();
					System.out.println("Pedido " + aux + " consumido pela thread " + id + ". Início: " + sdf.format(inicio) + " Fim: " + sdf.format(fim));
				}
				else
				{
					semaphore.release();
				}
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	private Buffer buffer;
	private static int sharedid = 0;
	private int id;
	private Date inicio, fim;
	private Semaphore semaphore;
	private Boolean stop;
}
