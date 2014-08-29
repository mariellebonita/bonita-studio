/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general.general;

import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;

/**
 * General property section.
 * 
 * @author Joachim Segala
 */
public class BotGeneralPropertySection extends BotBase {

    public BotGeneralPropertySection(final SWTGefBot bot) {
        super(bot);
    }

    public void setName(final String pName) {
        bot.textWithLabel(Messages.name).setText(pName);
        //FIXME: replace with wait until
        bot.sleep(1000);
    }

    /**
     * TASK.
     */

    /**
     * Set the type of the task.
     * 
     * @param pType
     */
    public void setTaskType(final String pType) {
        bot.comboBoxWithLabel(Messages.activityType).setSelection(pType);
    }

    /**
     * GATEWAY.
     */

    /**
     * Set the type of the gateway.
     * 
     * @param pType
     */
    public void setGatewayType(final String pType) {
        bot.comboBoxWithLabel(Messages.gatewayType).setSelection(pType);
    }

    /**
     * SEQUENCE FLOW.
     */

    /**
     * Check/Uncheck "Default flow".
     * 
     * @param pIsDefaultFlow
     */
    public void setIsDefaultFlow(final boolean pIsDefaultFlow) {
        final SWTBotCheckBox cb = bot.checkBox(Messages.defaultFlowLabel);
        if (cb.isChecked() && !pIsDefaultFlow) {
            cb.deselect();
        } else if (!cb.isChecked() && pIsDefaultFlow) {
            cb.select();
        }
    }
}
