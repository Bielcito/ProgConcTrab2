
public class Buffer 
{
	public Buffer(int maxTamanho)
	{
		inicio = 0;
		fim = -1;
		tamanho = 0;
		this.maxTamanho = maxTamanho;
		buffer = new Pedido[maxTamanho];
	}
	
	public Pedido remover()
	{
		if(isEmpty())
		{
			return null;
		}
		else
		{
			tamanho--;
			return buffer[inicio++%maxTamanho];
		}
	}
	
	public void inserir(Pedido pedido)
	{
		if(!isFull())
		{
			tamanho++;
			buffer[++fim%maxTamanho] = pedido;
		}
	}
	
	public Boolean isEmpty()
	{
		if(tamanho == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public Boolean isFull()
	{
		if(tamanho == maxTamanho-1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int getInicio()
	{
		return inicio;
	}
	
	public int getFim()
	{
		return fim;
	}
	
	private int inicio, fim;
	private Pedido[] buffer;
	private int maxTamanho;
	private int tamanho;
}
