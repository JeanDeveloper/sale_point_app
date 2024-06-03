package com.jchunga.salesapp.presentation.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.jchunga.salesapp.ui.theme.Grey
import com.jchunga.salesapp.ui.theme.focusedTextFieldText
import com.jchunga.salesapp.ui.theme.indicationColorTextField
import com.jchunga.salesapp.ui.theme.textColor
import com.jchunga.salesapp.ui.theme.textFieldContainer
import com.jchunga.salesapp.ui.theme.unfocusedTextFieldText

@Composable
fun CustomTextField(
    modifier: Modifier,
    label: String,
    trailingIcon: @Composable() (() -> Unit)? = null,
    textState: String,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit
) {

    val uiColor = if (isSystemInDarkTheme()) Color.White else Grey

    OutlinedTextField(
        modifier = modifier,
        value = textState,
        onValueChange = { onValueChange(it) },
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = uiColor
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.focusedTextFieldText,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.indicationColorTextField,
            disabledIndicatorColor = MaterialTheme.colorScheme.indicationColorTextField,
            focusedPlaceholderColor = MaterialTheme.colorScheme.focusedTextFieldText,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.unfocusedTextFieldText,
            unfocusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
            focusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
            focusedTextColor = MaterialTheme.colorScheme.textColor,
            unfocusedTextColor = MaterialTheme.colorScheme.textColor,
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        trailingIcon = trailingIcon,

        )

}