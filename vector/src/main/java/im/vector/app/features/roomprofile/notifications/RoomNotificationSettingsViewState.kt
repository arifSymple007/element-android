/*
 * Copyright (c) 2021 New Vector Ltd
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

package im.vector.app.features.roomprofile.notifications

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import im.vector.app.features.roomprofile.RoomProfileArgs
import org.matrix.android.sdk.api.session.room.model.RoomSummary
import org.matrix.android.sdk.api.session.room.notification.RoomNotificationState

data class RoomNotificationSettingsViewState(
        val roomId: String,
        val roomSummary: Async<RoomSummary> = Uninitialized,
        val isLoading: Boolean = false,
        val roomEncrypted: Boolean = false,
        val notificationState: Async<RoomNotificationState> = Uninitialized,
)  : MvRxState {

    constructor(args: RoomProfileArgs) : this(roomId = args.roomId)
}

data class AvatarData (
        val displayName: String,
        val avatarUrl: String
)

val RoomNotificationSettingsViewState.notificationOptions: List<RoomNotificationState>
    get() {
        return if (roomEncrypted) {
            listOf(RoomNotificationState.ALL_MESSAGES_NOISY, RoomNotificationState.ALL_MESSAGES, RoomNotificationState.MUTE)
        } else
            RoomNotificationState.values().asList()
    }
