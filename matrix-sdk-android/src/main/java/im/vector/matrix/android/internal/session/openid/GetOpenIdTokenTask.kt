/*
 * Copyright (c) 2020 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.matrix.android.internal.session.openid

import im.vector.matrix.android.internal.di.UserId
import im.vector.matrix.android.internal.network.executeRequest
import im.vector.matrix.android.internal.task.Task
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

internal interface GetOpenIdTokenTask : Task<Unit, RequestOpenIdTokenResponse>

internal class DefaultGetOpenIdTokenTask @Inject constructor(
        @UserId private val userId: String,
        private val openIdAPI: OpenIdAPI,
        private val eventBus: EventBus) : GetOpenIdTokenTask {

    override suspend fun execute(params: Unit): RequestOpenIdTokenResponse {
        return executeRequest(eventBus) {
            apiCall = openIdAPI.openIdToken(userId)
        }
    }
}
