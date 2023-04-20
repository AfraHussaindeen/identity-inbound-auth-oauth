/*
 * Copyright (c) 2023, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.oauth.endpoint.factory;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.configuration.mgt.core.ConfigurationManager;
import org.wso2.carbon.idp.mgt.IdpManager;

/**
 * Factory Beans serves as a factory for creating other beans within the IOC container. This factory bean is used to
 * instantiate the IDP Management service type of object inside the container.
 */
public class ConfigurationManagerFactory extends AbstractFactoryBean<ConfigurationManager> {

    private ConfigurationManager configurationManager;

    @Override
    public Class<ConfigurationManager> getObjectType() {

        return ConfigurationManager.class;
    }

    @Override
    protected ConfigurationManager createInstance() throws Exception {

        if (this.configurationManager != null) {
            return configurationManager;
        } else {
            ConfigurationManager configurationManager = (ConfigurationManager) PrivilegedCarbonContext.
                    getThreadLocalCarbonContext().getOSGiService(IdpManager.class, null);
            if (configurationManager != null) {
                this.configurationManager = configurationManager;
            }
            return configurationManager;
        }
    }
}
