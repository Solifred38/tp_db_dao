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
	private MagasinDao magasinDao;

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.articleDao = daoFactory.getArticleDao();
		this.magasinDao = daoFactory.getMagasinDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Entree dans doGet avant chargement articles");
		request.setAttribute("magasins", magasinDao.lister());
		String tri=request.getParameter("trimagasins");
		
		if ((tri!=null)&&!tri.equals("--selectionner--"))
		{
			System.out.println("magasin selectionné "+tri);
			request.setAttribute("articles", articleDao.filtrer(tri));
		}
		else 
			request.setAttribute("articles", articleDao.lister());
		this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	// recuperer la valeur du combobox de tri
	
		// On ajoute un article si les informations ont été rentrée sur le formulaire
		AjouterUnArticle(request);
		// on supprime les articles qui ont été cochés dans la table
		SupprimerArticles(request);
	
		doGet(request, response);
	}

	void AjouterUnArticle(HttpServletRequest request) {
		Article article = new Article();
		if (request.getParameter("nom") != null && request.getParameter("nom") != "") {
			article.setNom(request.getParameter("nom"));
			article.setDescription(request.getParameter("description"));
			if (request.getParameter("prix") != "")
				article.setPrix(Float.parseFloat(request.getParameter("prix")));
			// retrouver l'id du magasin à partir du nom
			//etape 1 on recupere le nom;
			String nom = request.getParameter("choixmagasin");
			articleDao.ajouter(article,nom);

			request.setAttribute("articles", articleDao.lister());	
		}
		
	}

	void SupprimerArticles(HttpServletRequest request) {
		// examen des articles à supprimer à partir des id d'articles dans le Dao et la
		// valeur de la checkbox
		List<Article> articles = articleDao.lister();
		for (int i = articles.size() - 1; i >= 0; i--) {
			// on récupère les informations dans la page jsp concernant le status
			// boolean results = (request.getParameter(String.valueOf(i))!=null);
			String[] valeurs = request.getParameterValues(String.valueOf(i));

			if ((valeurs != null) && (valeurs.length != 0)) {
				// elements à supprimer dans la base
				// récupération de l'article
				Article monarticle = articles.get(i);
				articleDao.supprimer(monarticle);
			}
		}
	}

}