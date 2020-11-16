package servece;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlcleaner.HtmlCleaner;

import model.Processo;

public class ProcessoServerce {
	public Processo buscarProcesso(String numeroProcesso) throws ParseException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		Processo processo = new Processo();

		try {
			numeroProcesso = numeroProcesso.replaceAll("[^A-Za-z0-9]", "");

			HttpGet request = new HttpGet(
					"https://patentscope.wipo.int/search/pt/detail.jsf?docId=" + numeroProcesso + "&redirectedID=true");

			CloseableHttpResponse response = httpClient.execute(request);

			try {

				HttpEntity entity = response.getEntity();

				if (entity != null) {
					String result = EntityUtils.toString(entity);
					String linhas[] = result.split("\n");

					for (int i = 0; i < linhas.length; i++) {

						if (linhas[i].contains("detailPCTtableWO"))
							processo.setNumeroPublicacao(
									new HtmlCleaner().clean(linhas[i]).getText().toString().trim());

						if (linhas[i].contains(">PCT/"))
							processo.setNumeroPedidoInternacional(
									new HtmlCleaner().clean(linhas[i]).getText().toString().trim());

						if (linhas[i].contains("detailPCTtablePubDate"))
							processo.setDataPublicacao(new HtmlCleaner().clean(linhas[i]).getText().toString().trim());

						if (linhas[i].contains("Requerentes"))
							processo.setRequerentes(new HtmlCleaner().clean(linhas[i + 7]).getText().toString().trim());

						if (linhas[i].contains("PCTtitle"))
							processo.setTitulo(new HtmlCleaner().clean(linhas[i]).getText().toString().trim());
					}
				}

			} catch (Exception e) {
				System.out.println(e);
			} finally {
				response.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			httpClient.close();
		}

		return processo;

	}
}
