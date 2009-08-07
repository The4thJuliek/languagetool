/* LanguageTool, a natural language style checker 
 * Copyright (C) 2006 Daniel Naber (http://www.danielnaber.de)
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
package de.danielnaber.languagetool.tagging.es;

import java.util.Locale;

import de.danielnaber.languagetool.tagging.BaseTagger;

/** Spanish Tagger
 * 
 * Based on FreeLing tagger dictionary 
 * and Spanish Wikipedia corpus tagged with FreeLing.
 * 
 * @author Marcin Milkowski
 */
public class SpanishTagger extends BaseTagger {

  public final String getFileName() {
    return "/resource/es/spanish.dict";    
  }
  
  public SpanishTagger() {
    super();
    setLocale(new Locale("es"));
  }
}
