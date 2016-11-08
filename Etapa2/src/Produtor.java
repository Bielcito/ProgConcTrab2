import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * 
 * @author LarissaNA
 * Construtor da classe Produtor que recebe o buffer o qual será preenchido por 
 * esta classe e o mutex que vai garantir a exclusão mútua entre os agentes.
 */
public class Produtor implements Runnable
{
	public Produtor(Buffer buffer, Semaphore semaphore)
	{
		this.buffer = buffer;
		this.semaphore = semaphore;
		stop = false;
		id = ++sharedid;
	}
	
	public void adicionarPedido() throws InterruptedException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM HH:mm:ss.ms");
		
		semaphore.acquire();
		
		if(!buffer.isFull())
		{
			Pedido pedido = new Pedido();
			buffer.inserir(pedido);
			fim = new Date();
			System.out.println("Cliente " + id + " adicionou o Pedido " + pedido + ". Início: " + sdf.format(inicio) + " Fim: " + sdf.format(fim) + ".");
		}
		
		semaphore.release();
	}
	
	public synchronized void run()
	{
		while(!stop)
		{
			try 
			{
				inicio = new Date();
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
	private Date inicio, fim;
}
