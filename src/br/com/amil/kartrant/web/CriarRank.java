package br.com.amil.kartrant.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import br.com.amil.kartrank.bo.RankBO;
import br.com.amil.kartrank.util.FileUtil;
import br.com.amil.kartrank.vo.Rank;
import br.com.amil.kartrank.vo.Volta;

@WebServlet(urlPatterns="/criar_rank")
@MultipartConfig
public class CriarRank extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part filePart = req.getPart("arquivo_log");
		FileUtil fileUtil = new FileUtil();
		
		try {
			List<Volta> voltas = fileUtil.readFile(filePart.getInputStream());
			RankBO rankBO = new RankBO();
			List<Rank> rank = rankBO.criarRank(voltas);
			
			req.setAttribute("rank", rank);
			
			Volta melhorVolta = rankBO.getMelhorVoltaDaCorrida(voltas);
			req.setAttribute("melhorVolta", melhorVolta);
			
			req.setAttribute("rankAposVencedor", rank);
						
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/rank.jsp");
			dispatcher.forward(req, resp);
			
		} catch (Exception e) {
			e.getStackTrace();
		}		
		
	}
	
}