package com.example.san4o.task_is_search_mvvm;

import com.example.san4o.task_is_search_mvvm.databinding.ItemBinding;
import com.example.san4o.task_is_search_mvvm.model.Person;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

/**
 * Created by san4o on 06.03.17.
 */

public class PersonHolder extends SortedListAdapter.ViewHolder<Person> {
    private final ItemBinding mBinding;

    public PersonHolder(ItemBinding binding, PersonAdapter.Listener listener) {
        super(binding.getRoot());
        binding.setListener(listener);
        mBinding = binding;
    }
        @Override
        protected void performBind (Person person){
            mBinding.setPerson(person);
        }
    }
