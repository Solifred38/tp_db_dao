package com.tp.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SubtitlesHandler {
	private ArrayList<String> originalSubtitles = null;
	private ArrayList<String> translatedSubtitles = null;
	public SubtitlesHandler() {
		
	}
	public SubtitlesHandler(String fileName) {
		originalSubtitles = new ArrayList<String>();
		translatedSubtitles = new ArrayList<String>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = br.readLine()) != null) {
				originalSubtitles.add(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getSubtitles() {
		return originalSubtitles;
	}
	public ArrayList<String> getTranslatedSubtitles(){
		return translatedSubtitles;
	}
	public void setTranslatedSubtitles(ArrayList<String> pTranslatedSub){
		translatedSubtitles=pTranslatedSub;
	}
	
	
	
}
