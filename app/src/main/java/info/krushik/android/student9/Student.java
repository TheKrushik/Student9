package info.krushik.android.student9;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    public long id;
    public long idGroup;
    public String FirstName;
    public String LastName;
    public long Age;

    public Student(long idGroup, String firstName, String lastName, long age) {
        this.idGroup = idGroup;
        FirstName = firstName;
        LastName = lastName;
        Age = age;
    }

    public Student() {
    }

    public Student(String firstName, String lastName, long age) {
        FirstName = firstName;
        LastName = lastName;
        Age = age;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.idGroup);
        dest.writeString(this.FirstName);
        dest.writeString(this.LastName);
        dest.writeLong(this.Age);
    }

    protected Student(Parcel in) {
        this.id = in.readLong();
        this.idGroup = in.readLong();
        this.FirstName = in.readString();
        this.LastName = in.readString();
        this.Age = in.readLong();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}