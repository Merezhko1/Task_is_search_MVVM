package com.example.san4o.task_is_search_mvvm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.san4o.task_is_search_mvvm.databinding.ItemBinding;
import com.example.san4o.task_is_search_mvvm.model.Person;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import java.util.Comparator;

/**
 * Created by san4o on 06.03.17.
 */

public class PersonAdapter extends SortedListAdapter<Person> {

    public interface Listener{
        void onPersonModelClicked(Person person);
    }

    private final Listener mListener;

    public PersonAdapter(Context context, Comparator<Person> comparator, Listener listener) {
        super(context, Person.class, comparator);
        mListener = listener;
    }

    @Override
    protected ViewHolder<? extends Person> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewTipe) {
        ItemBinding binding = ItemBinding.inflate(inflater, parent, false);
        return new PersonHolder(binding, mListener);
    }

    @Override
    protected boolean areItemsTheSame(Person item1, Person item2) {
        return item1.getId() == item2.getId();
    }

    @Override
    protected boolean areItemContentsTheSame(Person oldItem, Person newItem) {
        return oldItem.equals(newItem);
    }
}
