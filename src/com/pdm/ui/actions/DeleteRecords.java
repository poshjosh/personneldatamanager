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

import com.bc.appbase.App;
import com.bc.appcore.actions.Action;
import com.bc.appcore.actions.TaskExecutionException;
import com.bc.appcore.parameter.ParameterException;
import com.bc.jpa.dao.Dao;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JOptionPane;

/**
 * @author Chinomso Bassey Ikwuagwu on Apr 1, 2017 3:37:57 PM
 */
public class DeleteRecords implements Action<App,Boolean> {

    @Override
    public Boolean execute(App app, Map<String, Object> params) 
            throws ParameterException, TaskExecutionException {
        
        final int selection = JOptionPane.showConfirmDialog(app.getUIContext().getMainFrame(), 
                "Are you sure you want to delete the selected records(s)?", "Confirm Delete", 
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
        if(selection == JOptionPane.YES_OPTION) {
            
            final SelectedIdsList selectedIdsList = new SelectedIdsList(params);
            final Iterator<Entry<Class, List>> iter = selectedIdsList.entrySet().iterator();
            final Entry<Class, List> entry = !iter.hasNext() ? null : iter.next();
            final Class entityType = entry.getKey();
            final List idsList = entry.getValue();
            
            for(Object id : idsList) {
                
                try(Dao dao = app.getDao()) {

                    final Object managedEntity = dao.find(entityType, id);
                    
                    dao.begin().remove(managedEntity).commit();
                    app.getSlaveUpdates().addRemove(managedEntity);
                }
            }
        }
        
        app.getAction(PdmActionCommands.REFRESH_RESULTS).execute(app, params);
        
        app.getUIContext().showSuccessMessage("Success");
        
        return Boolean.TRUE;
    }
}
