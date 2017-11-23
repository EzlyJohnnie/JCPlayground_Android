package johnnie.com.jcplayground_android;

/**
 * Created by Johnnie on 29/10/17.
 */

public class JCEditTextDateUtils {

    public static JCTextIndex strForDate(String preStr, String currentStr, int preSelectEnd){
        JCTextIndex textIndex = new JCTextIndex();
        int selectedIndex = preSelectEnd;

        String preMonth = "";
        String preYear = "";
        String[] preDate = preStr.split("/");
        if(preDate.length > 0){
            preMonth = preDate[0];
        }
        if(preDate.length > 1){
            preYear = preDate[1];
        }

        String month = "";
        String year = "";
        String[] date = currentStr.split("/");
        if(date.length > 0){
            month = date[0];
        }
        if(date.length > 1){
            year = date[1];
        }

        if(month.length() > 2 && year.length() == 0){
            String temp = month;
            month = temp.substring(0, 2);
            year = temp.substring(2, temp.length());
        }


        if(month.length() > 2){
            month = preMonth;
            selectedIndex--;
        }

        if(year.length() > 2){
            year = preYear;
            selectedIndex--;
        }

        String finalStr = month;
        if(currentStr.length() > preStr.length()){
            //input
            if(month.length() >= 2 || year.length() > 0){
                finalStr += "/" + year;
                if(preMonth.length() < month.length()
                        || preYear.length() < year.length()) {
                    selectedIndex++;
                }
            }
        }
        else{
            //delete
            if(preYear.length() == 0 && preStr.contains("/") && !currentStr.contains("/")){
                finalStr = month.substring(0, 1);
            }
            else if(month.length() > 0 || year.length() > 0){
                finalStr += "/" + year;
            }
        }

        if(selectedIndex < 0){
            selectedIndex = 0;
        }
        else if(selectedIndex > finalStr.length()){
            selectedIndex = finalStr.length();
        }

        textIndex.text = finalStr;
        textIndex.selectedIndexEnd = selectedIndex;
        return textIndex;
    }


    public static class JCTextIndex{
        public int selectedIndexEnd;
        public String text;
    }
}
