package mbitsystem.com.shopping.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.stringFormat() = SimpleDateFormat("dd/MM/yyy", Locale.getDefault()).format(this)