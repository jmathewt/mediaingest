package org.nypl.mediaingest.utils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RepoUtils {

  public static String getPath(String uuid) {
    System.out.println("Entering into getPath(String uuid)");
    System.out.println("uuid : " + uuid);

    Pattern pattern =
        Pattern.compile("(....)(....)\\-(....)\\-(....)\\-(....)\\-(....)(....)(..)..");

    String uuidString = uuid.toUpperCase();
    Matcher matcher = pattern.matcher(uuidString);

    if (!matcher.matches())
      throw new IllegalArgumentException("Bad UUID.");

    StringBuilder sb = new StringBuilder();
    String root = "";
    sb.append(root);
    sb.append(uuidString.substring(0, 2));

    String separatorChar = File.separator;

    for (int i = 1; i <= 8; i++) {
      sb.append(separatorChar);
      sb.append(matcher.group(i));
    }

    sb.append(separatorChar);
    sb.append(uuidString);
    System.out.println("path : " + sb);
    System.out.println("leaving from getPath(String uuid)");
    return sb.toString();
  }


}
