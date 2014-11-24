/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package novelcobbler;

import java.io.Serializable;

/**
 *
 * @author nash
 */
public class Tag implements Serializable {
    private final String tag;
    
    public Tag(String t) {
        tag = t.toLowerCase();
    }
    
    public String getTag() {
        return tag;
    }
    
    public String toString() {
        return getTag();
    }

    public boolean equals(Tag t) {
        return tag.equals(t.getTag());
    }
}
