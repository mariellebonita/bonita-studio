/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.ui.validator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.jface.databinding.validator.UniqueValidatorFactory;
import org.bonitasoft.studio.la.handler.NewApplicationHandler;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.la.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.ui.validator.TypedValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class ApplicationDescriptorFileNameValidator extends TypedValidator<String, IStatus> {

    private static final String[] reservedChars = new String[] { "/", "\\", ":", "*", "?", "\"", "<", ">", ";", "|" };

    private final ApplicationRepositoryStore store;

    public ApplicationDescriptorFileNameValidator(ApplicationRepositoryStore store) {
        this.store = store;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.ui.validator.TypedValidator#doValidate(java.util.Optional)
     */
    @Override
    protected IStatus doValidate(Optional<String> value) {
        final String fileName = value.orElse("");
        if (fileName.isEmpty()) {
            return ValidationStatus.error(Messages.required);
        }
        if (Objects.equals(fileName, NewApplicationHandler.XML_EXTENSION)) {
            return ValidationStatus.error(String.format(Messages.invalidFileName, fileName));
        }

        final Optional<String> invalidChar = isValidFileName(fileName);
        if (invalidChar.isPresent()) {
            return ValidationStatus.error(String.format(Messages.invalidCharFileName, invalidChar.get()));
        }
        return UniqueValidatorFactory.uniqueValidator().in(getExistingFileNames()).create().validate(fileName);
    }

    protected Optional<String> isValidFileName(final String fileName) {
        for (final String reservedChar : reservedChars) {
            if (fileName.contains(reservedChar)) {
                return Optional.of(reservedChar);
            }
        }
        return Optional.empty();
    }

    private List<String> getExistingFileNames() {
        return store.getChildren().stream()
                .map(applicationDescriptor -> applicationDescriptor.getName().substring(0,
                        applicationDescriptor.getName().length() - NewApplicationHandler.XML_EXTENSION.length()))
                .collect(Collectors.toList());
    }

}