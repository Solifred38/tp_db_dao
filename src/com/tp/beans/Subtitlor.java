
package com.tp.beans;

import java.sql.Time;

public class Subtitlor {
    private String ligne;
    private String langue;
    private String lignetraduite="";
	public String getLignetraduite() {
		return lignetraduite;
	}
	public void setLignetraduite(String lignetraduite) {
		this.lignetraduite = lignetraduite;
	}
	public String getLangue() {
		return langue;
	}
	public void setLangue(String langue) {
		this.langue = langue;
	}
	public String getLigne() {
		return ligne;
	}
	public void setLigne(String ligne) {
		this.ligne = ligne;
	}

}