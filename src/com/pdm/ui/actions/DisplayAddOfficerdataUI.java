/*
 * Copyright 2017 NUROX Ltd.
 *
 * Licensed under the NUROX Ltd Software License (the "License");
 * you may not use this file except in compliance source the License.
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

package com.pdm.ui.actions;

import com.bc.appbase.ui.builder.UIBuilder;
import java.awt.Font;
import java.util.Map;
import java.util.logging.Logger;
import com.bc.appbase.App;
import com.bc.appbase.ui.SimpleFrame;
import com.bc.appcore.actions.Action;
import com.bc.appcore.util.Expirable;
import com.pdm.PdmApp;
import com.pdm.util.OfficersdataMap;
import java.awt.Container;
import java.util.concurrent.TimeUnit;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 25, 2017 9:45:59 PM
 */
public class DisplayAddOfficerdataUI implements Action<App, Container> {

    private static final Logger logger = Logger.getLogger(DisplayAddOfficerdataUI.class.getName());
    
    @Override
    public Container execute(App app, Map<String, Object> params) {
        
        final Container ui = this.buildUI(app, params);
        
        this.display(app, ui);
        
        return ui;
    }
    
    public Container buildUI(App app, Map<String, Object> params) {
        
        final Map officersdataMap = new OfficersdataMap((PdmApp)app);
        
        final UIBuilder<Map, Container> mapUIBuilder = app.get(UIBuilder.class);
        
        final Container ui = mapUIBuilder
                .source(officersdataMap)
                .build();
        
        app.addExpirable(ui, Expirable.from(officersdataMap, 10, TimeUnit.MINUTES));
        
        return ui;
    }

    public void display(App app, Container ui) {
        
        final SimpleFrame frame = new SimpleFrame(
                "Add Officers Data", app, ui, new Font(Font.MONOSPACED, Font.PLAIN, 18),
                " Add ", PdmActionCommands.ADD_OFFICERSDATA
        );
//        app.getUIContext().positionHalfScreenLeft(frame);
        
        frame.pack();
        frame.setVisible(true);
    }
}
