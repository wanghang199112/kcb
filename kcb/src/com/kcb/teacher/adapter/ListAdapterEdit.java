package com.kcb.teacher.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kcb.teacher.util.RadioButton;
import com.kcb.teacher.util.RadioButton.OnCheckedChangeListener;
import com.kcbTeam.R;

/**
 * 
 * @className: ListAdapterEdit
 * @description:
 * @author: ljx
 * @date: 2015年5月7日 下午8:19:23
 */
public class ListAdapterEdit extends BaseAdapter {

	private ArrayList<String> mList;
	private Context mContext;
	private static HashMap<Integer, Boolean> isSelected;
	private LayoutInflater inflater;

	public ListAdapterEdit(Context context, ArrayList<String> list) {
		mContext = context;
		mList = list;
		inflater = LayoutInflater.from(context);
		isSelected = new HashMap<Integer, Boolean>();

		initDate();
	}

	private void initDate() {

		for (int i = 0; i < mList.size(); i++) {
			getIsSelected().put(i, false);
		}
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	static class ViewHolder {
		TextView testname;
		// com.kcb.teacher.util.RadioButton testchosen;
		RadioButton testchosen;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.tch_listitem_dialog, null);
			holder.testname = (TextView) convertView
					.findViewById(R.id.textview_testname);
			holder.testchosen = (RadioButton) convertView
					.findViewById(R.id.checkBox_testchosen);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// holder.testname.setText(mList.get(position));
		/*
		 * holder.testchosen.setOncheckListener(new OnCheckListener() {
		 * 
		 * @Override public void onCheck(View view, boolean check) { // TODO
		 * Auto-generated method stub isSelected.put(position, check); if
		 * (((CheckBox) view).isCheck()) { for (int i = 0; i < mList.size();
		 * i++) { if (i != position) { isSelected.put(i, false); } } }
		 * ListAdapterEdit.this.notifyDataSetChanged();// update // checkbox
		 * 
		 * }
		 * 
		 * });
		 */
		holder.testchosen
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(boolean isChecked) {

						isSelected.put(position, isChecked);
						if (isChecked) {
							for (int i = 0; i < mList.size(); i++) {
								if (i != position) {
									isSelected.put(i, false);
								}
							}
						}
						ListAdapterEdit.this.notifyDataSetChanged();// update
						// checkbox
					}
				});
		holder.testname.setText(mList.get(position));
		holder.testchosen.setChecked(getIsSelected().get(position));
		return convertView;
	}

	public static HashMap<Integer, Boolean> getIsSelected() {
		return isSelected;
	}

	public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
		ListAdapterEdit.isSelected = isSelected;
	}

}
