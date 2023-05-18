package hcmute.edu.hnlbook.utlis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
  private static final String VALID_PHONE_REGEX = "^\\d{10}$";
  private static final String VALID_EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
  private static final String VALID_ISBN10_REGEX = "^\\d{1,5}-\\d{1,7}-\\d{1,6}-\\d$";
  private static final String VALID_ISBN13_REGEX = "^(978|979)-\\d{1,5}-\\d{1,7}-\\d{1,6}-\\d$";

  public enum Type{
    EMAIl,
    PHONE,
    ISBN10,
    ISBN13
  }
  public static boolean isWhatever(Type type, String value)
  {
    String tempRegex = switch (type) {
      case PHONE -> VALID_PHONE_REGEX;
      case EMAIl -> VALID_EMAIL_REGEX;
      case ISBN10 -> VALID_ISBN10_REGEX;
      case ISBN13 -> VALID_ISBN13_REGEX;
    };
    Pattern pattern = Pattern.compile(tempRegex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(value);
    return matcher.matches();
  }
}
