package mbitsystem.com.shopping.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import paperparcel.PaperParcel

@PaperParcel
@Entity(tableName = "shoppingItems")
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true)
    val shoppingItemId: Long = 0,
    val shoppingListId: Long,
    val name: String
) : Parcelable {

    override fun toString() = name

    override fun writeToParcel(dest: Parcel, flags: Int) =
        PaperParcelShoppingItem.writeToParcel(this, dest, flags)

    override fun describeContents(): Int = 0

    companion object {
        @JvmField
        val CREATOR = PaperParcelShoppingItem.CREATOR
    }
}
