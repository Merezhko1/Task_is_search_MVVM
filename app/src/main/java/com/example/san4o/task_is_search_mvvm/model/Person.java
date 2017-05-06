package com.example.san4o.task_is_search_mvvm.model;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

/**
 * Created by san4o on 06.03.17.
 */

public class Person implements SortedListAdapter.ViewModel{
        private final long mId;
        private final String mName;
        private final String mSurname;
        private final String mPhoneNumber;
        private final String mMail;
        private final String mSkype;
        private final String mFoto;

        public Person(long id, String name, String surname, String phoneNumber, String mail, String skype, String foto) {
                mId = id;
                mName = name;
                mSurname = surname;
                mPhoneNumber = phoneNumber;
                mMail = mail;
                mSkype = skype;
                mFoto = foto;
        }

        public long getId() {return mId;}

        public String getName() {return mName;}

        public String getSurname() {return mSurname;}

        public String getPhoneNumber(){return mPhoneNumber;}

        public String getMail(){return mMail;}

        public String getSkype() {return mSkype;}

        public String getFoto() {return mFoto;}

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Person person = (Person) o;

                if (mId != person.mId){ return false;}
                return  mName != null ? mName.equals(person.mName) : person.mName == null ||
                        mSurname != null ? mSurname.equals(person.mSurname) : person.mSurname == null ||
                        mPhoneNumber != null ? mPhoneNumber.equals(person.mPhoneNumber) : person.mPhoneNumber == null ||
                        mMail != null ?mMail.equals(person.mMail) : person.mMail == null ||
                        mSkype != null ? mSkype.equals(person.mSkype) : person.mSkype == null||
                        mFoto != null ? mFoto.equals(person.mFoto) : person.mFoto == null;

        }



        @Override
        public int hashCode() {
                int result = (int) (mId ^ (mId >>> 32));
                result = 31 * result + (mName != null ? mName.hashCode() : 0);
                result = 31 * result + (mSurname != null ? mSurname.hashCode() : 0);
                result = 31 * result + (mPhoneNumber != null ? mPhoneNumber.hashCode() : 0);
                result = 31 * result + (mMail != null ? mMail.hashCode() : 0);
                result = 31 * result + (mSkype != null ? mSkype.hashCode() : 0);
                result = 31 * result + (mFoto != null ? mFoto.hashCode() : 0);
                return result;
        }

}
