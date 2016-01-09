/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.contract.ui.wizard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.junit.Rule;
import org.junit.Test;

public class BusinessDataSelectedValidatorTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Test
    public void testValidate_OK() throws Exception {
        final WritableValue selectedDataObservable = new WritableValue();
        final BusinessObjectData businessObjectData = aBusinessData().withClassname("org.test.Employee").build();
        selectedDataObservable.setValue(businessObjectData);
        final List<Data> availableBusinessData = new ArrayList<Data>();
        availableBusinessData.add(ProcessFactory.eINSTANCE.createData());

        final BusinessObjectModelRepositoryStore businessObjectStore = mock(BusinessObjectModelRepositoryStore.class);
        when(businessObjectStore.getBusinessObjectByQualifiedName(businessObjectData.getClassName())).thenReturn(new BusinessObject());
        assertThat(new BusinessDataSelectedValidator(availableBusinessData, selectedDataObservable, businessObjectStore).validate().isOK()).isTrue();
    }

    @Test
    public void testValidate_KO_forEmptyBusinessData() throws Exception {
        final WritableValue selectedDataObservable = new WritableValue();
        final List<Data> availableBusinessData = new ArrayList<Data>();
        assertThat(
                new BusinessDataSelectedValidator(availableBusinessData, selectedDataObservable, mock(BusinessObjectModelRepositoryStore.class)).validate()
                        .isOK()).isFalse();
    }

    @Test
    public void testValidate_KO_forNoBusinessDataSelected() throws Exception {
        final WritableValue selectedDataObservable = new WritableValue();
        final BusinessObjectData businessObjectData = aBusinessData().withClassname("org.test.Employee").build();
        selectedDataObservable.setValue(businessObjectData);
        final List<Data> availableBusinessData = new ArrayList<Data>();
        availableBusinessData.add(ProcessFactory.eINSTANCE.createData());

        final BusinessObjectModelRepositoryStore businessObjectStore = mock(BusinessObjectModelRepositoryStore.class);
        when(businessObjectStore.getBusinessObjectByQualifiedName(businessObjectData.getClassName())).thenReturn(null);
        assertThat(new BusinessDataSelectedValidator(availableBusinessData, selectedDataObservable, businessObjectStore).validate().isOK()).isFalse();
    }

    @Test
    public void testValidate_KO_forInvalidBusinessDataSelected() throws Exception {
        final WritableValue selectedDataObservable = new WritableValue();
        final List<Data> availableBusinessData = new ArrayList<Data>();
        availableBusinessData.add(ProcessFactory.eINSTANCE.createData());
        assertThat(
                new BusinessDataSelectedValidator(availableBusinessData, selectedDataObservable, mock(BusinessObjectModelRepositoryStore.class)).validate()
                        .isOK()).isFalse();
    }

}