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
import com.bc.appbase.ui.ComboBoxItemListener;
import com.bc.appbase.ui.MainFrame;
import com.bc.appbase.ui.SearchResultsPanel;
import com.bc.appbase.ui.actions.ParamNames;
import com.bc.appcore.jpa.SearchContext;
import com.bc.appcore.util.Selection;
import com.bc.jpa.search.SearchResults;
import com.pdm.PdmApp;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import com.pdm.ui.actions.PdmActionCommands;

/**
 *
 * @author Josh
 */
public class SearchPanel extends javax.swing.JPanel {

    public SearchPanel() {
        this(null);
    }
    
    public SearchPanel(PdmApp app) {
        initComponents();
        if(app != null) {
            this.init(app);
        }
    }
    
    public void init(PdmApp app) { 
        this.getSearchButton().setActionCommand(PdmActionCommands.SEARCH_AND_DISPLAY_RESULTS);
        this.getSearchTextField().setActionCommand(PdmActionCommands.SEARCH_AND_DISPLAY_RESULTS);
        final ActionListener listener = app.getUIContext().getActionListener(this, PdmActionCommands.SEARCH_AND_DISPLAY_RESULTS);
        this.getSearchButton().addActionListener(listener);
        this.getSearchTextField().addActionListener(listener);
        
        final Set<Class> entityTypes = app.getSupportedEntityTypes();
        final Set<Selection<Class>> selections = new LinkedHashSet<>(entityTypes.size());
        entityTypes.stream().forEach((cls) -> selections.add(Selection.from(cls.getSimpleName(), cls)));
        
        final JComboBox combo = this.getEntityTypeComboBox();
        combo.setModel(new DefaultComboBoxModel(selections.toArray(new Selection[0])));
        
        combo.setSelectedItem((Class)app.getAttributes().get(ParamNames.ENTITY_TYPE));
        
        combo.addItemListener(new ComboBoxItemListener(){
            @Override
            public void itemSelected(Object selection) {
                
                final Class currentEntityType = (Class)app.getAttributes().get(ParamNames.ENTITY_TYPE);
                
                final Class selectedEntityType = (Class)selection;
                
                if(!selectedEntityType.equals(currentEntityType)) {
                    
                    final PdmUIContext uiContext = app.getUIContext();
                    final MainFrame mainFrame = uiContext.getMainFrame();
                    final SearchResultsPanel resultsPanel = mainFrame.getSearchResultsPanel();
                    final SearchContext searchContext = app.getSearchContext(selectedEntityType);
                    final SearchResults searchResults = searchContext.searchAll();
                    
                    resultsPanel.load(searchContext, searchResults, mainFrame.getName(), 0, 1, true);
                }
                
                app.getAttributes().put(ParamNames.ENTITY_TYPE, selectedEntityType);
            }
            @Override
            public void itemDeselected() {
                app.getAttributes().put(ParamNames.ENTITY_TYPE, null);
            }
        });
    }
    
    public void reset(App app) {
        this.getSearchTextField().setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        entityTypeComboBox = new javax.swing.JComboBox<>();

        searchTextField.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        searchButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        searchButton.setText("Search");

        entityTypeComboBox.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(entityTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(searchButton)
                .addComponent(entityTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> entityTypeComboBox;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    // End of variables declaration//GEN-END:variables

    public JComboBox<String> getEntityTypeComboBox() {
        return entityTypeComboBox;
    }
    
    public JButton getSearchButton() {
        return searchButton;
    }

    public JTextField getSearchTextField() {
        return searchTextField;
    }
}
