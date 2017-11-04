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
import com.bc.appbase.ui.SearchResultsPanelMouseRightClickListener;
import com.pdm.ui.actions.PdmActionCommands;

/**
 * @author Chinomso Bassey Ikwuagwu on Sep 15, 2017 12:31:42 AM
 */
public class SearchResultsRightClickListener extends SearchResultsPanelMouseRightClickListener {

    public SearchResultsRightClickListener(App app, SearchResultsPanel searchResultsPanel) {
        super(app, searchResultsPanel);
        this.addMenuItem("Add Row Above", PdmActionCommands.DISPLAY_ADD_CURRENT_ENTITY_TYPE_UI);
        this.addMenuItem("Edit Row", PdmActionCommands.DISPLAY_EDIT_SELECTED_ENTITIES_UIS);
        this.addMenuItem("View", PdmActionCommands.DISPLAY_MULTIPLE_RECORDS);
        this.addMenuItem("Add Posting/Unit/Appointment", PdmActionCommands.DISPLAY_ADD_PERSONNELPOSTING_UI);
        this.addMenuItem("Add Course Attended", PdmActionCommands.DISPLAY_ADD_COURSEATTENDED_UI);
        this.addMenuItem("Delete", PdmActionCommands.DELETE_SELECTED_RECORDS);
    }
}
