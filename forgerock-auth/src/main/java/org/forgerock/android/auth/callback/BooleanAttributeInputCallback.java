/*
 * Copyright (c) 2019 ForgeRock. All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package org.forgerock.android.auth.callback;

import androidx.annotation.Keep;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Implements a Callback for collection of a single identity object attribute from a user.
 */
@NoArgsConstructor
@Getter
public class BooleanAttributeInputCallback extends AttributeInputCallback {

    /**
     * The attribute Value
     */
    private Boolean value;

    /**
     * Constructor for this Callback.
     */
    @Keep
    public BooleanAttributeInputCallback(JSONObject jsonObject, int index) throws JSONException {
        super(jsonObject, index);
    }

    @Override
    protected void setAttribute(String name, Object value) {
        super.setAttribute(name, value);
        if (VALUE.equals(name)) {
            this.value = (Boolean) value;
        }
    }

    public void setValue(Boolean value) {
        this.value = value;
        super.setValue(value);
    }

    @Override
    public String getType() {
        return "BooleanAttributeInputCallback";
    }
}