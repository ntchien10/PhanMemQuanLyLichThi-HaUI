package com.example.quanlylichthi.ui.canbo;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlylichthi.R;

import java.util.ArrayList;

public class ListViewCanBoAdapter extends BaseAdapter {

    ArrayList<CanBoModel> listCanBo;

    public ListViewCanBoAdapter(ArrayList<CanBoModel> listCanBo) {
        this.listCanBo = listCanBo;
    }

    @Override
    public int getCount() {
        return this.listCanBo.size();
    }

    @Override
    public Object getItem(int position) {
        return listCanBo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        if (convertView == null) {
            view = View.inflate(parent.getContext(), R.layout.giaovien_view, null);
        } else view = convertView;

        //Bind sữ liệu phần tử vào View
        CanBoModel cb = (CanBoModel) getItem(position);
        ((TextView) view.findViewById(R.id.tv_magv)).setText(String.format("Mã cán bộ: %s", cb.MaCanBo));
        ((TextView) view.findViewById(R.id.tv_tengv)).setText(String.format("Tên cán bộ : %s", cb.TenCanBo));
        ((TextView) view.findViewById(R.id.tv_khoa)).setText(String.format("Khoa : %s", cb.Khoa));
        ((TextView) view.findViewById(R.id.tv_sdt)).setText(String.format("Số điện thoại: %d", cb.SDT));

        return view;
    }
}
