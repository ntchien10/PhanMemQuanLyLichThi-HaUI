package com.example.quanlylichthi.ui.phongthi;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlylichthi.R;

import java.util.ArrayList;

public class ListViewPhongThiAdapter extends BaseAdapter {

    ArrayList<PhongThiThiModel> listPhongThiModel;

    public ListViewPhongThiAdapter(ArrayList<PhongThiThiModel> listPhongThiModel) {
        this.listPhongThiModel = listPhongThiModel;
    }


    @Override
    public int getCount() {
        return this.listPhongThiModel.size();
    }

    @Override
    public Object getItem(int position) {
        return listPhongThiModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(parent.getContext(), R.layout.phongthi_item, null);
        } else view = convertView;

        //Bind sữ liệu phần tử vào View
        PhongThiThiModel phongthi = (PhongThiThiModel) getItem(position);
        ((TextView) view.findViewById(R.id.item_ngaybd)).setText(String.format("Từ: %s", phongthi.NgayBatDau));
        ((TextView) view.findViewById(R.id.item_ngaykt)).setText(String.format("Đến: %s", phongthi.NgayKetThuc));
        ((TextView) view.findViewById(R.id.item_tenphong)).setText(String.format("Tên phòng: %s", phongthi.TenPhong));
        ((TextView) view.findViewById(R.id.item_succhua)).setText(String.format("Sức chứa: %s", phongthi.SucChua));

        return view;
    }
}
