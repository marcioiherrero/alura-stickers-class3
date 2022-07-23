import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
	
	public static void main(String[] args) throws Exception {
		
		APIsUrl minhaAPIsUrl = APIsUrl.URL_IMDB;
		
		//Fazer uma conexão HTTP e buscar os TOP 250 filmes
		ClienteHttp http = new ClienteHttp();
		String json = http.buscaDados(minhaAPIsUrl.url());
		
		//Exibir e manipular os dados
		ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
		List<Conteudo> conteudos = extrator.extraiConteudos(json);
		
		System.out.println("API retornou " + conteudos.size() + " filmes.\n");
		
		GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
		
		for(int i = 0; i < 10; i++) {
			Conteudo conteudo = conteudos.get(i);
			
			InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
			String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";
			
			geradora.cria(inputStream, nomeArquivo);
			
			System.out.println(conteudo.getTitulo());
			System.out.println();
			
		}
		
	}
	
}
