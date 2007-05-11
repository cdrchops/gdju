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

package com.gerbildrop.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
    public static List readFile(String path, String fileName, boolean addBreaks) {
        List readFile = new ArrayList();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(path + fileName));
            for (String inner = in.readLine(); inner != null; inner = in.readLine()) {
                readFile.add(inner);
            }

            in.close();
        } catch (IOException ioe) {
            //ioe.printStackTrace();
            readFile.add(ioe.getMessage());
        }

        return readFile;
    }

    public static void appendFile(String filePath, String fileName, String extension, String content) {
        List lst = FileIO.readFile(filePath, fileName + extension, false);
        lst.add(content);

        writeFile(filePath, fileName + extension, lst);
    }

    public static void writeFile(String filePath, String fileName, List content) {
        try {
            File file = new File(filePath + fileName);
            FileOutputStream fout = new FileOutputStream(file);
            for (int i = 0; i < content.size(); i++) {
                String c = (String) content.get(i) + "\r";
                //System.out.println("c: " + c);
                fout.write(c.getBytes());
            }

            fout.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void writeFile(String filePath, String fileName, String extension, String content) {
        try {
            File file = new File(filePath + fileName + extension);
            int i = 'a';

            while (!createFile(file)) {
                file = new File(filePath + fileName + (char) i + extension);
                i++;
            }

            FileOutputStream fout = new FileOutputStream(file);
            fout.write(content.getBytes());
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
//            Debug.error(e);
        }
    }

    public static boolean createFile(File file) throws IOException {
        // Create file if it does not exist
        return file.createNewFile();
    }

    public static String readFileIn(String filePath, String fileName, boolean addBreaks) {
        StringBuffer sb = new StringBuffer();
        if (addBreaks) {
            sb.append("<b>").append(fileName).append("</b><br>");
        }

        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(filePath + fileName));

            for (String inner = in.readLine(); inner != null; inner = in.readLine()) {
                sb.append(inner);
                if (addBreaks) {
                    sb.append("<br>");
                }
            }

            in.close();

            if (addBreaks) {
                sb.append("<br><br>");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return sb.toString();
    }

    /**
     * read in a file and parse it to display the correct html formatting for the script
     *
     * @param filePath
     * @param fileName
     *
     * @return formatted script
     */
    public static List readFileIn(String filePath, String fileName) {
        BufferedReader in = null;

        List lst = new ArrayList();
        try {
            in = new BufferedReader(new FileReader(filePath + fileName));

            for (String inner = in.readLine(); inner != null; inner = in.readLine()) {
                lst.add(inner);
            }

            in.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return lst;
    }

    public static List getFileFromServerAsList(String url) {
        List list = new ArrayList();

        URL localUrl = null;

        try {
            localUrl = new URL(url);

            BufferedReader localReader = null;

            localReader = new BufferedReader(new InputStreamReader(localUrl.openStream()));

            String localInputLine = null;

            while ((localInputLine = localReader.readLine()) != null) {
                list.add(localInputLine);
            }

            localReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static StringBuffer getFileFromServerAsStringBuffer(String url) {
        StringBuffer sb = new StringBuffer();

        List lst = getFileFromServerAsList(url);

        int size = lst.size();
        for (int i = 0; i < size; i++) {
            sb.append(lst.get(i));
        }

        return sb;
    }

    //from ImageListTag -- needs to be moved to helper class so everything can use it
    //String directory = reqFile.substring(0, reqFile.lastIndexOf("\\")) + "\\";
    public static List getImageList(String pathName) {
        return getFileList(pathName, new String[]{"jpg", "JPG", "gif", "GIF"});
    }

    public static List getFileList(String pathName, String[] extensionList) {
        List lst = new ArrayList();

        File dir = new File(pathName);

        // It is also possible to filter the list of returned files.
        // This example does not return any files that start with `.'.
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return !name.startsWith(".");
            }
        };

        String[] children = dir.list(filter);

        for (int i = 0; i < children.length; i++) {
            String child = children[i];
            if (checkExt(extensionList, child)) {
                lst.add(child);
            }
        }

        return lst;
    }

    public static boolean checkExt(String[] extensionList, String strToCheck) {
        boolean returner = false;

        for (int i = 0; i < extensionList.length; i++) {
            String s = extensionList[i];
            if (strToCheck.indexOf(s) > -1) {
                returner = true;
                break;
            }
        }

        return returner;
    }

    public static void writeToFileAppend(String pathName, String fileName, String append) {
        List lst = FileIO.readFile(pathName, fileName, false);
        lst.add(append);
        FileIO.writeFile(pathName, fileName, lst);
    }
}