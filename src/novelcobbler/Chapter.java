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
public class Chapter {
    private static String nl = System.getProperty("line.separator");
    private ArrayList<Paragraph> paragraphs;
    private ArrayList<Tag> tags;
    
    public Chapter() {
        paragraphs = new ArrayList<Paragraph>();
        tags = new ArrayList<Tag>(); 
    }
    
    public void addParagraph(Paragraph p) {
        paragraphs.add(p);
        
        ArrayList<Tag> pTags = p.getTags();
        
        for(Tag pTag : pTags) {
            if(!containsTag(pTag)) {
                tags.add(pTag);
            }
        }
    }
    
    public void addTag(Tag t) {
        tags.add(t);
    }
    
    public boolean containsTag(Tag t) {
        for(Tag tag : tags) {
            if(tag.equals(t)) {
                return true;
            }
        }
        
        return false;
    }
    
    public ArrayList<Tag> getTags() {
        return tags;
    }
    
    public int size() {
        return paragraphs.size();
    }
    
    public String toString() {
        String temp = "";
        
        for(int i = 0; i < paragraphs.size(); i++) {
            temp = temp.concat(paragraphs.get(i).toString());
            
            if(i != paragraphs.size() - 1) {
                temp = temp.concat(nl);
            }
        }
        
        //System.out.println("Chapter: " + temp);
        return temp;
    }
    
    public int getWordCount() {
        int temp = 0;
        
        for(Paragraph para : paragraphs) {
            temp += para.getWordCount();
        }
        
        return temp;
    }
}
