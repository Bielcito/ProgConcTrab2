import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * 
 * @author LarissaNA
 * Classe produtor que vai inserir Pedidos no buffer que irá receber.
 */
public class Produtor implements Runnable
{
	/**
	 * Construtor da classe Produtor, inicializa variáveis e define um id único 
	 * para cada instância.
	 * @param buffer 
	 * buffer que será preenchido
	 * @param semaphore
	 * semáforo que irá garantir a exclusão mútua entre produtores e consumidores.
	 */
	public Produtor(Buffer buffer, Semaphore semaphore)
	{
		this.buffer = buffer;
		this.semaphore = semaphore;
		stop = false;
		id = ++sharedid;
	}
	
	/**
	 * Adiciona um pedido
	 * @throws InterruptedException
	 */
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
	
	/**
	 * Realiza as funções da thread, adicionando um pedido de tempos em tempos
	 */
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
