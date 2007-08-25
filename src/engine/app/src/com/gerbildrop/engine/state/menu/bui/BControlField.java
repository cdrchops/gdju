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
package com.gerbildrop.engine.state.menu.bui;

import com.jme.input.controls.Binding;
import com.jme.input.controls.GameControl;
import com.jmex.bui.BContainer;
import com.jmex.bui.BTextField;
import com.jmex.bui.event.ComponentListener;
import com.jmex.bui.layout.GroupLayout;


public class BControlField extends BContainer {

	private GameControl control;
	private Binding binding;
    private BTextField text;

    public BControlField(GameControl control,
                         Binding binding,
                         String _text1) {
        super(GroupLayout.makeHStretch());
        this.control = control;
        setBinding(binding);

        setStyleClass("labeltext");
        text = new BTextField(_text1);
        add(text);
    }

    public void addListener(final ComponentListener listener) {
        super.addListener(listener);
        text.addListener(listener);
    }

	public Binding getBinding() {
		return binding;
	}

    public void setText(String _text) {
        text.setText(_text);
    }

    public void setBinding(Binding _binding) {
		binding = _binding;
	}

	public GameControl getControl() {
		return control;
	}

	protected void updateText() {
        text.setText(binding != null ? binding.getName() : null);
	}

}