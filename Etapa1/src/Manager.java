
public class Manager {
	public Manager(int tamanho, int quantidade)
	{
		this.tamanho = tamanho;
		this.quantidade = quantidade;
		buffer = new Buffer(tamanho);
		Pedido aux;
		for(int i = 0; i < tamanho; i++)
		{
			aux = new Pedido(i);
			buffer.inserir(aux);
		}
	}
	
	public void run()
	{
		long Inicio = System.currentTimeMillis();
		
		//consumidores = new Consumidor[quantidade];
		threads = new Thread[quantidade];
		for(int i = 0; i < quantidade; i++)
		{
			threads[i] = new Thread(new Consumidor(buffer, i));
			threads[i].start();
		}
		
		for(int i = 0; i < quantidade; i++)
		{
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Tempo de execução: " + (System.currentTimeMillis() - Inicio));
	}
	
	public int getTamanho()
	{
		return tamanho;
	}
	
	private Buffer buffer;
	//private Consumidor[] consumidores;
	private Thread[] threads;
	private int tamanho, quantidade;
}
