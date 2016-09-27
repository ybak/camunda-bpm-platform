/*
 * Copyright 2016 camunda services GmbH.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.engine.impl.bpmn.behavior;

import org.camunda.bpm.engine.impl.event.ConditionalVariableEventPayload;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.persistence.entity.EventSubscriptionEntity;

/**
 * Represents an interface for the condition event behaviors.
 * Makes it possible to leave the current activity if the condition of the
 * conditional event is satisfied.
 *
 * @author Christopher Zell <christopher.zell@camunda.com>
 */
public interface ConditionalEventBehavioral {

  /**
   * Checks the condition, on satisfaction the activity is leaved.
   *
   * @param eventSubscription the event subscription which contains all necessary informations
   * @param conditionalVariableEventPayload the payload to evaluate the condition
   * @param commandContext the current command context
   */
  void leaveOnSatisfiedCondition(final EventSubscriptionEntity eventSubscription,
          ConditionalVariableEventPayload conditionalVariableEventPayload, final CommandContext commandContext);
}