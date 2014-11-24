/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package novelcobbler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author nash
 */
public class Sentence implements Serializable {
    private String sentence;
    private final int wordCount;
    private final ArrayList<Tag> tags;
    
    public Sentence(String s) {
        sentence = s;
        checkSyntax();
        System.out.println("Sentence: " + get());
        wordCount = countWords();
        System.out.println("Word count: " + wordCount);
        tags = new ArrayList<Tag>();       
    }
    
    public Sentence(String s, ArrayList<Tag> t) {
        sentence = s;
        checkSyntax();
        wordCount = countWords();
        tags = t;
    }
    
    public String get() {
        return sentence;
    }
    
    public int getWordCount() {
        return wordCount;
    }
    
    public ArrayList<Tag> getTags() {
        return tags;
    }
    
    public void addTag(Tag t) {
        tags.add(t);
    }
    
    public boolean containsTag(Tag t) {
        for(int i = 0; i < tags.size(); i++) {
            if(tags.get(i).equals(t)) {
                return true;
            }
        }
        return false;
    }
    
    private int countWords() {
        String[] words = sentence.split(" |\t|\n|\r|\f");
        System.out.println("words length: " + words.length);
        int temp = 0;
        
        for (String word : words) {
            if (!word.isEmpty()) {
                temp++;
            }
        }
        
        return temp;
    }
    
    public boolean isDialog() {
        return sentence.contains("\"");
    }
    
    private void checkSyntax() {
        sentence = sentence.trim();
        
        if(sentence.length() > 0 && 
                !Character.isUpperCase(sentence.charAt(0))) {
            char[] sent = sentence.toCharArray();
            
            sent[0] = Character.toUpperCase(sent[0]);
            sentence = new String(sent);
        }
        
        if(sentence.charAt(sentence.length() - 1) != '.' &&
           sentence.charAt(sentence.length() - 1) != '?' &&
           sentence.charAt(sentence.length() - 1) != '"' &&
           sentence.charAt(sentence.length() - 1) != '!') {
            sentence = sentence.concat(".");
        }
    }
    
    public String toString() {
        return get();
    }
}
