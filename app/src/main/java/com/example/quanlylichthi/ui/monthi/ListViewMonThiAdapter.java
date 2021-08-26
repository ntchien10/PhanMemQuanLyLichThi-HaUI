package com.example.quanlylichthi.ui.monthi;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlylichthi.R;

import java.util.ArrayList;

public class ListViewMonThiAdapter extends BaseAdapter {

    ArrayList<MonThiModel> listMonThiModel;

    public ListViewMonThiAdapter(ArrayList<MonThiModel> listMonThiModel) {
        this.listMonThiModel = listMonThiModel;
    }

    @Override
    public int getCount() {
        return this.listMonThiModel.size();
    }

    @Override
    public Object getItem(int position) {
        return listMonThiModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
//        if (convertView == null) {
//            view = View.inflate(parent.getContext(), R.layout.monthi_view, null);
//        } else view = convertView;
        view = View.inflate(parent.getContext(), R.layout.monthi_view, null);
        //Bind sữ liệu phần tử vào View
        MonThiModel monthi =listMonThiModel.get(position);
        ((TextView) view.findViewById(R.id.tv_bacdt)).setText(String.format("Bậc đào tạo: %s", monthi.getBacDaoTao()));
        ((TextView) view.findViewById(R.id.tv_kihoc)).setText(String.format("Kì học: %s", monthi.getKiHoc()));
        ((TextView) view.findViewById(R.id.tv_khoa)).setText(String.format("Khóa: %s", monthi.getKhoa()));
        ((TextView) view.findViewById(R.id.tv_MaHP)).setText(String.format("Mã HP : %s", monthi.getMaHocPhan()));
        ((TextView) view.findViewById(R.id.tv_tenHP)).setText(String.format("Tên HP: %s", monthi.getTenHocPhan()));
        ((TextView) view.findViewById(R.id.tv_soTC)).setText(String.format("Số TC: %s", monthi.getSoTC()));
        ((TextView) view.findViewById(R.id.tv_thoiLuong)).setText(String.format("Thời Lượng: %s", monthi.getThoiLuong()));

        return view;
    }
}
