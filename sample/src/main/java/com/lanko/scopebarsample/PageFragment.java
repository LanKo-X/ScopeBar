package com.lanko.scopebarsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * For pager
 * Created by Lex on 16/7/23.
 */
public class PageFragment extends Fragment {

    public static final String CLASS_NAME = PageFragment.class.getName();
    public static final String KEY_POSITION = CLASS_NAME + ".Position";

    public static Fragment getInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);

        Fragment fragment = new PageFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_page, container, false);

        TextView pageNumber = (TextView) contentView.findViewById(R.id.tv_page_number);
        pageNumber.setText(String.valueOf(getArguments().getInt(KEY_POSITION)));

        return contentView;
    }
}
