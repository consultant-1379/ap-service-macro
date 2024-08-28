/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.oss.services.ap.erbs.upgrade;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import com.ericsson.oss.itpf.sdk.upgrade.UpgradeEvent;
import com.ericsson.oss.itpf.sdk.upgrade.UpgradePhase;

@ApplicationScoped
public class UpgradeEventObserver {     

public void upgradeNotificationObserver(@Observes final UpgradeEvent event) {

        final UpgradePhase phase = event.getPhase();
                switch (phase) {
                        case SERVICE_INSTANCE_UPGRADE_PREPARE:
                                
                         // service performs some actions here 

                        	
                         // Now accept/reject the UpgradeEvent
                         event.accept("This service is happy to upgrade.");
                         break;
                        
                        // Service must accept all UpgradePhases that
                        // it will perform no specific action for.
                        case SERVICE_CLUSTER_UPGRADE_PREPARE:
                        case SERVICE_CLUSTER_UPGRADE_FAILED:
                        case SERVICE_CLUSTER_UPGRADE_FINISHED_SUCCESSFULLY:
                        case SERVICE_INSTANCE_UPGRADE_FAILED:
                        case SERVICE_INSTANCE_UPGRADE_FINISHED_SUCCESSFULLY:
                         event.accept("OK");
                         break;
                        
                        default:
                         event.reject("Unexpected UpgradePhase");
                         break;
                }
                
        }
}
