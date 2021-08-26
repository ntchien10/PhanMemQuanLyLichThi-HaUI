package com.example.quanlylichthi.ui.chonmonthi;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlylichthi.R;

import java.util.ArrayList;

public class ListViewLichThiAdapter extends BaseAdapter {

    ArrayList<LichThiModel> listLichThiModel;

    public ListViewLichThiAdapter(ArrayList<LichThiModel> listLichThiModel) {
        this.listLichThiModel = listLichThiModel;
    }

    @Override
    public int getCount() {
        return this.listLichThiModel.size();
    }

    @Override
    public Object getItem(int position) {
        return listLichThiModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        if (convertView == null) {
            view = View.inflate(parent.getContext(), R.layout.layout_lichthi_item, null);
        } else view = convertView;
//        view = View.inflate(parent.getContext(), R.layout.layout_lichthi_item, null);
        //Bind sữ liệu phần tử vào View
        LichThiModel lichthi =listLichThiModel.get(position);
        ((TextView) view.findViewById(R.id.tv_sapxep_item_BacDaoTao)).setText(String.format("Bậc đào tạo: %s", lichthi.getBacDaoTao()));
        ((TextView) view.findViewById(R.id.tv_sapxep_item_Khoa)).setText(String.format("Khóa: %s", lichthi.getKhoa()));
        ((TextView) view.findViewById(R.id.tv_sapxep_item_HocKy)).setText(String.format("Học kỳ: %s", lichthi.getHocKy()));
        ((TextView) view.findViewById(R.id.tv_sapxep_item_MaHP)).setText(String.format("Mã HP: %s", lichthi.getMaHP()));
        ((TextView) view.findViewById(R.id.tv_sapxep_item_TenHP)).setText(String.format("Tên HP: %s", lichthi.getTenHP()));
        ((TextView) view.findViewById(R.id.tv_sapxep_item_phongthi)).setText(String.format("Phòng Thi: %s", lichthi.getPhongThi()));
        ((TextView) view.findViewById(R.id.tv_sapxep_item_CaThi)).setText(String.format("Ca thi: %s", lichthi.getCaThi()));
        ((TextView) view.findViewById(R.id.tv_sapxep_item_NgayThi)).setText(String.format("Ngày thi: %s", lichthi.getNgayThi()));
        ((TextView) view.findViewById(R.id.tv_sapxep_item_cb1)).setText(String.format("Cán bộ 1: %s", lichthi.getCB1()));
        ((TextView) view.findViewById(R.id.tv_sapxep_item_cb2)).setText(String.format("Cán bộ 2: %s", lichthi.getCB2()));

        return view;
    }
}

