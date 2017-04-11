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
package com.pdm.util;

import com.pdm.ConfigNames;
import java.text.SimpleDateFormat;
import com.bc.appbase.App;

/**
 * @author Chinomso Bassey Ikwuagwu on Feb 11, 2017 12:17:37 AM
 */
public class DateTimeFormat extends SimpleDateFormat {

    private final App app;

    public DateTimeFormat(App app) {
        this.app = app;
        DateTimeFormat.this.setTimeZone(app.getTimeZone());
        DateTimeFormat.this.applyLocalizedPattern(
                app.getConfig().getString(ConfigNames.DATETIME_PATTERN, "dd-MMM-yy HH:mm"));
    }
}
