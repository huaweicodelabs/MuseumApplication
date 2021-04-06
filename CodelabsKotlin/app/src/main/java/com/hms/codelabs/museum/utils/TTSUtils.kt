/*
 * Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.
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
package com.hms.codelabs.museum.utils

import android.os.Bundle
import android.util.Pair
import com.hms.codelabs.museum.models.Constant
import com.huawei.hms.mlsdk.common.MLApplication
import com.huawei.hms.mlsdk.tts.*

class TTSUtils(api_key: String?) {
    // TODO: Machine learning Text To Speech engine and configs


    /**
     * Starts TTS reading
     *
     * @param text
     */
    fun startTTS(text: String) {
        //TODO : Start TTS reading. Split the context by sentences in order to overcome the character limit of the TTS.
        // Append all sentences one by one

    }

    /**
     * Stops TTS reading
     */
    fun stopTTS() {
        //TODO: Stop TTS function

    }

    /**
     * Shutdowns TTS
     */
    fun destroyTTS() {

    }

    /**
     * Text to speech service initialization
     */
    init {
        // TODO : Set Machine Learning TTS properties


        // TODO : Set a callback for TTS


    }
}