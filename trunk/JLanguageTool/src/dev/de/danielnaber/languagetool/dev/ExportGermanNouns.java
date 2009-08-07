/* LanguageTool, a natural language style checker 
 * Copyright (C) 2005 Daniel Naber (http://www.danielnaber.de)
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */

/*
 * Created on 06.05.2007
 */
package de.danielnaber.languagetool.dev;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import morfologik.fsa.FSA;

/**
 * Export German nouns as a serialized Java HashSet, to be used
 * by jWordSplitter.  
 * 
 * @author Daniel Naber
 */
public class ExportGermanNouns {

  private static final String DICT_FILENAME = "/resource/de/german.dict";
  
  private ExportGermanNouns() {
  }
  
  private Set<String> getWords() throws IOException {
    FSA fsa = FSA.getInstance(this.getClass().getResourceAsStream(DICT_FILENAME), "iso-8859-1");
    String lastTerm = null;
    Set<String> set = new HashSet<String>();
    for (Iterator i = fsa.getTraversalHelper().getAllSubsequences(fsa.getRootNode()); i.hasNext();) {
      final byte [] sequence = (byte []) i.next();
      String output = new String(sequence, "iso-8859-1");
      if (output.indexOf("+SUB:") != -1 && output.indexOf(":ADJ") == -1) {
        String[] parts = output.split("\\+");
        String term = parts[0].toLowerCase();
        if (lastTerm == null || !lastTerm.equals(parts[0])) {
          //System.out.println(parts[0]);
          set.add(term);
        }
        lastTerm = term;
      }
    }
    return set;
  }
  
  private void serialize(Set<String> words, File outputFile) throws IOException {
    FileOutputStream fos = new FileOutputStream(outputFile);
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(words);
    oos.close();
    fos.close();
  }
  
  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.out.println("Usage: ExportGermanNouns <outputFile>");
      System.exit(1);
    }
    ExportGermanNouns prg = new ExportGermanNouns();
    Set<String> words = prg.getWords();
    prg.serialize(words, new File(args[0]));
  }
    
}
