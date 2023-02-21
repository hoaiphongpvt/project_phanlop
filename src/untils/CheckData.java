package untils;

public class CheckData {

    public static char[] number = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    public static Boolean checkNumber(String data) {
        Boolean check = false;
        char datas[] = data.toCharArray();
        for (int i = 0; i < datas.length; i++) {
            check = false;
            for (int j = 0; j < number.length; j++) {
                if (datas[i] == number[j]) {
                    check = true;
                }
            }
            if (check == false) {
                return check;
            }
        }
        return check;
    }

    public static Boolean checkSoDienThoai(String data) {
        Boolean check = false;
        char datas[] = data.toCharArray();
        if (data.length() != 10) {
            return check;
        }
        for (int i = 0; i < datas.length; i++) {
            check = false;
            for (int j = 0; j < number.length; j++) {
                if (datas[i] == number[j]) {
                    check = true;
                }
            }
            if (check == false) {
                return check;
            }
        }
        return check;
    }
}
