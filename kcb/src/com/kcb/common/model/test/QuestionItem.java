package com.kcb.common.model.test;

import java.io.File;
import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.kcb.common.util.BitmapUtil;
import com.kcb.common.util.FileUtil;

/**
 * 
 * @className: TextContent
 * @description:
 * @author: ZQJ
 * @date: 2015年5月15日 下午8:07:01
 */
public class QuestionItem implements Serializable {

    private static final long serialVersionUID = 4919254309171318451L;

    private int mId; // from client, useful when item is choice

    private boolean mIsText = true;
    private String mText = "";
    private Bitmap mBitmap;
    private String mBitmapPath;
    private String mBitmapString;

    private boolean mIsRight; // useful when item is choice
    private boolean mIsSelected; // used in stu module, useful when item is choice, true if stu
                                 // selected this choice

    public QuestionItem() {}

    public static void copy(QuestionItem oldItem, QuestionItem newItem) {
        newItem.mId = oldItem.mId;
        if (oldItem.mIsText) {
            newItem.setText(oldItem.mText);
        } else {
            newItem.setBitmap(oldItem.mBitmap);
        }
        newItem.mIsRight = oldItem.mIsRight;
        newItem.mIsSelected = oldItem.mIsSelected;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public boolean isText() {
        return mIsText;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mIsText = true;
        mText = text;
        mBitmap = null;
        if (!TextUtils.isEmpty(mBitmapPath)) {
            new File(mBitmapPath).delete();
        }
        mBitmapPath = "";
    }

    public void setBitmap(Bitmap bitmap) {
        mIsText = false;
        mText = "";
        mBitmap = bitmap;
        mBitmapPath = "";
    }

    public Bitmap getBitmap() {
        if (null != mBitmap) {
            return mBitmap;
        } else if (null != mBitmapPath) {
            mBitmap = BitmapFactory.decodeFile(mBitmapPath);
            return mBitmap;
        } else {
            return null;
        }
    }

    public void setBitmapPath(String bitmapPath) {
        mBitmapPath = bitmapPath;
    }

    public String getBitmapPath() {
        return mBitmapPath;
    }

    public String getBitmapString() {
        Bitmap bitmap = getBitmap();
        if (null != bitmap) {
            byte[] bytes = BitmapUtil.bitmapToByteArray(bitmap);
            if (null != bytes) {
                return new String(bytes);
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public void setIsRight(boolean isRight) {
        mIsRight = isRight;
    }

    public boolean isRight() {
        return mIsRight;
    }

    public void setIsSelected(boolean selected) {
        mIsSelected = selected;
    }

    public boolean isSelected() {
        return mIsSelected;
    }

    public boolean equals(QuestionItem item) {
        if (mIsRight == item.mIsRight) {
            if (mIsText == item.mIsText) {
                if (mIsText) {
                    return mText.equals(item.mText);
                } else {
                    if (null != item.getBitmap()) {
                        return getBitmap().equals(item.getBitmap());
                    }
                }
            }
        }
        return false;
    }

    public boolean isCompleted() {
        return !TextUtils.isEmpty(mText) || mBitmap != null;
    }

    public void release() {
        if (null != mBitmap) {
            mBitmap.recycle();
            mBitmap = null;
        }
    }

    /**
     * question item to json, json to question item
     */
    public static final String KEY_ID = "id";
    public static final String KEY_ISTEXT = "istext";
    public static final String KEY_TEXT = "text";
    public static final String KEY_BITMAPSTRING = "bitmapstring";
    public static final String KEY_BITMAPPATH = "bitmappath";
    public static final String KEY_ISRIGHT = "isright";
    public static final String KEY_ISSELECTED = "isselected";

    /**
     * 发送到服务器的JsonObject包括的是图片String，保存到数据库的JsonObject包括的是图片的路径。
     */
    public JSONObject toJsonObject(boolean toServer) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(KEY_ID, mId);
            jsonObject.put(KEY_ISTEXT, mIsText);
            jsonObject.put(KEY_TEXT, mText);
            if (toServer && !TextUtils.isEmpty(mBitmapPath)) {
                jsonObject.put(KEY_BITMAPSTRING, getBitmapString());
            }
            jsonObject.put(KEY_BITMAPPATH, mBitmapPath);
            jsonObject.put(KEY_ISRIGHT, mIsRight);
            jsonObject.put(KEY_ISSELECTED, mIsSelected);
        } catch (JSONException e) {}
        return jsonObject;
    }

    public static QuestionItem fromJsonObject(JSONObject jsonObject) {
        QuestionItem item = new QuestionItem();
        item.mId = jsonObject.optInt(KEY_ID);
        item.mIsText = jsonObject.optBoolean(KEY_ISTEXT);
        item.mText = jsonObject.optString(KEY_TEXT);
        item.mBitmapString = jsonObject.optString(KEY_BITMAPSTRING);
        item.mBitmapPath = jsonObject.optString(KEY_BITMAPPATH);
        item.mIsRight = jsonObject.optBoolean(KEY_ISRIGHT);
        item.mIsSelected = jsonObject.optBoolean(KEY_ISSELECTED);
        return item;
    }

    public void saveBitmap(String testName, int questionIndex, int itemIndex) {
        String path = FileUtil.getQuestionItemPath(testName, questionIndex + 1, itemIndex);
        Bitmap bitmap = BitmapUtil.stringToBitmap(mBitmapString);
        FileUtil.saveBitmap(path, bitmap);
    }

    public void deleteBitmap() {
        if (null != mBitmapPath) {
            new File(mBitmapPath).delete();
        }
    }

    public void renameBitmap(String testName, int questionIndex, int itemIndex) {
        if (!TextUtils.isEmpty(mBitmapPath)) {
            File file = new File(mBitmapPath);
            String newPath = FileUtil.getQuestionItemPath(testName, questionIndex + 1, itemIndex);
            file.renameTo(new File(newPath));
            mBitmapPath = newPath;
        }
    }
}
