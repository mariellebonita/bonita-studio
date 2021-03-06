/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.contract;

import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * Contract property section.
 *
 * @author Romain Bioteau
 */
public class BotContractPropertySection extends BotBase {

    public BotContractPropertySection(final SWTGefBot bot) {
        super(bot);
    }

    public BotContractInputTab selectInputTab() {
        bot.cTabItem(Messages.inputTabLabel).activate();
        return new BotContractInputTab(bot);
    }

    public BotContractConstraintTab selectConstraintTab() {
        bot.cTabItem(Messages.constraintsTabLabel).activate();
        return new BotContractConstraintTab(bot);
    }

}
