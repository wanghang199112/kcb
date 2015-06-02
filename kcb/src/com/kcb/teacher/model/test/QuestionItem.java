package com.kcb.teacher.model.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;

/**
 * 
 * @className: TextContent
 * @description:
 * @author: ZQJ
 * @date: 2015年5月15日 下午8:07:01
 */
public class QuestionItem implements Serializable {

    private static final long serialVersionUID = 4919254309171318451L;

    private String mId; // from client, only use if choice

    private boolean isText = true;
    private String mText = "";
    private Bitmap mBitmap;
    private byte[] mBytesOfBitmap;
    private boolean isRight = false; // only use if choice
    private double mRate; // if the item is a choice, mRate represent a choice rate;if the item is a
                          // question title, mRate represent the correct rate

    public QuestionItem() {}

    public QuestionItem(String text) {
        mText = text;
    }

    public static void copy(QuestionItem oldItem, QuestionItem newItem) {
        if (oldItem.isText) {
            newItem.setText(oldItem.getText());
        } else {
            newItem.setBitmap(oldItem.getBitmap());
        }
        newItem.setIsRight(oldItem.isRight);
    }

    public void setId(String id) {
        mId = id;
    }

    public String getId() {
        return mId;
    }

    public boolean isText() {
        return isText;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        isText = true;
        mText = text;
        mBitmap = null;
        mBytesOfBitmap = null;
    }

    public void setBitmap(Bitmap bitmap) {
        isText = false;
        mText = "";
        mBitmap = bitmap;
    }

    public Bitmap getBitmap() {
        if (null != mBytesOfBitmap) {
            return BitmapFactory.decodeByteArray(mBytesOfBitmap, 0, mBytesOfBitmap.length);
        }
        return mBitmap;
    }

    public byte[] getBytesOfBitmap() {
        return mBytesOfBitmap;
    }

    public void setIsRight(boolean _isRight) {
        isRight = _isRight;
    }

    public boolean isRight() {
        return isRight;
    }

    public double getRate() {
        return mRate;
    }

    public void setRate(double rate) {
        mRate = rate;
    }

    public boolean equals(QuestionItem item) {
        if (isRight == item.isRight) {
            if (isText == item.isText) {
                if (isText) {
                    return mText.equals(item.mText);
                } else {
                    if (null != item.getBitmap()) {
                        return getBitmap().equals(item.getBitmap()); // when use intent transport
                                                                     // data , mBitmap must set as
                                                                     // null,so use gtBitmap() is
                                                                     // safer. mBitmap maybe =null
                    }
                }
            }
        }
        return false;
    }

    public void changeBitmapToBytes() {
        if (null != mBitmap) {
            mBytesOfBitmap = getBytes(mBitmap);
            mBitmap = null;
        }
    }

    public boolean isCompleted() {
        return !TextUtils.isEmpty(mText) || mBitmap != null || mBytesOfBitmap != null;
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos);
        return baos.toByteArray();
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", mId);
            jsonObject.put("isText", isText);
            jsonObject.put("text", mText);
            jsonObject.put("bitmap", mBytesOfBitmap);
            jsonObject.put("isRight", isRight);
        } catch (JSONException e) {}
        return jsonObject;
    }



    public static String encodeBitmapToString(Bitmap bitmap) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(Base64.encode(out.toByteArray(), Base64.DEFAULT));
    }

    public static Bitmap decodeStringToBitmap(String string) throws IOException {
        byte[] bytes = Base64.decode(string, Base64.DEFAULT);
        if (bytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            return bitmap;
        } else {
            return null;
        }
    }
}
