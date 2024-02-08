package branch.datalayer

import android.content.Context
import android.content.SharedPreferences

class BranchSharedPreferences(context: Context) {

    val sharedPreferences: SharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    val isFirstTimeUser = sharedPreferences.getBoolean("isFirstTimeUser", true)

}