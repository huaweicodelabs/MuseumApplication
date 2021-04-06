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
package com.hms.codelabs.museum.models

import android.Manifest
import com.hms.codelabs.museum.R

object Constant {
    /**
     * TTS SETTINGS
     */
    const val TTS_VOLUME = 1.0f
    const val TTS_SPEED = 1.0f

    /**
     * Awareness kit configuration
     */
    const val AWARENESS_BARRIER_RADIUS = 5000.0
    const val AWARENESS_BARRIER_DURATION = 1000L

    /**
     * Permission Strings
     */
    @JvmField
    val GUIDE_PERMISSIONS = arrayOf(
            Manifest.permission.BLUETOOTH,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    )
    @JvmField
    val SEARCH_PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            "android.permission.ACCESS_BACKGROUND_LOCATION",
            "com.huawei.hms.permission.ACTIVITY_RECOGNITION"
    )

    /**
     * Default search range for Site Kit Nearby Search
     */
    const val DEFAULT_SEARCH_RANGE = 50

    /**
     * Predefined Exhibit Info
     */
    val exhibitInfo: MutableList<Exhibit> = arrayListOf(
            Exhibit(0, "Hercules Head", "Roman sculpture head of Hercules, mid 2nd cent AD excavated from the Vale Giardino, Nemi. The head was probably made separately for the insertion onto a statue, probably depicting the gold seated. The work is a copy of a Greek original of the late Hellenistic period, inspired by a statue by the Greek sculptor Lysippos of Sicyon known as the ‘Herakles Epitapezios’ sculpted around 300 BC. The National Roman Museum, Rome, Italy", R.drawable.hercules, "Galleria Borghese", "statue"),
            Exhibit(1, "Sleeping Hermaphrodite", "Sleeping Hermaphroditus, The Borghese Hermaphrodite. A Life size ancient 2nd century AD Roman statue sculpted in Greek Marble and found in the grounds of Santa Maria della Vittoria, near the Baths of Diocletian, Rome. It was added to the Borghese Collection by Cardinal Scipione Borghese, in the 17th century and was named the \"Borghese Hermaphroditus”. It was later sold to the occupying French and was removed it to The Louvre. Hermaphrodite, son of Hermes and Aphrodite had repels the advances of the nymph Salmacis. However, she got Zeus as their two bodies are united in a bisexual being. The Sleeping Hermaphroditus has been described as a good early Imperial Roman copy of a bronze original by the later of the two Hellenistic sculptors named Polycles (150 BC) the original bronze was mentioned in Pliny's Natural History. In 1619 Bernini sculpted the mattress on which the ancient marble of Hermaphrodite lies. Louvre Museum, Paris", R.drawable.hermaphrodites, "Galleria Borghese", "statue")
    )

    /**
     * Exhibit detect range for virtual guide
     */
    const val EXHIBIT_DETECT_RANGE = 1.5f
}