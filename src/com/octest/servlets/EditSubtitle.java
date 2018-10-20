package com.octest.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp.beans.Subtitlor;
import com.tp.dao.DaoFactory;
import com.tp.dao.SubtitlorDao;
import com.tp.utilities.SubtitlesHandler;

@WebServlet("/EditSubtitle")
public class EditSubtitle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FILE_NAME = "/WEB-INF/password_presentation.srt";
    private SubtitlesHandler subtitles=null;
	private SubtitlorDao subtitlorDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		System.out.println(context.getRealPath(FILE_NAME));
		subtitles = new SubtitlesHandler(context.getRealPath(FILE_NAME));
		request.setAttribute("subtitles", subtitles.getSubtitles());
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/edit_subtitle.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> translatedSubtitles = new ArrayList<String>();
	        DaoFactory daoFactory = DaoFactory.getInstance();
	        this.subtitlorDao = daoFactory.getSubtitlorDao();
		for (int i = 0;i<subtitles.getSubtitles().size();i++)
		{
			Subtitlor subtitlor= new Subtitlor();
			String lignetraduite=request.getParameter("line"+i);
			subtitlor.setLigne(subtitles.getSubtitles().get(i));
			subtitlor.setLignetraduite(lignetraduite);
			translatedSubtitles.add(lignetraduite);
			subtitlorDao.ajouter(subtitlor);
			
		}
		subtitles.setTranslatedSubtitles(translatedSubtitles);
		doGet(request,response);
	}

}
