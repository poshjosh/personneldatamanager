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

import com.bc.appbase.ui.MainFrame;
import com.bc.appbase.ui.SearchResultsPanel;
import java.awt.Font;
import javax.swing.JPanel;
import com.bc.appbase.App;
import com.bc.appcore.jpa.SearchContext;
import com.bc.jpa.search.SearchResults;
import com.pdm.PdmApp;
import com.pdm.ui.actions.PdmActionCommands;

/**
 * The help menu though created and set-up, is not added by default. Use 
 * {@link #addHelpMenu()} to add the help menu after adding all other menus 
 * to the menu bar.
 * @author Chinomso Bassey Ikwuagwu on Mar 30, 2017 6:01:52 PM
 */
public class PdmMainFrame extends MainFrame {

    public PdmMainFrame() { 
        this(null);
    }

    public PdmMainFrame(App app) {
        this(app, new SearchPanel(), new java.awt.Font("Segoe UI", 0, 18), null);
    }

    public PdmMainFrame(App app, JPanel topPanel, Font menuFont, String aboutMenuItemActionCommand) {
        super(app, topPanel, menuFont, aboutMenuItemActionCommand);
    }

    @Override
    public void initComponents() {
        
        super.initComponents(); 
        
        this.addHelpMenu();
    }

    @Override
    public void init(App app) {
        
        super.init(app);
        
        final SearchResultsPanel resultsPanel = this.getSearchResultsPanel();
        resultsPanel.getAddButton().setActionCommand(PdmActionCommands.DISPLAY_ADD_OFFICERDATA_UI);
        app.getUIContext().addActionListeners(resultsPanel, resultsPanel.getAddButton());
    }

    @Override
    public void reset(App app) {
        
        super.reset(app);
        
        final SearchContext searchContext = ((PdmApp)app).getSearchContext(null);
        final SearchResults searchResults = searchContext.getSearchResults((Class)null);
        this.getSearchResultsPanel().reset(app, searchContext, searchResults);
    }
    
    @Override
    public void init(App app, JPanel topPanel) { 
        (((SearchPanel)topPanel)).init((PdmApp)app);
    }

    @Override
    public void reset(App app, JPanel topPanel) { 
        (((SearchPanel)topPanel)).reset((PdmApp)app);
    }
}
