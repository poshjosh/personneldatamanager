/*
 * Copyright 2017 NUROX Ltd.
 *
 * Licensed under the NUROX Ltd Software License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.looseboxes.com/legal/licenses/software.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pdm.ui;

import com.bc.appbase.App;
import com.bc.appbase.ui.SearchResultsPanel;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 30, 2017 6:37:57 PM
 */
public class PdmUIContextImpl extends com.bc.appbase.ui.UIContextBase implements PdmUIContext {

    public PdmUIContextImpl(App app, ImageIcon imageIcon, JFrame mainFrame) {
        super(app, imageIcon, mainFrame);
    }

    @Override
    public PdmMainFrame getMainFrame() {
        return (PdmMainFrame)super.getMainFrame();
    }

    @Override
    public MouseListener getMouseListener(Container container) {
        
        if(container instanceof SearchResultsPanel) {
            
            final MouseListener listener = 
                    new SearchResultsRightClickListener(this.getApp(), (SearchResultsPanel)container);
            
            return listener;
            
        }else{
            return new MouseAdapter() {};
        }
    }
}
