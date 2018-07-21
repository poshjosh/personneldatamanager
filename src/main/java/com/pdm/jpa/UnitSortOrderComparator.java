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

package com.pdm.jpa;

import com.pdm.pu.entities.Unit;
import java.util.Comparator;

/**
 * @author Chinomso Bassey Ikwuagwu on Jun 6, 2017 9:12:33 PM
 */
public class UnitSortOrderComparator implements Comparator<Unit> {

    @Override
    public int compare(Unit u1, Unit u2) {
        final int n1 = this.getSortOrder(u1, Integer.MAX_VALUE);
        final int n2 = this.getSortOrder(u2, Integer.MAX_VALUE);
        return Integer.compare(n1, n2);
    }
    
    public int getSortOrder(Unit unit, int outputIfNone) {
        final int sortOrder = unit.getUnitsortorder() == null ? outputIfNone : unit.getUnitsortorder();
        return sortOrder;
    }
}
