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

package com.pdm;

import com.bc.appcore.AppLauncherCore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 29, 2017 10:21:47 PM
 */
public class TestApp {

    static final boolean PRODUCTION_MODE = false;
    
    public static final PdmApp getInstance(boolean waitTillCompletion) {
        
        final AppLauncherCore<PdmApp> launcher = new AppLauncherImpl(PRODUCTION_MODE);
        
        final PdmApp app = launcher.launch(new String[0]);

        if(waitTillCompletion) {
            try{
                launcher.waitTillCompletion();
            }catch(InterruptedException e) {
                Logger.getLogger(TestApp.class.getName()).log(Level.WARNING, 
                        "Interrupted while waiting for " + launcher.getClass().getSimpleName() + "#launch(String[]) to complete", e);
            }
        }

        return app;
    }
}

