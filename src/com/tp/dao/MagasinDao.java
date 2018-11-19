package com.tp.dao;

import java.util.List;

import com.tp.beans.Magasin;

public interface MagasinDao {
	void ajouter(Magasin magasin);
	List<Magasin> lister();
	void supprimer(Magasin magasin);
}
