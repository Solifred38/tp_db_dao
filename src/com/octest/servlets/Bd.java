package com.octest.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp.beans.Article;
import com.tp.beans.Magasin;
import com.tp.dao.*;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Bd")
public class Bd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleDao articleDao;
	private MagasinDao magasinDao;
	private String Operation="";

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.articleDao = daoFactory.getArticleDao();
		this.magasinDao = daoFactory.getMagasinDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (Operation.equals("")||Operation.equals("Requete_Principale")||Operation.equals("Nouveau_Magasin")||Operation.equals("Requete_Principale_Magasins")) {
			System.out.println("Entree dans doGet avant chargement articles");
			request.setAttribute("magasins", magasinDao.lister());
			String tri=request.getParameter("filtre_magasins");
			
			if ((tri!=null)&&!tri.equals("--selectionner--"))
			{
				System.out.println("magasin selectionné "+tri);
				request.setAttribute("articles", articleDao.filtrer(tri));
			}
			else 
				request.setAttribute("articles", articleDao.lister());
			this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
			
		}
		else if (Operation.equals("Gerer_Magasins")) {
			
			// entree dans la gestion des magasins
			request.setAttribute("magasins", magasinDao.lister());
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/magasin.jsp").forward(request, response);
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// on cherche quel commit a ete effectue : creation de magasin, ajout article ...
		 Enumeration <String> parametres = request.getParameterNames();
	     while(parametres.hasMoreElements())
	     {
	         String param = parametres.nextElement();
	         if (param.equals("Gerer_Magasins")||param.equals("Requete_Principale")||param.equals("Requete_Principale_Magasins")) Operation=param;
	     }

		// gestion des magasins (suppression et ajout de magasin)
		GererMagasin(request,Operation);

		// On ajoute un article si les informations ont ete rentree sur le formulaire
		AjouterUnArticle(request);
		// on supprime les articles qui ont été cochés dans la table
		SupprimerArticles(request);
	
		doGet(request, response);
	}

	void AjouterUnArticle(HttpServletRequest request) {
		if (request.getParameter("nom") != null && request.getParameter("nom") != "") {
			Article article = new Article();
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
	void GererMagasin(HttpServletRequest request, String Operation) {
		if (request.getParameter("nom_magasin") != null && request.getParameter("nom_magasin") != "") {
			Magasin magasin = new Magasin();
			magasin.setNom(request.getParameter("nom_magasin"));
			magasinDao.ajouter(magasin);

			request.setAttribute("magasins", magasinDao.lister());	
		}

		if (Operation.equals("Requete_Principale_Magasins")) {
			// examen des articles à supprimer à partir des id d'articles dans le Dao et la
			// valeur de la checkbox
			List<Magasin> magasins = magasinDao.lister();
			for (int i = magasins.size() - 1; i >= 0; i--) {
				// on récupère les informations dans la page jsp concernant le status
				// boolean results = (request.getParameter(String.valueOf(i))!=null);
				String[] valeurs = request.getParameterValues(String.valueOf(i));

				if ((valeurs != null) && (valeurs.length != 0)) {
					// elements à supprimer dans la base
					// récupération de l'article
					Magasin monmagasin = magasins.get(i);
					magasinDao.supprimer(monmagasin);
				}
			}
			
		}
	}

}