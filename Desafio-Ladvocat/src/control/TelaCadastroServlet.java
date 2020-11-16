package control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.Processo;
import servece.ProcessoServerce;

/**
 * Servlet implementation class ActionServlet
 */
public class TelaCadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TelaCadastroServlet() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Processo processo = new ProcessoServerce().buscarProcesso(request.getParameter("proc"));
		
		if (processo.isValid()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("processo", processo);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(JSONObject.valueToString(map));
		} else {
			response.setStatus(204);
			response.getWriter().write("Sem informações");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Processo processo = new Processo();
		
		processo.setNumeroPublicacao(request.getParameter("numeroPublicacao"));
		processo.setNumeroPedidoInternacional(request.getParameter("numeroPedidoInternacional"));
		processo.setRequerentes(request.getParameter("requerentes"));
		processo.setTitulo(request.getParameter("titulo"));
		processo.setDataPublicacao(request.getParameter("dataPublicacao"));
		
		Boolean x = false;
		
		if("" != processo.getDataPublicacao() && "" != processo.getNumeroPedidoInternacional() && 
				"" != processo.getNumeroPublicacao() && "" != processo.getRequerentes() && "" != processo.getTitulo()) {
			x = new ProcessoController().salvarProcesso(processo);			
		}
		
		
		if (x) {
			response.setStatus(200);
			response.getWriter().write("Salvo com sucesso");
		} else {
			response.setStatus(204);
			response.getWriter().write("Sem informações");
		}
	}
}