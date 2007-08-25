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

import java.util.HashMap;

import com.jme.input.controls.Binding;
import com.jme.input.controls.GameControl;
import com.jme.input.controls.GameControlManager;
import com.jmex.bui.BContainer;
import com.jmex.bui.BLabel;
import com.jmex.bui.event.MouseEvent;
import com.jmex.bui.event.MouseListener;
import com.jmex.bui.layout.GroupLayout;

public class GameControlEditor extends BContainer implements MouseListener {
	public static int MOUSE_THRESHOLD = 5;
	public static float JOYSTICK_THRESHOLD = 0.2f;

	private GameControlManager manager;
	private int bindings;
	protected HashMap<GameControl, BControlField[]> controls;
	private BControlFieldListener listener;

	public GameControlEditor(GameControlManager _manager, int _bindings) {
        super(GroupLayout.makeVStretch());

        this.manager = _manager;
		this.bindings = _bindings;
		controls = new HashMap<GameControl, BControlField[]>();
        init();
	}

	public void init() {
		listener = new BControlFieldListener(this);
        setLayoutManager(GroupLayout.makeVStretch());

        for (String name : manager.getControlNames()) {
			GameControl control = manager.getControl(name);
            BContainer bc = new BContainer();
            bc.setLayoutManager(GroupLayout.makeHStretch());
            // Add the label
			BLabel label = new BLabel(name + ":");

			bc.add(label);

			// Add bindings
			BControlField[] fields = new BControlField[bindings];
			for (int i = 0; i < fields.length; i++) {
				Binding binding = null;
				if (control.getBindings().size() > i) {
					binding = control.getBindings().get(i);
				}

                String txt1 = binding != null ? binding.getName() : "";

                fields[i] = new BControlField(control, binding, txt1);
				fields[i].addListener(this);
                fields[i].updateText();

				bc.add(fields[i]);
			}

            add(bc);
			controls.put(control, fields);
		}
	}

    public void mouseClicked(MouseEvent evt) {
		if ((evt.getButton() == MouseEvent.BUTTON1)
            && (evt.getSource() instanceof BControlField)) {
            BControlField field = (BControlField)evt.getSource();
            listener.prompt(field);
		}
	}

	public void mouseEntered(MouseEvent evt) {
	}

	public void mouseExited(MouseEvent evt) {
	}

	public void mousePressed(MouseEvent evt) {
	}

	public void mouseReleased(MouseEvent evt) {
	}

	public void apply() {
		for (GameControl control : controls.keySet()) {
			control.clearBindings();	// Remove previous bindings
			for (BControlField field : controls.get(control)) {
//                if (field != null) {
                    if (field.getBinding() != null) {
                        // Set new binding back if not null
                        control.addBinding(field.getBinding());
                    }
//                }
            }
		}
	}

	public void reset() {
		for (GameControl control : controls.keySet()) {
			BControlField[] fields = controls.get(control);
			for (int i = 0; i < fields.length; i++) {
				if (control.getBindings().size() > i) {
					fields[i].setBinding(control.getBindings().get(i));
                    fields[i].setText(control.getBindings().get(i).getName());
                } else {
                    if (fields[i] != null) {
                        fields[i].setBinding(null);
                    }
                }
			}
		}
	}

	public void clear() {
		for (GameControl control : controls.keySet()) {
			BControlField[] fields = controls.get(control);
            for (BControlField field : fields) {
                field.setBinding(null);
            }
        }
	}


}