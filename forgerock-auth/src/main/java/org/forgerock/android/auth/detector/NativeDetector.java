/*
 * Copyright (c) 2019 ForgeRock. All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package org.forgerock.android.auth.detector;

import android.content.Context;

/**
 * Check su command natively using NDK
 */
public class NativeDetector extends FileDetector {

    private static boolean libraryLoaded = false;

    static {
        try {
            System.loadLibrary("tool-file");
            libraryLoaded = true;
        } catch (UnsatisfiedLinkError e) {
            //ignore
        }
    }

    public native int exists(Object[] pathArray);

    public double isRooted(Context context) {

        if (!libraryLoaded) {
            return 0;
        }

        String[] paths = new String[PATHS.length * getFilenames().length];
        for (int i = 0; i < paths.length; i++) {
            for (String filename: getFilenames()) {
                paths[i] = PATHS[i] + filename;
            }
        }

        try {
            if (exists(paths) > 0) {
                return 1.0;
            }
        } catch (UnsatisfiedLinkError e) {
            return 0;
        }
        return 0;
    }

    @Override
    protected String[] getFilenames() {
        return new String[]{"su"};
    }

}
