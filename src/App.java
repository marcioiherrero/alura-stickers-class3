import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
	
	public static void main(String[] args) throws Exception {
		
		//Fazer uma conexão HTTP e buscar os TOP 250 filmes
		//String url = "https://api.mocki.io/v2/549a5d8b";
		String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
		URI endereco = URI.create(url);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();
		
		//Extrair só os dados que interessam (título, pôster e classificação)
		JsonParser parser = new JsonParser();
		List<Map<String, String>> listaFilmes = parser.parse(body);
		
		System.out.println(listaFilmes.size() + " filmes\n");
		
		//Exibir e manipular os dados
		for (Map<String, String> filme : listaFilmes) {
			
			String urlImagem = filme.get("image");
			String titulo = filme.get("title");
			
			InputStream inputStream = new URL(urlImagem).openStream();
			String nomeArquivo = "saida/" + titulo + ".png";
			
			GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
			geradora.cria(inputStream, nomeArquivo);
			
			System.out.println(filme.get("title"));
			System.out.println(filme.get("image"));
			System.out.println(filme.get("imDbRating"));
			
			System.out.print("(");
			
			double classif1 = Double.parseDouble(filme.get("imDbRating"));
			long classif2 = (long) classif1;

			for (int i = 0; i < classif2; i++) {
				System.out.print("*");
			}
			
			System.out.println(")\n");
		}
	}

}
