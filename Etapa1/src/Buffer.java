
public class Buffer {
	public Buffer(int maxTamanho)
	{
		this.maxTamanho = maxTamanho;
		buffer = new Pedido[maxTamanho];
		tamanho = -1;
	}
	
	public Pedido remover()
	{
		if(isEmpty())
		{
			return null;
		}
		else
		{
			return buffer[tamanho--];
		}
	}
	
	public void inserir(Pedido pedido)
	{
		if(!isFull())
		{
			buffer[++tamanho] = pedido;
		}
	}
	
	public Boolean isEmpty()
	{
		return tamanho == -1;
	}
	
	public Boolean isFull()
	{
		return tamanho+1 == maxTamanho;
	}
	
	private Pedido[] buffer;
	private int maxTamanho;
	private int tamanho;
}
