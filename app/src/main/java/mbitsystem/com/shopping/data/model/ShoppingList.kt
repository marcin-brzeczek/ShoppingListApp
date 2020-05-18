package mbitsystem.com.shopping.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import paperparcel.PaperParcel
import java.util.*

@PaperParcel
@Entity(tableName = "shoppingLists")
data class ShoppingList(
    @PrimaryKey (autoGenerate = true)
    val shoppingListId: Long = 0,
    val name: String,
    val date: Date,
    val archived:Boolean = false
) : Parcelable {

    override fun toString() = name

    override fun writeToParcel(dest: Parcel, flags: Int) =
        PaperParcelShoppingList.writeToParcel(this, dest, flags)

    override fun describeContents(): Int = 0

    companion object {
        @JvmField
        val CREATOR = PaperParcelShoppingList.CREATOR
    }
}