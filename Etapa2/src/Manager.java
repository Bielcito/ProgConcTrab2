import java.util.concurrent.Semaphore;

public class Manager {
	public Manager(int tamanhoBuffer, int numConsumidores, int numAgentes)
	{
		this.tamanhoBuffer = tamanhoBuffer;
		this.numConsumidores = numConsumidores;
		this.numAgentes = numAgentes;
		semaphore = new Semaphore(1);
		buffer = new Buffer(tamanhoBuffer);
		consumidores = new Thread[numConsumidores];
		agentes = new Thread[numAgentes];
	}
	
	public synchronized void run()
	{
		for(int i = 0; i < numConsumidores; i++)
		{
			consumidores[i] = new Thread(new Consumidor(buffer, semaphore));
			consumidores[i].start();
		}
		
		for(int i = 0; i < numAgentes; i++)
		{
			agentes[i] = new Thread(new Produtor(buffer, semaphore));
			agentes[i].start();
		}
	}
	
	public int getTamanhoBuffer()
	{
		return tamanhoBuffer;
	}
	
	public int getNumConsumidores()
	{
		return numConsumidores;
	}
	
	public int getNumAgentes()
	{
		return numAgentes;
	}
	
	private Semaphore semaphore;
	private Buffer buffer;
	private Thread[] consumidores;
	private Thread[] agentes;
	private int numConsumidores, numAgentes, tamanhoBuffer;
}
