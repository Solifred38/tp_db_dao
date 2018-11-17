package com.octest.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp.beans.Article;
import com.tp.dao.*;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Bd")
public class Bd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleDao articleDao;

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.articleDao = daoFactory.getArticleDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Entree dans doGet avant chargement articles");

		request.setAttribute("articles", articleDao.lister());
		this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Article article = new Article();
		if (request.getParameter("nom") != null && request.getParameter("nom") != "") {
			article.setNom(request.getParameter("nom"));
			article.setDescription(request.getParameter("description"));
			article.setPrix(Float.parseFloat(request.getParameter("prix")));
			boolean test = (request.getParameter("todelete")!=null);
			if (test)
			{
				System.out.println("la checkbox a ÈtÈ sÈlectionnÈe");
			}
			articleDao.ajouter(article);

			request.setAttribute("articles", articleDao.lister());
		}
		// examen des articles √† supprimer √† partir des id d'articles dans le Dao et la valeur de la checkbox
		List<Article> articles = articleDao.lister();
		for (int i=articles.size()-1;i >= 0 ;i--) {
			// on r√©cup√®re les informations dans la page jsp concernant le status
			//boolean results = (request.getParameter(String.valueOf(i))!=null);
			String[] valeurs = request.getParameterValues(String.valueOf(i));

			if ((valeurs!=null)&&(valeurs.length!=0))
			{
				//elements √† supprimer dans la base
				//r√©cup√©ration de l'article
				Article monarticle = articles.get(i);
				articleDao.supprimer(monarticle);
			}
		}
		

		doGet(request, response);
	}

}