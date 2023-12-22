package ce.bhesab.dongchi.component

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat

val localeMap = mapOf("en" to "english", "fa" to "فارسی")

@Composable
fun LanguageDropdown(
    selectedLanguage: String = Locale.current.language, onLanguageSelected: (String) -> Unit = {selectedLanguage->
        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(selectedLanguage)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .wrapContentSize()
        .padding(10.dp)
        .clickable { expanded = true }) {
        Row {
            Icon(imageVector = Icons.Default.Language, contentDescription = "locale")
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = if (selectedLanguage == "fa") "فا" else selectedLanguage)
            Spacer(modifier = Modifier.width(8.dp))
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            localeMap.forEach { (code, lang) ->
                DropdownMenuItem(onClick = {
                    onLanguageSelected(code)
                    expanded = false
                }, text = { Text(lang) })
            }
        }
    }
}
