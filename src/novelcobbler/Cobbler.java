/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package novelcobbler;

import java.util.ArrayList;

/**
 *
 * @author nash
 */
public class Cobbler {
    private static final double PARA_COEF = 1.2;
    private static final int PARA_SHIFT = 1;
    
    private static final double CHAPT_COEF = 0.4;
    private static final int CHAPT_SHIFT = 0;
    
    private static final int TAG_SHIFT = 1;
    
    private static final String CHAPTER_HEAD = "\t\t\tCHAPTER ";
    
    public static String cobble(String title, ArrayList<Sentence> s) {
        ArrayList<Sentence> sents = new ArrayList<Sentence>();
        ArrayList<Paragraph> paras = new ArrayList<Paragraph>();
        ArrayList<Chapter> chapters = new ArrayList<Chapter>();
        
        for(Sentence sent : s) {
            sents.add(sent);
        }
        
        while(sents.size() > 0) {
            int index = (int)(Math.random() * sents.size());
            
            Sentence tSent = sents.remove(index);
            
            boolean placed = false;
            for(int i = 0; i < paras.size() && !placed; i++) {
                if(Math.random() < paraChance(tSent, paras.get(i))) {
                    paras.get(i).addSentence(tSent);
                    placed = true;
                }
            }
            
            if(!placed) {
                Paragraph temp = new Paragraph();
                temp.addSentence(tSent);
                paras.add(temp);
            }
        }
        
        while(paras.size() > 0) {
            int index = (int)(Math.random() * paras.size());
            
            Paragraph tPara = paras.remove(index);
            
            boolean placed = false;
            for(int i = 0; i < chapters.size() && !placed; i++) {
                if(Math.random() < chapterChance(tPara, chapters.get(i))) {
                    chapters.get(i).addParagraph(tPara);
                    placed = true;
                }
            }
            
            if(!placed) {
                Chapter temp = new Chapter();
                temp.addParagraph(tPara);
                chapters.add(temp);
            }
        }
        
        String novel = "\t\t\t\t" + title + "\n\n";
        
        for(int i = 0; i < chapters.size(); i++) {
            novel = novel.concat(CHAPTER_HEAD + (i + 1) + "\n");
            novel = novel.concat(chapters.get(i).toString());
            
            if(i != chapters.size() - 1) {
                novel = novel.concat("\n");
            }
        }
        
        return novel;
    }
    
    public static double paraChance(Sentence s, Paragraph p) {
        int tagMatches = 0;
        
        for(Tag tg : s.getTags()) {
            if(p.containsTag(tg)) {
                tagMatches++;
            }
        }
        
        double tagChance = 
                (tagMatches + 1.0) / (1.0 + tagMatches + TAG_SHIFT);
        
        return tagChance / (p.size() * PARA_COEF + PARA_SHIFT);
    }
    
    public static double chapterChance(Paragraph p, Chapter c) {
        int tagMatches = 0;
        
        for(Tag tg : p.getTags()) {
            if(c.containsTag(tg)) {
                tagMatches++;
            }
        }
        
        double tagChance = 
                (tagMatches + 1.0) / (1.0 + tagMatches + TAG_SHIFT);
        
        return tagChance / (c.size() * CHAPT_COEF + CHAPT_SHIFT);
    }
}
