package com.gcpx.core.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
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
            text = text
        )

        trailingIcon?.let {
            Icon(
                painter = painterResource(it),
                contentDescription = null,
                modifier = Modifier
            )
        }

    }
}

@Composable
fun AppToolbar(
    toolbarTitle: String = "",
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    leadingIconOnClick : () -> Unit = {},
    trailingIconOnClick : () -> Unit = {},
) {

    TopAppBar(
        backgroundColor = Color.White,
        elevation = 0.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        leadingIcon?.let {
            Icon(
                imageVector = leadingIcon,
                contentDescription = "",
                modifier = Modifier.clickable {
                    leadingIconOnClick.invoke()
                }
            )
        }

        Text(
            modifier = Modifier.weight(1f),
            text = toolbarTitle
        )

        trailingIcon?.let {
            Icon(
                imageVector = trailingIcon,
                contentDescription = "",
                modifier = Modifier.clickable {
                    trailingIconOnClick.invoke()
                }
            )
        }
    }
}
