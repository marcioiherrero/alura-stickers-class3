import java.util.List;

//Abstração das classes Extratoras de conteúos como:
//ExtratoraDeConteudoDaNasa e ExtratoraDeConteudoDoIMDB
public interface ExtratorDeConteudo {
	
	List<Conteudo> extraiConteudos(String json);	
	
}
