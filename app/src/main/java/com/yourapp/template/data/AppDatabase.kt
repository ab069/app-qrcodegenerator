package com.yourapp.template.data

// Uncomment and configure when this app needs a local database.
//
// @Database(entities = [YourEntity::class], version = 1, exportSchema = false)
// abstract class AppDatabase : RoomDatabase() {
//     abstract fun yourDao(): YourDao
//
//     companion object {
//         @Volatile private var INSTANCE: AppDatabase? = null
//
//         fun getInstance(context: Context): AppDatabase =
//             INSTANCE ?: synchronized(this) {
//                 Room.databaseBuilder(context, AppDatabase::class.java, "app.db")
//                     .build()
//                     .also { INSTANCE = it }
//             }
//     }
// }
