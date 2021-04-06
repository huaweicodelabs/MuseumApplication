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
package com.hms.codelabs.museum.utils;

import android.os.Bundle;
import android.util.Pair;

import com.hms.codelabs.museum.models.Constant;
import com.huawei.hms.mlsdk.common.MLApplication;
import com.huawei.hms.mlsdk.tts.MLTtsAudioFragment;
import com.huawei.hms.mlsdk.tts.MLTtsCallback;
import com.huawei.hms.mlsdk.tts.MLTtsConfig;
import com.huawei.hms.mlsdk.tts.MLTtsConstants;
import com.huawei.hms.mlsdk.tts.MLTtsEngine;
import com.huawei.hms.mlsdk.tts.MLTtsError;
import com.huawei.hms.mlsdk.tts.MLTtsWarn;

public class TTSUtils {
    // TODO: Machine learning Text To Speech engine and configs


    /**
     * Text to speech service initialization
     */
    public TTSUtils(String api_key) {
        // TODO : Set Machine Learning TTS properties


        // TODO : Set a callback for TTS

    }

    /**
     * Starts TTS reading
     *
     * @param text
     */
    public void startTTS(String text) {
        //TODO : Start TTS reading. Split the context by sentences in order to overcome the character limit of the TTS.
        // Append all sentences one by one

    }

    /**
     * Stops TTS reading
     */
    public void stopTTS() {
        //TODO: Stop TTS function

    }

    /**
     * Shutdowns TTS
     */
    public void destroyTTS() {
        if (mlTtsEngine != null) {
            mlTtsEngine.shutdown();
        }
    }
}
