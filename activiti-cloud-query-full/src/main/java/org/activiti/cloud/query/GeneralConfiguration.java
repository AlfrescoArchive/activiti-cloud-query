/*
 * Copyright 2018 Alfresco, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.cloud.query;

import org.activiti.api.process.model.payloads.GetProcessDefinitionsPayload;
import org.activiti.api.process.model.payloads.GetProcessInstancesPayload;
import org.activiti.api.runtime.shared.security.SecurityManager;
import org.activiti.cloud.common.swagger.DocketCustomizer;
import org.activiti.cloud.starter.audit.configuration.PayloadsDocketCustomizer;
import org.activiti.core.common.spring.security.policies.ProcessSecurityPoliciesManager;
import org.activiti.core.common.spring.security.policies.ProcessSecurityPoliciesManagerImpl;
import org.activiti.core.common.spring.security.policies.SecurityPoliciesRestrictionApplier;
import org.activiti.core.common.spring.security.policies.conf.SecurityPoliciesProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestHandlerSelectors;

import java.util.function.Predicate;

@Configuration
public class GeneralConfiguration {

    @Bean
    public String auditApiSelector() {
        return "fake";
    }

    @Bean
    public String queryRestApiSelector() {
        return "fake";
    }

    @Bean
    public Predicate<RequestHandler> queryFullRestApiSelector() {
        //TODO: return RequestHandlerSelectors.basePackage("org.activiti.cloud.services.audit")::apply;
        return RequestHandlerSelectors.basePackage("org.activiti.cloud.services.query.rest")::apply;
    }

    @Bean
    @Primary
    public ProcessSecurityPoliciesManager processSecurityPoliciesManager(SecurityManager securityManager, SecurityPoliciesProperties securityPoliciesProperties, SecurityPoliciesRestrictionApplier<GetProcessDefinitionsPayload> processDefinitionRestrictionApplier, SecurityPoliciesRestrictionApplier<GetProcessInstancesPayload> processInstanceRestrictionApplier) {
        //org.activiti.cloud.services.audit.jpa.security.config.AuditJPASecurityAutoConfiguration.securityPoliciesApplicationService
        //org.activiti.core.common.spring.security.policies.config.ActivitiSpringSecurityPoliciesAutoConfiguration.processSecurityPoliciesManager
        return new ProcessSecurityPoliciesManagerImpl(securityManager, securityPoliciesProperties, processDefinitionRestrictionApplier, processInstanceRestrictionApplier);
    }
    
}