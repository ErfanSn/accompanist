/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.accompanist.insets.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A wrapper around [BottomNavigation] which supports the setting of [contentPadding] to add
 * internal padding. This is especially useful in conjunction with insets.
 *
 * For an edge-to-edge layout, typically you would use the
 * [com.google.accompanist.insets.WindowInsets.navigationBars] insets like so below:
 *
 * @sample com.google.accompanist.sample.insets.BottomNavigation_Insets
 */
@Composable
public fun BottomNavigation(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = BottomNavigationDefaults.Elevation,
    content: @Composable RowScope.() -> Unit,
) {
    BottomNavigationSurface(modifier, backgroundColor, contentColor, elevation) {
        BottomNavigationContent(Modifier.padding(contentPadding)) {
            content()
        }
    }
}

@Composable
public fun BottomNavigationSurface(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = BottomNavigationDefaults.Elevation,
    content: @Composable () -> Unit
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        modifier = modifier,
    ) {
        content()
    }
}

@Composable
public fun BottomNavigationContent(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(BottomNavigationHeight)
            .selectableGroup(),
        horizontalArrangement = Arrangement.SpaceBetween,
        content = content,
    )
}

/**
 * Copied from [androidx.compose.material.BottomNavigationHeight]
 * Height of a [BottomNavigation] component
 */
private val BottomNavigationHeight = 56.dp
