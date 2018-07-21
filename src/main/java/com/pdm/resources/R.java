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

package com.pdm.resources;

/**
 * @author Chinomso Bassey Ikwuagwu on Sep 17, 2017 6:05:00 PM
 */
public interface R {
    
}
/**
 * 
    public static final Path ROOT_PATH;
    
    public static final URL ROOT_URL;
    
    static{
        try{
            final URL url = R.class.getResource("naflogo.jpg");
            ROOT_PATH = Paths.get(url.toURI()).getParent();
            ROOT_URL = ROOT_PATH.toUri().toURL();
        }catch(URISyntaxException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
 * 
 */
