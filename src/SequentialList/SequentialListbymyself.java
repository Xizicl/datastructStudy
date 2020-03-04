package SequentialList;

public class SequentialListbymyself {
    private int data[];
    private int length;

    public SequentialListbymyself(int length) {
        this.data = new int[length];
        this.length = 0;
    }

    public SequentialListbymyself() {
        this.data = new int[20];
        this.length = 0;
    }

    public Boolean ListEmpty() {
        if (this.data == null) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean ClearList() {
        this.data = null;
        this.length = 0;
        return true;
    }

    public int GetElem(int location) {
        if (this.data.length < location || location < 1 || this.data.length == 0) {
            return 0;
        } else {
            return this.data[location - 1];
        }
    }

    public int LocateElem(int elem) {
        // TODO 书46页说明的查找功能
        return 0;
    }

    public boolean ListInsert(int location, int elem) {
        if (this.data.length == this.length) {
//            int[] temp = this.data;
//            this.data = new SequentialList(this.data.length + 20).data;
//            for (int i = 0; i < this.length; i++) {
//                this.data[i] = temp[i];
//            }
            return false;
        }
        if (location < 1 || location > this.length+1) {
            return false;
        }
        if (location < this.length) {
            for (int i = this.length - 1; i >= location - 1; i--) {
                this.data[i + 1] = this.data[i];
            }
        }
        this.data[location - 1] = elem;
        this.length++;
        return true;
    }

    public boolean ListDelete(int location) {
        if (this.length == 0) {
            return false;
        }
        if (location < 1 || location > this.length) {
            return false;
        }
        if (location < this.length) {
            for (int i = location; i < this.length; i++) {
                this.data[i - 1] = this.data[i];
            }
        }
        this.length--;
        return true;
    }
    public int ListLength(){
        return this.length;
    }


}
