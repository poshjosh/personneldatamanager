package com.pdm;

import com.bc.appbase.FilenamesDevMode;
import java.nio.file.Paths;
import com.bc.appcore.Filenames;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 13, 2017 9:52:58 PM
 */
public class Personneldatamanager {

    public static final Boolean PRODUCTION_MODE = Boolean.FALSE;
    
    public static void main(String [] args) {
        
        final String workingDir = Paths.get(System.getProperty("user.home"), FileNames.ROOT).toString();
        
        final Filenames filenames = PRODUCTION_MODE ? 
                new FilenamesProductionMode(workingDir) :  new FilenamesDevMode(workingDir);
        
        final PdmAppLauncher launcher = new PdmAppLauncher(false, filenames);
        
        try{
            
            launcher.launch(args);  
            
        }catch(Throwable t) {
            
            launcher.showErrorMessageAndExit(t);
        }
    }    
}
