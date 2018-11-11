package com.backcountrydesigngroup.android.inventoryapp.data;

/** Copyright (C) 2016 The Android Open Source Project

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.

        Code originates from https://github.com/udacity/ud845-Pets
        */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.backcountrydesigngroup.android.inventoryapp.data.InventoryContract.InventoryDB;

// Class initiates, updates, and versions the SQLite DB
public class InventoryDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = InventoryDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "inventory.db";
    private static final int DATABASE_VERSION = 1;

    /**
     * Instantiate the DB, passing
     * @param context
     */
    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create the DB schema
    @Override
    public void onCreate(SQLiteDatabase db) {
        // References the definitions in InventoryContract
        String SQL_CREATE_INVENTORY_TABLE =  "CREATE TABLE " + InventoryDB.TABLE_NAME + " ("
                + InventoryDB._ID + InventoryDB._ID_TYPE +", "
                + InventoryDB.COLUMN_PRODUCT_NAME + InventoryDB.COLUMN_PRODUCT_TYPE + ", "
                + InventoryDB.COLUMN_PRICE_NAME + InventoryDB.COLUMN_PRICE_TYPE + ", "
                + InventoryDB.COLUMN_QUANTITY_NAME + InventoryDB.COLUMN_QUANTITY_TYPE + ", "
                + InventoryDB.COLUMN_SUPPLIER_NAME + InventoryDB.COLUMN_SUPPLIER_TYPE + ", "
                + InventoryDB.COLUMN_SUPPLIER_PHONE_NAME + InventoryDB.COLUMN_SUPPLIER_PHONE_TYPE + ");";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_INVENTORY_TABLE);
    }

    // Use this when versioning the DB
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}