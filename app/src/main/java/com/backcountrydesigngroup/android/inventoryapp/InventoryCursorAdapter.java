package com.backcountrydesigngroup.android.inventoryapp;

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

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.backcountrydesigngroup.android.inventoryapp.data.InventoryContract;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class InventoryCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link InventoryCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView name = (TextView) view.findViewById(R.id.product_name_text_view);
        TextView price = (TextView) view.findViewById(R.id.price_text_view);
        TextView quantity = (TextView) view.findViewById(R.id.quantity_text_view);
        Button saleButton = (Button) view.findViewById(R.id.sale_button);

        // Find the columns of inventory attributes that we're interested in
        final int IdColumnIndex = cursor.getInt(cursor.getColumnIndex(InventoryContract.InventoryDB._ID));
        int nameColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryDB.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryDB.COLUMN_PRICE_NAME);
        int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryDB.COLUMN_QUANTITY_NAME);

        // Read the pet attributes from the Cursor for the current product
        String productName = cursor.getString(nameColumnIndex);
        Double productPrice = cursor.getDouble(priceColumnIndex);
        final int productQuantity = cursor.getInt(quantityColumnIndex);

        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri quantityUri = ContentUris.withAppendedId(InventoryContract.InventoryDB.CONTENT_URI, IdColumnIndex);
                decrement(context, quantityUri, productQuantity);
            }
        });

        // Populate fields with extracted properties
        name.setText(productName);
        DecimalFormat d = new DecimalFormat("'$'0.00");
        String result = d.format(productPrice);
        price.setText(result);
        quantity.setText(String.valueOf(productQuantity));
    }

    private void decrement(Context context, Uri qtyURI, int qty) {
        Log.e("Quantity", "Quantity starts at: " + qty);
        if (qty == 0) {
            qty = 0;
        } else {
            qty = qty - 1;
        }
        Log.e("Quantity", "Quantity ends at: " + qty);
        ContentValues values = new ContentValues();
        values.put(InventoryContract.InventoryDB.COLUMN_QUANTITY_NAME, qty);
        context.getContentResolver().update(qtyURI, values, null, null);

    }
}
