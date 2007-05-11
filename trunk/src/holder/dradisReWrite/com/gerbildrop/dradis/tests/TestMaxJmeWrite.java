/*
 * Copyright (c) 2004-2006 GerbilDrop Software
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'GerbilDrop Software' nor 'XBG' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
* Copyright (c) 2003-2006 jMonkeyEngine
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are
* met:
*
* * Redistributions of source code must retain the above copyright
*   notice, this list of conditions and the following disclaimer.
*
* * Redistributions in binary form must reproduce the above copyright
*   notice, this list of conditions and the following disclaimer in the
*   documentation and/or other materials provided with the distribution.
*
* * Neither the name of 'jMonkeyEngine' nor the names of its contributors
*   may be used to endorse or promote products derived from this software
*   without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
* "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
* TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
* PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
* CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
* EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
* PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
* PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
* LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
* NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package com.gerbildrop.dradis.tests;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.jme.app.SimpleGame;
import com.jme.scene.Node;
import com.jme.util.export.binary.BinaryExporter;
import com.jme.util.export.binary.BinaryImporter;
import com.jmex.model.XMLparser.Converters.MaxToJme;

public class TestMaxJmeWrite extends SimpleGame {
    public static void main(String[] args) {

        TestMaxJmeWrite app = new TestMaxJmeWrite();

        if (args.length > 0) {
            app.setModelToLoad(args[0]);
        }

        app.setDialogBehaviour(SimpleGame.FIRSTRUN_OR_NOCONFIGFILE_SHOW_PROPS_DIALOG);
        app.start();
    }

    private URL modelToLoad = null;
    private URL textureForModel = null;

    private void setModelToLoad(String string) {
        try {
            modelToLoad = (new File(string)).toURL();
        } catch (MalformedURLException e) {
            //No Op
        }
    }

    String[] models = new String[]{"colbstr1cox.3DS",
                                   "colbstr2cox.3DS",
                                   "colcels1cox.3DS",
                                   "colclm1cox.3DS",
                                   "colcol1cox.3DS",
                                   "coldefn1cox.3DS",
                                   "colflat1cox.3DS",
                                   "colgem1cox.3DS",
                                   "colgem2cox.3DS",
                                   "colline1cox.3DS",
                                   "colmftr1cox.3DS",
                                   "colmftr2cox.3DS",
                                   "colminr1cox.3DS",
                                   "colmvr1cox.3DS",
                                   "colpyln1cox.3DS",
                                   "colRailGunLowcox.3DS",
                                   "colrap1cox.3DS",
                                   "colrefn1cox.3DS",
                                   "colrstr1cox.3DS",
                                   "colshtl1cox.3DS",
                                   "coltube1cox.3DS",
                                   "cylbstr1cox.3DS",
                                   "cylftrh1cox.3DS",
                                   "cylmftr1cox.3DS"};

    protected void simpleInitGame() {
        if (modelToLoad == null) {
            modelToLoad = ClassLoader.getSystemResource("dradis/models/Corvette_Sting_Ray.3ds");
        }

        if (textureForModel == null) {
            textureForModel = ClassLoader.getSystemResource("dradis/models/");
        }

        try {
            MaxToJme maxConverter = new MaxToJme();
            ByteArrayOutputStream BO = new ByteArrayOutputStream();

            maxConverter.setProperty("texurl", textureForModel);

            maxConverter.convert(new BufferedInputStream(modelToLoad.openStream()), BO);

            Node r1 = (Node) BinaryImporter.getInstance().load(new ByteArrayInputStream(BO.toByteArray()));
            BinaryExporter.getInstance().save(r1, new File("stingray.jme"));
        } catch (IOException e) {
            System.out.println("Damn exceptions:" + e);
            e.printStackTrace();
        }
    }
}