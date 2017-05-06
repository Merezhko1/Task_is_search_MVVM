package com.example.san4o.task_is_search_mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.san4o.task_is_search_mvvm.databinding.FragmentBinding;
import com.example.san4o.task_is_search_mvvm.model.Person;

import java.util.ArrayList;

/**
 * Created by san4o on 11.03.17.
 */

public class FragmentPerson extends Fragment {

    Person mPerson;
    private FragmentBinding fragmentBinding;
    AdapterSharedPreferences adapterSharedPreferences;
    ArrayList<Person> listFragment;
    int id;

    public static FragmentPerson newInstanse(int id){
        FragmentPerson fragmentPerson = new FragmentPerson();
        Bundle bundle = new Bundle();
        bundle.putInt(MainActivity.KEY, id);
        fragmentPerson.setArguments(bundle);
        return fragmentPerson;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle) {
        adapterSharedPreferences = new AdapterSharedPreferences();
        listFragment = adapterSharedPreferences.getPersons(getContext());

        id = getArguments().getInt(MainActivity.KEY);
        mPerson = listFragment.get(id);
        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment, container, false);
        fragmentBinding.setPerson(mPerson);
        return  fragmentBinding.getRoot();

    }

}
