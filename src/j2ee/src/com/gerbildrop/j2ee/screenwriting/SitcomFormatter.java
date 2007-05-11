/*
 * Copyright (c) 2003-2006, GerbilDrop Java Utilities
 * http://gerbildrop.com
 * http://sourceforge.net/projects/gerbildrop
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the Gerbildrop, GDJU, Gerbildrop Game Engine, Austin, StandTrooper, nor the
 * names of its contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS &quot;AS IS&quot;
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Copyright (c) 2006, Your Corporation. All Rights Reserved.
 */

package com.gerbildrop.j2ee.screenwriting;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.gerbildrop.util.ListUtil;
import com.gerbildrop.util.StringUtil;

/**
 * reads in a file specified and parses it to display it in the correct screenplay/script format
 *
 * @author timo
 * @version 1.0
 * @since Mar 1, 2005 -- 4:16:25 PM
 */
public class SitcomFormatter {

    // the formatter should be able to have tools to format just as if it were in msword.
    // characters = formatCharactersFromRequest(request.getParameter("characters"));// chars;
    // automatic scene numbering/lettering
    // automatic act numbering

    /**
     * static variables to represent the returns
     */
    private static final int NOTES = 1;
//    private static final int TITLE = 2;
    private static final int FADES = 3;
    private static final int CUTS = 4;
    private static final int LOGLINE = 5;
    private static final int PARENTHETICAL = 6;
    private static final int CHARACTER = 7;
    private static final int DIALOG = 8;
    private static final int MONTAGE = 9;
    private static final int DUAL_DIALOG = 10;
    private static final int ACT_SCENE = 11;

    /**
     * DIRECTIONALS
     */
    private static List directionalList = new ArrayList();
    private static List fadesList = new ArrayList();
    private static List cutsList = new ArrayList();
    private static List montageList = new ArrayList();
    private static List locationsList = new ArrayList();
    private static List customExclusionsList = new ArrayList();
    private static List actSceneList = new ArrayList();

    /**
     * custom variables reset everytime by the application
     */
    private List charactersList = new ArrayList();
    private List customCutsList = new ArrayList();
    private List customDirectionalList = new ArrayList();
    private List customFadesList = new ArrayList();

    static {
        directionalList.add("V.O.");
        directionalList.add("O.C.");
        directionalList.add("O.S.");
        directionalList.add("CONT");
        directionalList.add("CONTD");
        directionalList.add("CONT'D");
        directionalList.add("T.V.");
        directionalList.add("ON T.V.");
        directionalList.add("ON TV");
        directionalList.add("TV");

        fadesList.add("FADE IN:");
        fadesList.add("FADE OUT:");
        fadesList.add("FADE TO WHITE:");
        fadesList.add("FADE TO BLACK:");
        fadesList.add("SLAM CUT:");
        fadesList.add("BACK TO:");
        fadesList.add("FLASH CUT:");
        fadesList.add("FLASHCUT:");
        fadesList.add("SLAM CUT BACK TO");
        fadesList.add("INTERCUT");
        fadesList.add("VFX:");
        fadesList.add("SFX:");
        fadesList.add("ESTABLISHING SHOT");

        cutsList.add("CUT TO:");
        cutsList.add("DISSOLVE TO:");
        cutsList.add("RESET TO:");

        montageList.add("MONTAGE");
        montageList.add("SERIES OF SHOTS");

        locationsList.add("INT.");
        locationsList.add("EXT.");

        customExclusionsList.add("N/A");
        customExclusionsList.add("NA");

        actSceneList.add("ACT");
        actSceneList.add("SCENE");
        actSceneList.add("COLD OPEN");
        actSceneList.add("TAG");
        actSceneList.add("SHOW");
    }

    /**
     * custom variables to use throughout formatting process they are statics because each line is run through the
     * steps, and these may persist over multiple lines they are handled as switches inside the code.
     */
    private boolean isDialog = false;
    private int checker = -1;
    private boolean isAnotherLine = false;
    private boolean isSeriesOfShots = false;
    private boolean isMontage = false;
    private char seriesOfShotsNumber = 'a';
    private boolean isDualDialog = false;
    private List dualDialog = new ArrayList();

    public static String readFileFromList(List lst) {
        int i = 0;
        StringBuffer sb = new StringBuffer();
        SitcomFormatter sf = new SitcomFormatter();

        int size = lst.size();
        for (int j = 0; j < size; j++) {
            String inner = (String) lst.get(j);
            if (i <= 4) {//we're not doing the title at the top of the page for the sitcom format
                sf.isAnotherLine = false;
                switch (i) {
                    case 0: // add custom characters
                        if (inner.length() >= 13) {
                            //take the line and parse through the delimited values and add them to a List
                            StringUtil.parseDelimitedString(inner.substring(12), ",", true, sf.charactersList);
                        }
                        break;
                    case 1: // add custom fades
                        if (inner.length() >= 8) {
                            //take the line and parse through the delimited values and add them to a List
                            StringUtil.parseDelimitedString(inner.substring(7), ",", true, sf.customFadesList);
                        }
                        break;
                    case 2: // add custom cuts
                        if (inner.length() >= 7) {
                            //take the line and parse through the delimited values and add them to a List
                            StringUtil.parseDelimitedString(inner.substring(6), ",", true, sf.customCutsList);
                        }
                        break;
                    case 3: // add custom directionals
                        if (inner.length() >= 15) {
                            //take the line and parse through the delimited values and add them to a List
                            StringUtil.parseDelimitedString(inner.substring(14), ",", true, sf.customDirectionalList);
                        }
                        break;
                    case 4: // add the title
                        //    if(inner.length() >= 8) {
                        //        sb.append(sf.formatHTML("title", "\"" + inner.substring(7) + "\"") + "\n");
                        //    }
                        break;
                }

                i++;
            } else {
                sb.append(sf.formatLine(inner)).append("\n");
            }
        }

        return sb.toString();
    }

    boolean isCharacterNext = false;
    boolean isFirst = true;

    /**
     * the bulk of the formatting checks are done here format each line according to what type of item it is
     *
     * @param toFormat
     *
     * @return formatted line
     */
    private String formatLine(String toFormat) {
        StringBuffer returner = new StringBuffer();
        String toFormatUpper = toFormat.trim().toUpperCase();

        if (toFormatUpper != null) {
            logicCheck(toFormat, toFormatUpper, returner);

            switch (checker) {
                case 100:
                    if (isDualDialog) {
                        returner.append(formatDualDialog());
                    } else {
                        returner.append(" ");
                    }

                    isDialog = false;
                    isAnotherLine = false;
                    isMontage = false;
                    isDualDialog = false;
                    break;
                case ACT_SCENE:

                    String longBr = "<br><br><br><br><br><br><br><br><br><br><br><br><br><br>";
                    boolean isAct = (StringUtil.indexOf(toFormatUpper, "ACT")
                            || StringUtil.indexOf(toFormatUpper, "COLD OPEN")
                            || StringUtil.indexOf(toFormatUpper, "TAG"));
                    boolean isEnd = StringUtil.indexOf(toFormatUpper, "END OF");

                    if (!isEnd && isAct) {
                        returner.append("<br clear=all style='page-break-before:always'>");
                    }

                    if (!isEnd && isAct) {
                        returner.append(longBr);
                    } else if (!StringUtil.indexOf(toFormatUpper, "SCENE")
                            && !StringUtil.indexOf(toFormatUpper, "SHOW")) {
                        returner.append("<br><br>");
                    }

                    returner.append(formatHTML("ActScene", toFormatUpper));

                    returner.append("<br><br>");

                    break;
                case FADES:
                    returner.append(formatHTML("Fades", toFormatUpper));
                    if (StringUtil.indexOf(toFormat, "END of") || StringUtil.indexOf(toFormat, "end of")) {
                        returner.append("<br clear=all style='page-break-after:always'>");
                    } else {
                        returner.append("<br>");
                    }

                    isDialog = false;
                    break;
                case CUTS:
                    returner.append(formatHTML("Cuts", toFormatUpper));
                    isDialog = false;
                    break;
                case LOGLINE:
                    returner.append(formatHTML("Logline", toFormatUpper));
                    isDialog = false;
                    isCharacterNext = true;
                    break;
                case CHARACTER:
                    returner.append(formatHTML("Character", toFormatUpper));
                    //returner.append("<br>");
                    isDialog = true;
                    break;
                case PARENTHETICAL:
                case DIALOG:
                    if (isCharacterNext) {
                        returner.append(formatHTML("SceneCharacters", getOutputFormat(toFormat)));
                        //returner.append("<br>");
                        isCharacterNext = false;
                    } else {
                        returner.append(formatHTML("Dialog", getOutputFormat(toFormat)));
                        //returner.append("<br>");
                        isDialog = true;
                    }
                    break;
                case NOTES:
                    returner.append("<br>").append(formatHTML("note", toFormatUpper));
                    isDialog = false;
                    break;
                case DUAL_DIALOG:
                    if (!toFormatUpper.equals("DUAL DIALOG")) {
                        dualDialog.add(toFormat);
                    }

                    isDualDialog = true;
                    isAnotherLine = true;
                    isMontage = false;
                    isDialog = false;
                    break;
                case MONTAGE:
                    if (!isAnotherLine) {
                        returner.append("<br>").append(formatHTML("Fades", " "));
                        returner.append(formatHTML("Fades", toFormatUpper));
                    } else {
                        returner.append(formatMontage(toFormat));
                    }

                    isAnotherLine = true;
                    isMontage = true;
                    isDialog = false;
                    break;
                default:
                    isDialog = true;
                    returner.append("<br>").append(formatHTML("Description", toFormat));
                    isAnotherLine = true;
                    break;
            }
        } else {
            returner.append(" ");
        }

        return returner.toString();
    }

    /**
     * run through a series of line checks to determine exactly which type of item we want to format this line for
     *
     * @param toFormat
     *
     * @return a constant variable related to the type of item we are going to format this with
     */
    private int checkType(String toFormat) {
        int returner = -1;
        int fades = hasFades(toFormat);

        if (fades == FADES) {
            returner = FADES;
        } else if (fades == NOTES) {
            returner = NOTES;
        } else if (isDualDialog(toFormat)) {
            returner = DUAL_DIALOG;
        } else if (hasCuts(toFormat)) {
            returner = CUTS;
        } else if (isLogline(toFormat)) {
            returner = LOGLINE;
        } else if (isParenthetical(toFormat)) {
            returner = PARENTHETICAL;
        } else if (isCharacter(toFormat)) {
            returner = CHARACTER;
        } else if (isMontage(toFormat)) {
            returner = MONTAGE;
        } else if (isActScene(toFormat)) {
            returner = ACT_SCENE;
        } else if (checkForNull(toFormat)) {
            returner = 100;
        }

        //System.out.println("returner: " + returner);
        return returner;
    }

    /**
     * loops through list to pull out information
     *
     * @param toFormat
     * @param lister
     *
     * @return
     */
    private boolean checker(String toFormat, List lister) {
        boolean returner = false;

        final int size = lister.size();

        for (int i = 0; i < size; i++) {
            String _temp = (String) lister.get(i);

            if (StringUtil.indexOf(toFormat, _temp) && !isCustomExclusion(toFormat)) {
                returner = true;
                break;
            }
        }

        return returner;
    }

    /**
     * does this line contain any predefined CUTS or any custom CUTS predefined cuts are from {@link
     * ScriptFormatter#cutsList}
     *
     * @param toFormat
     *
     * @return true if this contains either predefined CUTS or custom CUTS
     */
    private boolean hasCuts(String toFormat) {
        return checker(toFormat, ListUtil.appendList(new List[]{cutsList, customCutsList}));
    }

    /**
     * does this line contain any predefined FADES or any custom FADES predefined FADES are from {@link
     * ScriptFormatter#fadesList}
     *
     * @param toFormat
     *
     * @return true if this contains either predefined FADES or custom FADES
     */
    private int hasFades(String toFormat) {
        int returner = -1;

        boolean notes = StringUtil.indexOf(toFormat, "[NOTE:]");

        if (checker(toFormat, ListUtil.appendList(new List[]{fadesList, customFadesList})) && !notes) {
            returner = FADES;
        } else if (notes) {
            returner = NOTES;
        }

        return returner;
    }

    /**
     * determines if the line contains INT. or EXT.
     *
     * @param toFormat
     *
     * @return true if this contains INT. or EXT.
     */
    private boolean isLogline(String toFormat) {
        return checker(toFormat, locationsList);
    }

    /**
     * @param toFormat
     *
     * @return true if this contains ( or )
     */
    private boolean isParenthetical(String toFormat) {
        boolean returner = false;

        ////find out if the beginning paren and end paren are in the line
        int indexOfBeginParen = toFormat.indexOf("(");
        int indexOfEndParen = toFormat.indexOf(")");
        //
        ////if either are AND the beginParen is the first character OR the endParen is the last character
        ////then we're dealing with a parenthetical -- in a real world || toFormat.substring(toFormat.length() -1).equals(")")
        ////for formatting text, we want only the initial one
        if ((indexOfBeginParen > -1
                || indexOfEndParen > -1)
                && (toFormat.substring(0, 1).equals("(")
        )) {
            returner = true;
        }

        return returner;
    }

    /**
     * does this line contain any predefined CHARACTERS or any custom CHARACTERS predefined CHARACTERS are from {@link
     * ScriptFormatter#charactersList}
     *
     * @param toFormat
     *
     * @return true if this contains either predefined CHARACTERS or custom CHARACTERS
     */
    private boolean isCharacter(String toFormat) {
        boolean returner = false;

        final int size = charactersList.size();

        for (int i = 0; i < size; i++) {
            String _character = (String) charactersList.get(i);

            if ((StringUtil.isEqual(toFormat, _character)
                    || (StringUtil.indexOf(toFormat, _character)
                    && (isDirectional(toFormat) || (StringUtil.indexOf(toFormat, "/")
                    && !StringUtil.indexOf(toFormat, ",")))))
                    && toFormat.indexOf("CONTINUE") == -1 && !StringUtil.indexOf(toFormat, "<")) {
                returner = true;
                break;
            }
        }

        return returner;
    }

    /**
     * does this line contain any predefined DIRECTIONAL or any custom DIRECTIONAL predefined DIRECTIONAL are from
     * {@link ScriptFormatter#directionalList} custom DIRECTIONAL are from {@link ScriptFormatter#directionalList}
     *
     * @param toFormat
     *
     * @return true if this contains either predefined DIRECTIONAL or custom DIRECTIONAL
     */
    private boolean isDirectional(String toFormat) {
        boolean returner = checker(toFormat, ListUtil.appendList(new List[]{directionalList, customDirectionalList}));
        if (returner) {
            if (StringUtil.indexOf(toFormat, "(") && StringUtil.indexOf(toFormat, ")")) {
                //we're still golden
            } else {
                returner = false;
            }
        }

        return returner;
    }

    /**
     * determines if the line contains the text DUAL DIALOG to let us handle dual dialog in the script
     *
     * @param toFormat
     *
     * @return true if this contains the text DUAL DIALOG
     */
    private boolean isDualDialog(String toFormat) {
        boolean returner = false;

        if (StringUtil.indexOf(toFormat, "DUAL DIALOG")) {
            returner = true;
            isDualDialog = true;
        }

        return returner;
    }

    /**
     * does this line contain any predefined MONTAGE listings predefined MONTAGE listings are from {@link
     * ScriptFormatter#montageList}
     *
     * @param toFormat
     *
     * @return true if this contains either predefined MONTAGE listings
     */
    private boolean isMontage(String toFormat) {
        boolean returner;

        returner = checker(toFormat, montageList);

        if (returner) {
            if (toFormat.equals(montageList.get(1))) {
                isSeriesOfShots = true;
            }

            isMontage = true;
        }

        return returner;
    }

    /**
     * ensure that the line isn't blank
     *
     * @param toFormat
     *
     * @return true if the line is blank
     */
    private boolean checkForNull(String toFormat) {
        return "".trim().equals(toFormat.trim());
    }

    /**
     * loop through the actual dualdialog list and format the dual dialog accordingly {@link
     * ScriptFormatter#dualDialog}
     *
     * @return formatted string of the dualDialog list
     */
    private String formatDualDialog() {
        StringBuffer sb = new StringBuffer();

        String dialogs[] = (String[]) dualDialog.toArray(new String[dualDialog.size()]);
        sb.append("<table border=\"0\">");
        sb.append("    <tr valign=\"top\">");
        sb.append("        <td>");

        StringBuffer temp1 = new StringBuffer();
        StringBuffer temp2 = new StringBuffer();

        int j = 0;

        for (int i = 0; i < dialogs.length; i++) {
            StringTokenizer tokens = new StringTokenizer(dialogs[i], "|");

            while (tokens.hasMoreTokens()) {
                String token = tokens.nextToken().trim();
                if (isParenthetical(token)) {
                    if (j == 0) {
                        temp1.append("<p class=\"dualParentheticals\">");
                        temp1.append(token);
                        temp1.append("</p>");
                        j = 1;
                    } else {
                        temp2.append("<p class=\"dualParentheticals\">");
                        temp2.append(token);
                        temp2.append("</p>");
                        j = 0;
                    }
                } else if (isCharacter(token)) {
                    if (j == 0) {
                        temp1.append("<p class=\"sidebyside\">");
                        temp1.append(token);
                        temp1.append("</p>");
                        j = 1;
                    } else {
                        temp2.append("<p class=\"sidebyside\">");
                        temp2.append(token);
                        temp2.append("</p>");
                        j = 0;
                    }
                } else {
                    if (j == 0) {
                        temp1.append("<p class=\"dualDialogs\">");
                        temp1.append(token);
                        temp1.append("</p>");
                        j = 1;
                    } else {
                        temp2.append("<p class=\"dualDialogs\">");
                        temp2.append(token);
                        temp2.append("</p>");
                        j = 0;
                    }
                }
            }
        }

        sb.append(temp1);
        sb.append("</td><td>");
        sb.append(temp2);
        sb.append("        </td>");
        sb.append("    </tr>");
        sb.append("</table>");

        isDualDialog = false;
        dualDialog.clear();

        return sb.toString();
    }

    /**
     * format the montage lines according to predefined standards {@link ScriptFormatter#montageList}
     *
     * @param toFormat
     *
     * @return formatted montage lines
     */
    private String formatMontage(String toFormat) {
        StringBuffer sb = new StringBuffer();

        if (isSeriesOfShots) {
            String temp = ("" + seriesOfShotsNumber).toUpperCase();
            sb.append("<p class=seriesOfShots style='margin-left:1.0in;text-indent:-.25in;'>");
            sb.append(temp).append(") ");
            sb.append(toFormat);
            sb.append("</p>");
            seriesOfShotsNumber++;
        } else {
            sb.append("<p class=seriesOfShots style='margin-left:.5in;text-indent:-.25in;mso-list: l20 level1 lfo57;tab-stops:list .5in'><span style='font-family:Symbol'>-</span>       ");
            sb.append(toFormat);
            sb.append("</p>");
        }

        return sb.toString();
    }

    /**
     * generic pass through.  Send in a style and the text to go between the paragraph &lt;p&gt; tags and it will insert
     * the format you've requested
     *
     * @param style
     * @param insert
     *
     * @return formatted line
     */
    private String formatHTML(String style, String insert) {
        StringBuffer sb = new StringBuffer();
        if (!isAnotherLine) {
            sb.append("<p class=\"");
            sb.append(style);
            sb.append("\">");
        } else {
            sb.append(" ");
        }

        sb.append(insert);

        if (!style.equals("Description") && !isAnotherLine) {
            sb.append("</p>");
        }

        return sb.toString();
    }

    /**
     * custom exclusions are items that are used for reference, but we don't want them to be considered when deciding on
     * formatting
     *
     * @param toFormat
     *
     * @return true if exclusions are included in the string
     */
    private boolean isCustomExclusion(String toFormat) {
        //using this line instead of the other code below causes a 500 servlet exception StackOverflowError
//        return checker(toFormat, customExclusionsList);
        boolean returner = false;

        final int size = customExclusionsList.size();

        for (int i = 0; i < size; i++) {
            String customExclusion = (String) customExclusionsList.get(i);

            if (StringUtil.indexOf(toFormat, customExclusion)) {
                returner = true;
                break;
            }
        }

        return returner;
    }

    private boolean isActScene(String toFormat) {
        return checker(toFormat, actSceneList);
    }

    private void logicCheck(String toFormat, String toFormatUpper, StringBuffer returner) {
        if (isAnotherLine && !isDualDialog) {
            if (!isMontage && checkForNull(toFormat)) {
                returner.append("</p><br>");
                isAnotherLine = false;
            } else if (isMontage && checkForNull(toFormat)) {
                checker = checkType(toFormatUpper);
                isAnotherLine = false;
                isSeriesOfShots = false;
                isMontage = false;
                seriesOfShotsNumber = 'a';
            } else if (isMontage) {
                checker = MONTAGE;
            }
        } else {
            if (isDualDialog) {
                if (!checkForNull(toFormat)) {
                    checker = DUAL_DIALOG;
                } else {
                    checker = 100;
                }
            }
        }

        if (isDialog && !isDualDialog) {
            if (isParenthetical(toFormatUpper)) {
                checker = PARENTHETICAL;
            } else if (checkForNull(toFormat)) {
                isDialog = false;
                checker = checkType(toFormatUpper);
            } else {
                checker = DIALOG;
            }
        } else if (!isMontage && !isDualDialog) {
            checker = checkType(toFormatUpper);
        }
    }

    private String getOutputFormat(String toFormat) {
        int beginParen;
        int endParen;
        String returner;
        StringTokenizer tokens = new StringTokenizer(toFormat, " ");
        StringBuffer sb = new StringBuffer();

        if (StringUtil.indexOf(toFormat, "(") || StringUtil.indexOf(toFormat, ")")) {
            boolean hasParen = false;
            while (tokens.hasMoreTokens()) {
                String token = tokens.nextToken();

                if (StringUtil.indexOf(token, "(") && StringUtil.indexOf(token, ")")) {
                    beginParen = token.indexOf("(");
                    endParen = token.indexOf(")");
                    sb.append("( ").append(token.substring(beginParen + 1, endParen).toUpperCase()).append(" )  ");
                } else if (StringUtil.indexOf(token, "(")) {
                    beginParen = token.indexOf("(");
                    sb.append("( ").append(token.substring(beginParen + 1).toUpperCase()).append(" ");
                    hasParen = true;
                } else if (StringUtil.indexOf(token, ")")) {
                    endParen = token.indexOf(")");
                    sb.append(token.substring(0, endParen).toUpperCase()).append(" )  ");
                    hasParen = false;
                } else {
                    if (hasParen) {
                        sb.append(token.toUpperCase()).append(" ");
                    } else {
                        sb.append(token).append(" ");
                    }
                }
            }
//            sb.append(toFormat.toUpperCase());
        } else {
            sb.append(toFormat);
        }

        returner = sb.toString();

        return returner;
    }
}