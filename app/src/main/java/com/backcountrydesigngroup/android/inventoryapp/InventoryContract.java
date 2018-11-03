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
        */

import android.provider.BaseColumns;

public class InventoryContract {

    // Empty constructor
    private InventoryContract() {
    }

    // Inner class - definitions for database setup
    public static final class InventoryDB implements BaseColumns {

        // Table name
        public final static String TABLE_NAME = "inventory";

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