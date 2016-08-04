package com.zuhra.sharemebackendv2.utils;

import java.util.ArrayList;

/**
 * Created by zuhra on 16.07.2016.
 */
public interface AsyncTaskListener {

    void setQRCodeList(ArrayList<String> list);
    void onFinish(boolean result);
}
