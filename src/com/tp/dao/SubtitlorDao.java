package com.tp.dao;

import java.util.List;

import com.tp.beans.Subtitlor;

public interface SubtitlorDao {
    void ajouter( Subtitlor subtitlor );
    
    
    List<Subtitlor> lister();
}