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
public class Paragraph {
    private ArrayList<Sentence> sentences;
    private ArrayList<Tag> tags;
    
    public Paragraph() {
        sentences = new ArrayList<Sentence>();
        tags = new ArrayList<Tag>();
    }
    
    public Paragraph(ArrayList<Sentence> s, ArrayList<Tag> t) {
        sentences = s;
        tags = t;
    }
    
    public Paragraph(ArrayList<Sentence> s) {
        sentences = s;
        tags = new ArrayList<Tag>();
        
        for(int i = 0; i < sentences.size(); i++) {
            ArrayList<Tag> curTags = sentences.get(i).getTags();
            
            for(int j = 0; j < curTags.size(); j++) {
                if(!containsTag(curTags.get(i))) {
                    addTag(curTags.get(i));
                }
            }
        }
    }
    
    public void addSentence(Sentence s) {
        sentences.add(s);
        
        for(int i = 0; i < s.getTags().size(); i++) {
            if(!containsTag(s.getTags().get(i))) {
                addTag(s.getTags().get(i));
            }
        }
    }
    
    public void addTag(Tag t) {
        tags.add(t);
    }
    
    public ArrayList<Tag> getTags() {
        return tags;
    }
    
    public int size() {
        return sentences.size();
    }
    
    public boolean containsTag(Tag t) {
        for(int i = 0; i < tags.size(); i++) {
            if(tags.get(i).equals(t)) {
                return true;
            }
        }
        
        return false;
    }
    
    public String toString() {
        String temp = "\t";
        
        for(int i = 0; i < sentences.size(); i++) {
            temp = temp.concat(sentences.get(i).get());
            
            if(i != sentences.size() - 1) {
                if(sentences.get(i).isDialog()) {
                    temp = temp.concat("\n\t");
                } else {
                    temp = temp.concat(" ");
                }
            }
        }
        
        return temp;
    }
    
    public int getWordCount() {
        int temp = 0;
        
        for (Sentence sentence : sentences) {
            temp += sentence.getWordCount();
        }
        
        return temp;
    }
}
