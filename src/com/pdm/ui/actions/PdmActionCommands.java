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

package com.pdm.ui.actions;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 25, 2017 9:47:01 PM
 */
public interface PdmActionCommands extends com.bc.appbase.ui.actions.ActionCommands {
    
    String ABOUT = About.class.getName();
    
    String DISPLAY_ADD_CURRENT_ENTITY_TYPE_UI = DisplayAddCurrentEntityTypeUI.class.getName();
    String DISPLAY_EDIT_SELECTED_ENTITIES_UIS = DisplayEditSelectedEntitiesUIs.class.getName();
    String DISPLAY_ADD_PERSONNELPOSTING_UI = DisplayAddPersonnelpostingUI.class.getName();
    String DISPLAY_ADD_COURSEATTENDED_UI = DisplayAddCourseattendedUI.class.getName();
    
    String ADD_OFFICERSDATA = AddOfficersdataFromUI.class.getName();
    String ADD_AIRMANSDATA = AddAirmansdataFromUI.class.getName();
    String ADD_PERSONNELPOSTING = AddPersonnelpostingFromUI.class.getName();
    String ADD_COURSEATTENDED = AddCourseattendedFromUI.class.getName();
    
    
    String SEARCH = Search.class.getName();
    String SEARCH_AND_DISPLAY_RESULTS = SearchAndDisplayResults.class.getName();
}
