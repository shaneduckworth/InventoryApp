package com.backcountrydesigngroup.android.inventoryapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.backcountrydesigngroup.android.inventoryapp.InventoryContract.InventoryDB;

public class MainActivity extends AppCompatActivity {

    // private member database handler
    private InventoryDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up database, creates it if it does not exist
        mDbHelper = new InventoryDbHelper(this);
        insertPet();
        displayDatabaseInfo();
    }

    /**
     * This code is to satisfy Inventory App Stage 1 display of database information.  This will
     * be modified in Stage 2 to drop information into the right spots.
     */
    private void displayDatabaseInfo() {
        // Attach database to handler
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database will be handled
        String[] projection = {
                InventoryDB._ID,
                InventoryDB.COLUMN_PRODUCT_NAME,
                InventoryDB.COLUMN_PRICE_NAME,
                InventoryDB.COLUMN_QUANTITY_NAME,
                InventoryDB.COLUMN_SUPPLIER_NAME,
                InventoryDB.COLUMN_SUPPLIER_PHONE_NAME};

        // Perform a query on the inventory table, pull all the information
        Cursor cursor = db.query(
                InventoryDB.TABLE_NAME,        // The table to query
                projection,                    // The columns, defined above
                null,                 // The columns for the WHERE clause
                null,              // The values for the WHERE clause
                null,                  // Row grouping
                null,                   // Row grouping values
                null);                 // The sort order

        TextView dbView = (TextView) findViewById(R.id.db_display);

        try {
            dbView.setText("The " + InventoryDB.TABLE_NAME + " table contains " + cursor.getCount() + " products.\n\n");
            dbView.append(InventoryDB._ID + "\n" +
                    InventoryDB.COLUMN_PRODUCT_NAME + "\n" +
                    InventoryDB.COLUMN_PRICE_NAME + "\n" +
                    InventoryDB.COLUMN_QUANTITY_NAME + "\n" +
                    InventoryDB.COLUMN_SUPPLIER_NAME + "\n" +
                    InventoryDB.COLUMN_SUPPLIER_PHONE_NAME + "\n");

            // Obtain column indices from the cursor
            int idColumnIndex = cursor.getColumnIndex(InventoryDB._ID);
            int productColumnIndex = cursor.getColumnIndex(InventoryDB.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(InventoryDB.COLUMN_PRICE_NAME);
            int quantityColumnIndex = cursor.getColumnIndex(InventoryDB.COLUMN_QUANTITY_NAME);
            int supplierColumnIndex = cursor.getColumnIndex(InventoryDB.COLUMN_SUPPLIER_NAME);
            int supplierPhoneColumnIndex = cursor.getColumnIndex(InventoryDB.COLUMN_SUPPLIER_PHONE_NAME);

            // Iterate through the cursor - starts at -1, if working correctly
            while (cursor.moveToNext()) {
                // Get column values
                int currentID = cursor.getInt(idColumnIndex);
                String currentProduct = cursor.getString(productColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplier = cursor.getString(supplierColumnIndex);
                String currentSupplierPhone = cursor.getString(supplierPhoneColumnIndex);

                // Display the column values
                dbView.append(("\n" + currentID + ", " +
                        currentProduct + ", Rs" +
                        currentPrice + ", Qty: " +
                        currentQuantity + ", " +
                        currentSupplier + ", " +
                        currentSupplierPhone + "\n"));
            }
        } finally {
            cursor.close();
        }
    }

    /**
     * Helper method to insert hardcoded inventory data into the database, for the purposes of Inventory App Stage 1.
     * This will be revised for Stage 2 to take human-entered data.
     */
    private void insertPet() {
        // Attach database to handler
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Insert hard-coded values
        ContentValues values = new ContentValues();
        values.put(InventoryDB.COLUMN_PRODUCT_NAME, "Rock-On Cellular Headphones");
        values.put(InventoryDB.COLUMN_PRICE_NAME, 100);
        values.put(InventoryDB.COLUMN_QUANTITY_NAME, 200);
        values.put(InventoryDB.COLUMN_SUPPLIER_NAME, "Szenchen Quality Tech Products");
        values.put(InventoryDB.COLUMN_SUPPLIER_PHONE_NAME, "+91 8800305692");

        // Insert the values into the table.
        // The second argument allows "NULL" to be entered into a column if there are no entries.
        // Null for this argument skips the entry if there are no values.
        long newRowId = db.insert(InventoryDB.TABLE_NAME, null, values);

        values.put(InventoryDB.COLUMN_PRODUCT_NAME, "Lenovo X1 Thinkpad");
        values.put(InventoryDB.COLUMN_PRICE_NAME, 130000);
        values.put(InventoryDB.COLUMN_QUANTITY_NAME, 15);
        values.put(InventoryDB.COLUMN_SUPPLIER_NAME, "Szenchen Quality Tech Products");
        values.put(InventoryDB.COLUMN_SUPPLIER_PHONE_NAME, "+91 8800305692");
        newRowId = db.insert(InventoryDB.TABLE_NAME, null, values);

        values.put(InventoryDB.COLUMN_PRODUCT_NAME, "HP Bluetooth Mini Speaker 300");
        values.put(InventoryDB.COLUMN_PRICE_NAME, 4200);
        values.put(InventoryDB.COLUMN_QUANTITY_NAME, 35);
        values.put(InventoryDB.COLUMN_SUPPLIER_NAME, "Szenchen Quality Tech Products");
        values.put(InventoryDB.COLUMN_SUPPLIER_PHONE_NAME, "+91 8800305692");
        newRowId = db.insert(InventoryDB.TABLE_NAME,  null, values);
    }
}
