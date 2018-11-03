package com.backcountrydesigngroup.android.inventoryapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.backcountrydesigngroup.android.inventoryapp.InventoryContract.InventoryDB;

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