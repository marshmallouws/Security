package dk.cphbusiness.soft.sqlinject;

public class PlaceHolders {

    public static String field(String name, String... whitelist) {
        for (String option : whitelist) {
            if (option.equals(name)) {
                return name;
            }
        }
        throw new IllegalArgumentException("Bad input bro...");
    }

    public static String string(String value) {
        char[] blacklist = {'\''};
        String res = "";

        for(int i = 0; i < value.length(); i++) {
            for(int j = 0; j < blacklist.length; j++) {
                if(value.charAt(i) == blacklist[j]) {
                    res += "\\";
                    break;
                }
            }
            res += value.charAt(i);
        }
        return "'" + res + "'";
    }

    public static String stringList(String... values) {
        // First attempt, does not use "string(...)"
        // return "('"+join("','",values)+"')";
        String result = "";
        for (String value : values) {
            if (result.isEmpty()) {
                result = string(value);
            } else {
                result += ", " + string(value);
            }
        }
        return "(" + result + ")";
    }

    public static String integer(String value) {
        throw new UnsupportedOperationException("No support");
    }

    public static String integerList(String... values) {
        throw new UnsupportedOperationException("No support");
    }

}
