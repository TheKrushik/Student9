package info.krushik.android.student9;

import android.os.Parcel;
import android.os.Parcelable;

public class Group implements Parcelable{
    public long id;

    public String Number;

    public Group(String number) {
        this.Number = number;
    }

    public Group() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.Number);
    }

    protected Group(Parcel in) {
        this.id = in.readInt();
        this.Number = in.readString();
    }

    public static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel source) {
            return new Group(source);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };
}
