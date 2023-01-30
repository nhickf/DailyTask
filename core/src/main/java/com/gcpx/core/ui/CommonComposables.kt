package com.gcpx.core.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun TextIcon(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes trailingIcon: Int? = null,
    @DrawableRes leadingIcon: Int? = null
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        leadingIcon?.let {
            Icon(
                painter = painterResource(it),
                contentDescription = null,
                modifier = Modifier
            )
        }

        Text(
            textAlign = TextAlign.Center,
            text = text)

        trailingIcon?.let {
            Icon(
                painter = painterResource(it),
                contentDescription = null,
                modifier = Modifier
            )
        }

    }
}
