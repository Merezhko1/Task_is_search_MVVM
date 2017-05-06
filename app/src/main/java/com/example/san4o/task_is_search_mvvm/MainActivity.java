package com.example.san4o.task_is_search_mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.san4o.task_is_search_mvvm.databinding.ActivityMainBinding;
import com.example.san4o.task_is_search_mvvm.model.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {


    public static final String STACK = "Stack";
    public static final String KEY = "Key";
    public static final String[] NAME =  new String[] {"Denys", "Stanislav", "Vadim", "Eugeniy", "Alexander", "Vladislav", "Sergey", "Aleksey"};
    private static final String[] SURNAME = new String[] {"Kalashnyk", "Puzin", "Hotiun", "Kovalov", "Merezhko", "Pomoynyts'kyy", "Rudenko", "Greenyuk"};
    private static final String[] PHONE_NUMBER = new String [] {"0993414821", "0667077979", "0974995005", "0933988237", "0932007592", "0631365815", "0938375507", "0679999977"};
    private static final String[] MAIL = new String [] {"kalashnyk.denys@gmail.com", "stanislavshido@gmail.com", "v.a.d.i.k@mail.ru", "eugene.kovalev@me.com",
            "merezhkosasha@gmail.com", "ekar89@mail.ru", "thesergeyrudenko@gmail.com", "alexey.grinyuk@gmail.com"};
    private static final String[] SKYPE = new String [] {"denis_ka27", "shido_s", "hotun.vadim", "kovalev_eugene", "sasha_merezhko", "vladislavpom", "sergey_rudenko_84", "greenya1"};
    public static final String [] FOTO = {"http://inrosa-auto.com.ua/images/big_slide/4.png",
            "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcT-ZoKiYmasqfHAxdpo4SY7jBtqubJWsBpwEdYLZjXOzEd9hu7R9g",
            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTNMo7m5NT4SVXQ1DVXlWQfjHGA9xKdB5k4dTu64kZ-ibw-cp2l",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQH6g-RlWn-o0eW9QEZVsnuqK9jiFsISDgWzH6XYZOMotP3-6dR9g",
            "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRH6V3UggwkMCNdhItafOmOVwxeYYX_QoWFXfbG4q3aoFLxqYx2",
            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcT9amZGk2iReYOzY-vLSftw0tV3thO-91wqia55sPPzXIE-NxPI",
            "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRN3C7PqPkAlU-PyDOw1rMJviHUKQCBUscskOqZlPgBMCv_FdNlRw",
            "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTK_fofEumwI2bI2FPgnhlyJkJqc9eXLkQAw_pJq6nUYygxxCUqXA"};
    ArrayList<Person> list;
    private static final Comparator<Person> ALPHABETICAL_COMPARATOR = new Comparator<Person>() {
        @Override
        public int compare(Person a, Person b) {
            return a.getName().compareTo(b.getName());
        }
    };
    private ActivityMainBinding mBinding;
    PersonAdapter adapter;
    AdapterSharedPreferences adapterSharedPreferences;
    FragmentPerson fragmentPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = new ArrayList<>();
        for (int i = 0, count = NAME.length; i < count; i++) {
            list.add(new Person(i, NAME[i], SURNAME[i], PHONE_NUMBER[i], MAIL[i], SKYPE[i], FOTO[i]));
        }
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.resView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        adapter = new PersonAdapter(this, ALPHABETICAL_COMPARATOR, new PersonAdapter.Listener() {
            @Override
            public void onPersonModelClicked(Person person) {
                fragmentPerson = FragmentPerson.newInstanse((int) person.getId());
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.action_container, fragmentPerson).addToBackStack(null).commit();
                mBinding.resView.setVisibility(View.GONE);
                final String name = getString(R.string.model_clicked_pattern, person.getName());
                Toast.makeText(mBinding.getRoot().getContext(), name, Toast.LENGTH_SHORT ).show();
            }
        });
        mBinding.resView.setAdapter(adapter);
        adapter.edit()
                .replaceAll(list)
                .commit();
        adapterSharedPreferences = new AdapterSharedPreferences();
        adapterSharedPreferences.savePerson(this, list);
    }

    @Override
    public void onBackPressed() {
        mBinding.resView.setVisibility(View.VISIBLE);
        super.onBackPressed();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        final List<Person> filteredModelList = filter(list, query);
        adapter.edit()
                .replaceAll(filteredModelList)
                .commit();

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private static List<Person> filter(List<Person> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<Person> filteredModelList = new ArrayList<>();
        for (Person model : models) {
            final String name = model.getName().toLowerCase();
            if (name.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}
