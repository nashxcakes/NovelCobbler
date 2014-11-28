package novelcobbler;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nash
 */
public class SentenceParser {

    public static ArrayList<Sentence> parse(java.awt.Component par, String text) {
        ArrayList<Sentence> sentences = new ArrayList<Sentence>();
        //javax.swing.ProgressMonitor monitor = new javax.swing.ProgressMonitor(
        //        par, "Parsing Text", "Parsing Text", 0, text.length());

        //int progress = 0;
        
        while (text.length() > 0) {
            String[] popped = parseSentence(text);

            sentences.add(toSentence(popped[0]));
            text = popped[1];
            
            //progress += popped[0].length();
            //monitor.setProgress(progress);
        }

        return sentences;
    }

    public static String[] parseSentence(String text) {
        int index = -1;
        int temp = text.indexOf('.');

        if (temp < 0 || text.indexOf('!') < temp) {
            temp = text.indexOf('!');

            if (temp < 0 || text.indexOf('?') < temp) {
                temp = text.indexOf('?');

                if (temp < 0 || text.indexOf('\n') < temp) {
                    temp = text.indexOf('\n');

                    if (temp >= 0) {
                        index = temp;
                    }
                } else {
                    index = temp;
                }
            } else {
                index = temp;
            }
        } else {
            index = temp;
        }

        if (index >= 0 && index < text.length() - 1) {
            if (text.charAt(index + 1) == '\"') {
                index++;
            }
            index++;

            String tempString = text.substring(0, index).trim();
            text = text.substring(index);
            
            if(text.length() > 0 && text.charAt(0) == '\"') {
                text = text.substring(1);
                tempString = tempString.concat("\"");
            }
            
            String[] bundle = {tempString, text};
            return bundle;
        } else {
            String[] bundle = { text, "" };
            return bundle;
        }
    }

    public static Sentence toSentence(String s) {
        Sentence temp = new Sentence(s);
        ArrayList<Tag> tags = parseTags(s);
        //System.out.println("Parsed tags: " + tags.size());
        for (Tag t : tags) {
            temp.addTag(t);
        }

        return temp;
    }

    public static ArrayList<Tag> parseTags(String s) {
        ArrayList<Tag> tags = new ArrayList<Tag>();
        String[] words = s.split(" |\t|\n|\r|\f");

        //System.out.println("TAG PARSE: ");
        for (String word : words) {
            if (!word.isEmpty() && Character.isUpperCase(word.charAt(0))) {
                //System.out.println("|||" + word + "|||");
                word = word.replaceAll(
                        "\\.|\\,|\\!|\\?|\\-|\\\"|\\)|\\;|\\:|\\'|\\-", "");
                tags.add(new Tag(word));
            }
        }

        return tags;
    }
}
