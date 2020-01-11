package case1.nl.util;

import org.apache.commons.lang3.StringUtils;

public class Tabricator {

    /**
     *
     * @param tsvString
     * @return
     */
    public String getRemarkupTable(String tsvString) {

        StringBuilder result = new StringBuilder();

        if (tsvString != null) {

            String[] lines = tsvString.split(System.getProperty("line.separator"));

            for (int i = 0; i < lines.length; i++) {

                String line = lines[i];

                result.append("| ")
                        .append(line.replace("\t", " | "))
                        .append("\n");

                //Setting the header
                if (i == 0) {

                    String underLine = "";

                    int columnCount = StringUtils.countMatches(line, "\t");

                    for (int j = 0; j < columnCount + 1; j++) {
                        underLine += "| ---- ";
                    }
                    result.append(underLine)
                            .append("\n");
                }
            }

        }
        return result.toString();
    }

}
