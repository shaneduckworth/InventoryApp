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

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class InventoryContract {

    // Empty constructor
    private InventoryContract() {
    }

    public static final String CONTENT_AUTHORITY = "com.backcountrydesigngroup.android.inventoryapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_INVENTORY = "inventory";

    // Inner class - definitions for database setup
    public static final class InventoryDB implements BaseColumns {

        // URI
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INVENTORY);

        // Table name
        public final static String TABLE_NAME = "inventory";

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of inventory items.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single inventory item.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;

        // Unique ID
        public final static String _ID = BaseColumns._ID;
        public final static String _ID_TYPE = " INTEGER PRIMARY KEY AUTOINCREMENT";

        // Product Name
        public final static String COLUMN_PRODUCT_NAME = "name";
        public final static String COLUMN_PRODUCT_TYPE = " TEXT NOT NULL";

        // Price
        public final static String COLUMN_PRICE_NAME = "price";
        public final static String COLUMN_PRICE_TYPE = " INTEGER NOT NULL DEFAULT 1";

        // Quantity
        public final static String COLUMN_QUANTITY_NAME = "quantity";
        public final static String COLUMN_QUANTITY_TYPE = " INTEGER NOT NULL";

        // Supplier Name
        public final static String COLUMN_SUPPLIER_NAME = "supplierName";
        public final static String COLUMN_SUPPLIER_TYPE = " TEXT";

        // Supplier Phone Number
        public final static String COLUMN_SUPPLIER_PHONE_NAME = "supplierPhone";
        public final static String COLUMN_SUPPLIER_PHONE_TYPE = " TEXT";

    }
}